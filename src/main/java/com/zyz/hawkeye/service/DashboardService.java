package com.zyz.hawkeye.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Sets;
import com.zyz.hawkeye.dao.DashboardRepository;
import com.zyz.hawkeye.dao.entity.DashboardEntity;
import com.zyz.hawkeye.exception.HawkEyeException;
import com.zyz.hawkeye.http.DashboardVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DashboardService {

    @Autowired
    private DashboardRepository dashboardRepository;

    @Autowired
    private ChartService chartService;

    public DashboardVO get(Integer id) {
        DashboardEntity entity = dashboardRepository.findById(id)
                .orElseThrow(() -> new HawkEyeException("无此看板"));
        return entity2VO(entity);
    }

    private DashboardVO entity2VO(DashboardEntity entity) {
        DashboardVO vo = new DashboardVO();
        vo.setConfig(JSON.parse(entity.getConfig()));
        vo.setCreateDate(entity.getCreateDate().getTime());
        vo.setId(entity.getId());
        vo.setDashboardDesc(entity.getDashboardDesc());
        vo.setName(entity.getName());
        if (!StringUtils.isEmpty(entity.getChartIds())) {
            vo.setChartList(
                    JSON.parseArray(entity.getChartIds(), Integer.class).stream()
                            .map(chartService::get)
                            .filter(Objects::nonNull)
                            .collect(Collectors.toList())
            );
        }
        return vo;
    }

    public int updateLayout(Integer dashboardId, String content) {
        DashboardEntity entity = dashboardRepository.findById(dashboardId)
                .orElseThrow(() -> new HawkEyeException("无此看板"));
        JSONObject jb = JSON.parseObject(entity.getConfig());
        JSONArray ja = JSONArray.parseArray(content);
        jb.put("layout", ja);
        entity.setConfig(jb.toJSONString());
        dashboardRepository.save(entity);
        return 1;
    }

    public int addChart(Integer dashboardId, Integer chartId) {
        DashboardEntity entity = dashboardRepository.findById(dashboardId)
                .orElseThrow(() -> new HawkEyeException("无此看板"));
        List<Integer> ints = JSON.parseArray(entity.getChartIds(), Integer.class);
        Set<Integer> chartSet = ints == null ? Sets.newHashSet() : Sets.newHashSet(ints);

        chartSet.add(chartId);
        entity.setChartIds(JSON.toJSONString(chartSet));
        dashboardRepository.save(entity);
        return 1;
//        JSONObject jb = JSON.parseObject(entity.getConfig());
////
////        List<GridItemVO> grids = JSON.parseArray(jb.getString("layout"), GridItemVO.class);

    }
}
