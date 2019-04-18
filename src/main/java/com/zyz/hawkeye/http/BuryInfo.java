package com.zyz.hawkeye.http;

import lombok.Data;

@Data
public class BuryInfo {
    private String event;

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof BuryInfo) {
            BuryInfo other = (BuryInfo)obj;
            return other.getEvent().equals(event);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return event.hashCode();
    }
}
