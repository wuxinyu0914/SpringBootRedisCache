package com.example.demo.mq;

import com.example.demo.config.RabbitConstants;
import com.example.demo.model.SysUser;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author:wuxy
 * @Description:
 * @Date:2018/9/4
 */
@Component
public class Sender implements RabbitTemplate.ConfirmCallback{

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public Sender(RabbitTemplate template){
        rabbitTemplate=template;
        rabbitTemplate.setConfirmCallback(this);
    }

    /**
     * 发送消息(topic模式)
     * @param user
     */
    public void sendTopic(SysUser user){
        System.out.println("发送消息===="+user.getUserName());
        // Topic 转发模式
        rabbitTemplate.convertAndSend(RabbitConstants.EXCHANGE_NAME,RabbitConstants.ROUTINGKEY_NAME,user);
    }

    /**
     * 发送消息(Direct模式)
     * @param user
     */
    public void sendDirect(SysUser user){
        System.out.println("发送消息===="+user.getUserName());
        //Direct模式 一对一发送
        rabbitTemplate.convertAndSend(RabbitConstants.QUEUE_NAME,user);
    }

    /**
     * 发送消息(Fanout广播模式)
     * @param user
     */
    public void sendFanout(SysUser user){
        System.out.println("发送Fanout消息===="+user.getUserName());
        rabbitTemplate.convertAndSend(RabbitConstants.FANOUT_NAME,"",user);
    }

    /**
     * 消息发送成功确认
     * @param correlationData
     * @param b
     * @param s
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        System.out.println("CorrelationData="+correlationData+";b="+b+";s="+s);
        if(b){
            System.out.println("消息发送成功");
        }else{
            System.out.println("消息发送失败"+s);
        }
    }
}
