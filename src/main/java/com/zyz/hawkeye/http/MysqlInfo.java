package com.zyz.hawkeye.http;

import lombok.Data;

@Data
public class MysqlInfo {
    private String host;
    private String database;
    private String table;
    private String user;
    private String password;

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof MysqlInfo) {
            MysqlInfo other = (MysqlInfo)obj;
            return other.getDatabase().equals(database) && other.getTable().equals(table);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (database+table).hashCode();
    }
}
