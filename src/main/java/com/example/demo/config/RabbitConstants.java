package com.example.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author:wuxy
 * @Description:
 * @Date:2018/9/11
 */

public class RabbitConstants {

    /**
     * 队列
     */
    public static final String QUEUE_NAME = "my-test-mq";
    public static final String QUEUE_TOPIC_NAME = "my-test-mq-topic";
    public static final String QUEUE_FANOUTA_NAME = "my-test-mq-fanoutA";
    public static final String QUEUE_FANOUTB_NAME = "my-test-mq-fanoutB";

    /**
     * 交换器
     */

    public static final String EXCHANGE_NAME = "my_exchange";
    public static final String ROUTINGKEY_NAME = "topic.message";

    /**
     * 广播
     */
    public static final String FANOUT_NAME = "my_fanout";

}
