package com.zyz.hawkeye.service;

import com.alibaba.fastjson.JSON;
import com.zyz.hawkeye.dao.DashboardRepository;
import com.zyz.hawkeye.dao.entity.DashboardEntity;
import com.zyz.hawkeye.exception.HawkEyeException;
import com.zyz.hawkeye.http.DashboardVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Objects;
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
}
