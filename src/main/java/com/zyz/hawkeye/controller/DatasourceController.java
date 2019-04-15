package com.zyz.hawkeye.controller;

import com.zyz.hawkeye.http.DatasourceVO;
import com.zyz.hawkeye.http.HawkeyeResponse;
import com.zyz.hawkeye.service.DatasourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
