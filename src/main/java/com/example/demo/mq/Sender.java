package com.example.demo.mq;

import com.example.demo.config.RabbitConfig;
import com.example.demo.model.SysUser;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author:wuxy
 * @Description:
 * @Date:2018/9/4
 */
@Component
public class Sender implements RabbitTemplate.ConfirmCallback{

    @Autowired
    private AmqpTemplate template;

    @Resource(name="taskQueue")
    private Queue queue;

    /**
     * 发送消息(topic模式)
     * @param user
     */
    public void sendTopic(SysUser user){
        System.out.println("发送消息===="+user.getUserName());
        // Topic 转发模式
        template.convertAndSend(RabbitConfig.EXCHANGE_NAME,RabbitConfig.ROUTINGKEY_NAME,user);
    }

    /**
     * 发送消息(Direct模式)
     * @param user
     */
    public void sendDirect(SysUser user){
        System.out.println("发送消息===="+user.getUserName());
        //Direct模式 一对一发送
        template.convertAndSend(queue.getName(),user);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        System.out.println("消息ID="+correlationData.getId());
        if(b){
            System.out.println("消息发送成功");
        }else{
            System.out.println("消息发送失败"+s);
        }
    }
}
