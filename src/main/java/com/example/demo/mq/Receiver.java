package com.example.demo.mq;

import com.example.demo.config.RabbitConfig;
import com.example.demo.model.SysUser;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

/**
 * @Author:wuxy
 * @Description:
 * @Date:2018/9/4
 */
@Component
public class Receiver implements ChannelAwareMessageListener {

    /**
     * Direct模式
     * @param user
     */
    @RabbitListener(queues = RabbitConfig.QUEUE_NAME)
    public void receiver(SysUser user){
        System.out.println("msg—Direct:"+user.getUserName());
    }

    /**
     * topic模式
     * @param user
     */
    @RabbitListener(queues = RabbitConfig.QUEUE_TOPIC_NAME)
    public void receiverTopic(SysUser user){
        System.out.println("msg-topic:"+user.getUserName());
    }


    /**
     * fanout模式
     * @param user
     */
    @RabbitListener(queues = RabbitConfig.QUEUE_FANOUTA_NAME)
    public void receiverFanout(SysUser user){
        System.out.println("msg-fanoutA:"+user.getUserName());
    }

    /**
     * fanout模式
     * @param user
     */
    @RabbitListener(queues = RabbitConfig.QUEUE_FANOUTB_NAME)
    public void receiverFanoutB(SysUser user){
        System.out.println("msg-fanoutB:"+user.getUserName());
    }

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        System.out.println("消费端接收到消息:" + message.getMessageProperties() + ":" + new String(message.getBody()));
        // false只确认当前一个消息收到，true确认所有consumer获得的消息
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
