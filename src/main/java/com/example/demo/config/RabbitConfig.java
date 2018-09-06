package com.example.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author:wuxy
 * @Description:
 * @Date:2018/9/4
 */
@Configuration
public class RabbitConfig {

    public static final String QUEUE_NAME="my-test-mq";
    public static final String QUEUE_TOPIC_NAME = "my-test-mq-topic";
    public static final String EXCHANGE_NAME = "my_exchange";
    public static final String ROUTINGKEY_NAME = "topic.message";

    /**
     * 配置队列
     * @return
     */
    @Bean(name="taskQueue")
    public Queue queue(){
        return new Queue(QUEUE_NAME);
    }

    /**
     * topic 队列
     * @return
     */
    @Bean(name = "topicQueue")
    public Queue queueTopic(){
        return new Queue(QUEUE_TOPIC_NAME);
    }

    /**
     * 配置交换机
     * @return
     */
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(EXCHANGE_NAME);
    }

    /**
     * 队列绑定交换机
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchange(@Qualifier("topicQueue") Queue queue,TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(ROUTINGKEY_NAME);
    }
}
