package com.zyz.hawkeye.dao.druid.entity;

import in.zapr.druid.druidry.aggregator.HyperUniqueAggregator;
import lombok.Data;

/**
 * Created by cheng on 2018/12/22.
 */
@Data
public class HyperUniqueAggregatorNew extends HyperUniqueAggregator {

    private Boolean round;

    public HyperUniqueAggregatorNew(String name, String fieldName, Boolean round) {
        super(name, fieldName);
        this.round = round;
    }
}
