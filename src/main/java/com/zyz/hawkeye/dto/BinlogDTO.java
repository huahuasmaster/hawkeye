package com.zyz.hawkeye.dto;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.Map;

@Data
public class BinlogDTO {

    private String database;// 数据库名

    private String table;// 表名

    private String type;// DML类型

    private Long ts;// DML发生的时间

    private Long xid;// 数据库事务ID

    private Boolean commit;// 事务是否提交

    private JSONObject data;// 修改后的完整的数据。

    private JSONObject old;// update或delete操作前的旧数据。
}
