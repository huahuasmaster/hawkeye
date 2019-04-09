package com.zyz.hawkeye.dao.druid.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode
@Builder
public class DruidDataSource {


    private String type = "index_kafka";
    private DataSchemaBean dataSchema; // 指明数据源格式、数据解析、维度等信息
    private TuningConfigBean tuningConfig;// 存储优化方式
    private IoConfigBean ioConfig;// 数据如何在Druid中存储
    private Object context;


    @Data
    @Builder
    public static class DataSchemaBean {


        private String dataSource;
        private ParserBean parser;
        private GranularitySpecBean granularitySpec;
        private TransformSpecBean transformSpec;
        private List<MetricsSpecBean> metricsSpec;



        @Data
        @Builder
        public static class ParserBean {

            private String type;
            private ParseSpecBean parseSpec;


            @Data
            public static class ParseSpecBean {
                private String format;
                private TimestampSpecBean timestampSpec;
                private DimensionsSpecBean dimensionsSpec;


                @Data
                @AllArgsConstructor
                public static class TimestampSpecBean {
                    private String column;// 指定为时间戳的列
                    private String format;// 格式{iso,lills,auto,Joda,posix}
                }

                @Data
                @AllArgsConstructor
                public static class DimensionsSpecBean {
                    private List<String> dimensions;// 维度名列表，默认类型为string，相比于数字类型的维度，占据磁盘位置更大但是可以建立索引，查询速度更快
                    private List<String> dimensionExclusions; // 剔除的维度名


                }
            }
        }

        @Data
        @Builder
        public static class GranularitySpecBean {

            // 默认uniform
            private String type = "uniform";
            // 数据分片粒度
            private String segmentGranularity;
            // 查询粒度
            private String queryGranularity;
            // 是否进行预聚合
            private boolean rollup;
            // 时间间隔，仅仅用于批处理
            private Object intervals;


        }

        @Data
        public static class TransformSpecBean {
            private Object filter;
            private List<?> transforms;

        }

        @Data

        public static class MetricsSpecBean {
            private String type;// 聚合类型
            private String name;// 聚合后，存于druid的指标列名
            private String fieldName; // 聚合运用的列名
            private Object expression;
            private Boolean isInputHyperUnique;

            public MetricsSpecBean() {
            }

            public MetricsSpecBean(String type, String name, String fieldName) {
                this.type = type;
                this.name = name;
                this.fieldName = fieldName;
            }

            public MetricsSpecBean(String type, String name) {
                this.type = type;
                this.name = name;
            }
        }
    }

    @Data
    public static class TuningConfigBean {


        private String type;
        private int maxRowsInMemory;
        private int maxRowsPerSegment;
        private String intermediatePersistPeriod;
        private String basePersistDirectory;
        private int maxPendingPersists;
        private IndexSpecBean indexSpec;
        private boolean buildV9Directly;
        private boolean reportParseExceptions;
        private int handoffConditionTimeout;
        private boolean resetOffsetAutomatically;
        private Object segmentWriteOutMediumFactory;
        private Object workerThreads;
        private Object chatThreads;
        private int chatRetries;
        private String httpTimeout;
        private String shutdownTimeout;
        private String offsetFetchPeriod;


        @Data
        public static class IndexSpecBean {


            private BitmapBean bitmap;
            private String dimensionCompression;
            private String metricCompression;
            private String longEncoding;


            @Data
            public static class BitmapBean {

                private String type;
            }
        }
    }

    @Data
    @Builder
    public static class IoConfigBean {

        private String topic;
        private int replicas;
        private int taskCount;
        private String taskDuration;
        private ConsumerPropertiesBean consumerProperties;
        private String startDelay;
        private String period;
        private boolean useEarliestOffset;
        private String completionTimeout;
        private Object lateMessageRejectionPeriod;
        private Object earlyMessageRejectionPeriod;
        private boolean skipOffsetGaps;


        @Data
        @AllArgsConstructor
        public static class ConsumerPropertiesBean {
            @JSONField(name = "bootstrap.servers")
            private String servers;
        }
    }

//    @Data
//    @Builder
//    public static class IoConfigBean {
//        private String type;
//
//
//    }
}

