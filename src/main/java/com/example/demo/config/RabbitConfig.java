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


    /**
     * 配置队列
     *
     * @return
     */
    @Bean(name = "taskQueue")
    public Queue queue() {
        return new Queue(RabbitConstants.QUEUE_NAME);
    }

    /**
     * topic 队列
     *
     * @return
     */
    @Bean(name = "topicQueue")
    public Queue queueTopic() {
        return new Queue(RabbitConstants.QUEUE_TOPIC_NAME);
    }

    /**
     * 广播路由 队列
     *
     * @return
     */
    @Bean(name = "fanoutQueue")
    public Queue queueFanoutA() {
        return new Queue(RabbitConstants.QUEUE_FANOUTA_NAME);
    }

    /**
     * 广播路由 队列
     *
     * @return
     */
    @Bean(name = "fanoutQueueB")
    public Queue queueFanoutB() {
        return new Queue(RabbitConstants.QUEUE_FANOUTB_NAME);
    }

    /**
     * 配置交换机
     *
     * @return
     */
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(RabbitConstants.EXCHANGE_NAME);
    }

    /**
     * 配置广播路由
     *
     * @return
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(RabbitConstants.FANOUT_NAME);
    }

    /**
     * topic模式 队列绑定交换机
     *
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchange(@Qualifier("topicQueue") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(RabbitConstants.ROUTINGKEY_NAME);
    }

    /**
     * 广播模式绑定队列和交换机
     *
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeByFanout(@Qualifier("fanoutQueue") Queue queue, FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }

    /**
     * 广播模式绑定队列和交换机
     *
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeByFanoutB(@Qualifier("fanoutQueueB") Queue queue, FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }

}
