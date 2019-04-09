package com.zyz.hawkeye.dto;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.Map;

@Data
public class BinlogDTO {

    private String database;

    private String table;

    private String type;

    // dml发生的时间
    private Long ts;

    private Long xid;

    private Boolean commit;

    // 完整的数据，在改数据时，此处为修改后的数据
    private JSONObject data;

    // 改数据时才会有，只保存被修改的旧数据
    private JSONObject old;
}
