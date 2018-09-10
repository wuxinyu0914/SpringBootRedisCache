package com.example.demo.config;

import org.springframework.amqp.core.*;
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

    //队列
    public static final String QUEUE_NAME="my-test-mq";
    public static final String QUEUE_TOPIC_NAME = "my-test-mq-topic";
    public static final String QUEUE_FANOUTA_NAME = "my-test-mq-fanoutA";
    public static final String QUEUE_FANOUTB_NAME = "my-test-mq-fanoutB";

    //交换器
    public static final String EXCHANGE_NAME = "my_exchange";
    public static final String ROUTINGKEY_NAME = "topic.message";

    //广播
    public static final String FANOUT_NAME = "my_fanout";
//    public static final String

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
     * 广播路由 队列
     * @return
     */
    @Bean(name="fanoutQueue")
    public Queue queueFanoutA(){
        return new Queue(QUEUE_FANOUTA_NAME);
    }

    /**
     * 广播路由 队列
     * @return
     */
    @Bean(name="fanoutQueueB")
    public Queue queueFanoutB(){
        return new Queue(QUEUE_FANOUTB_NAME);
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
     * 配置广播路由
     * @return
     */
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange(FANOUT_NAME);
    }

    /**
     * topic模式 队列绑定交换机
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchange(@Qualifier("topicQueue") Queue queue,TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(ROUTINGKEY_NAME);
    }

    /**
     * 广播模式绑定队列和交换机
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeByFanout(@Qualifier("fanoutQueue") Queue queue,FanoutExchange exchange){
        return BindingBuilder.bind(queue).to(exchange);
    }

    /**
     * 广播模式绑定队列和交换机
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeByFanoutB(@Qualifier("fanoutQueueB") Queue queue,FanoutExchange exchange){
        return BindingBuilder.bind(queue).to(exchange);
    }


}
