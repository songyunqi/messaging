package com.foo.messaging.rocket.test;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.sql.DataSourceDefinition;
import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties(RocketmqProperties.PREFIX)
@Data
public class RocketmqProperties {
    public static final String PREFIX = "zebra.rocketmq";
    private String namesrvAddr;
    private String producerGroupName;
    private String transactionProducerGroupName;
    private String consumerGroupName;
    private String producerInstanceName;
    private String consumerInstanceName;
    private String producerTranInstanceName;
    private int consumerBatchMaxSize;
    private boolean consumerBroadcasting;
    private boolean enableHisConsumer;
    private boolean enableOrderConsumer;
    private List subscribe = new ArrayList<>();
}


