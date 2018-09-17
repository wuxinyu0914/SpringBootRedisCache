package com.example.demo.mq;

import com.example.demo.config.RabbitConfig;
import com.example.demo.config.RabbitConstants;
import com.example.demo.model.SysUser;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Author:wuxy
 * @Description:
 * @Date:2018/9/4
 */
@Component
public class Receiver{

    /**
     * Direct模式
     * @param user
     */
    @RabbitListener(queues = RabbitConstants.QUEUE_NAME)
    public void receiver(SysUser user){
        System.out.println("msg—Direct:"+user.getUserName());
    }

    /**
     * topic模式
     * @param user
     */
    @RabbitListener(queues = RabbitConstants.QUEUE_TOPIC_NAME)
    public void receiverTopic(SysUser user,Channel channel,Message message) throws IOException {
        System.out.println("msg-topic:"+user.getUserName());
        //确认消息,队列中移除该消息(false只确认当前一个消息收到;true确认所有consumer获得的消息)
        ackMsg(channel, message);
    }

    private void ackMsg(Channel channel, Message message) throws IOException {
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        } catch (IOException e) {
            e.printStackTrace();
            //ack返回false，并重新回到队列
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }
    }


    /**
     * fanout模式
     * @param user
     */
    @RabbitListener(queues = RabbitConstants.QUEUE_FANOUTA_NAME)
    public void receiverFanout(SysUser user,Channel channel,Message message) throws IOException {
        System.out.println("msg-fanoutA:"+user.getUserName());
        //确认消息,队列中移除该消息(false只确认当前一个消息收到;true确认所有consumer获得的消息)
        ackMsg(channel, message);
    }

    /**
     * fanout模式
     * @param user
     */
    @RabbitListener(queues = RabbitConstants.QUEUE_FANOUTB_NAME)
    public void receiverFanoutB(SysUser user,Channel channel,Message message) throws IOException {
        System.out.println("msg-fanoutB:"+user.getUserName());
        //确认消息,队列中移除该消息(false只确认当前一个消息收到;true确认所有consumer获得的消息)
        ackMsg(channel, message);
    }

}
