package com.zyz.hawkeye.controller;

import com.alibaba.fastjson.JSON;
import com.zyz.hawkeye.enums.metric.DataSourceType;
import com.zyz.hawkeye.exception.HawkEyeException;
import com.zyz.hawkeye.http.DatasourceVO;
import com.zyz.hawkeye.http.HawkeyeResponse;
import com.zyz.hawkeye.http.MysqlInfo;
import com.zyz.hawkeye.service.DatasourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/hawkeye/api/datasources")
public class DatasourceController {

    @Autowired
    private DatasourceService datasourceService;

    @GetMapping("")
    public HawkeyeResponse<List<DatasourceVO>> list() {
        return HawkeyeResponse.success(datasourceService.list());
    }

    @GetMapping("/fields")
    public HawkeyeResponse<List<String>> listFields(@RequestParam("info") String info,
                                                    @RequestParam("type") String type) {
        switch (DataSourceType.fromType(type)) {
            case MYSQL: return HawkeyeResponse.success(datasourceService.listFields(JSON.parseObject(info, MysqlInfo.class)));
            case BURY: return HawkeyeResponse.success(new ArrayList<>());
            default: throw new HawkEyeException("不支持的数据源类型");
        }

    }

}
