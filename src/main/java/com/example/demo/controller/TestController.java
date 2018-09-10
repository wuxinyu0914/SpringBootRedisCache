package com.example.demo.controller;

import com.example.demo.model.SysUser;
import com.example.demo.mq.Sender;
import com.example.demo.services.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @Author:wuxy
 * @Description:
 * @Date:2018/8/22
 */
@RestController
public class TestController {

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private Sender sender;


    @GetMapping("hello")
    public SysUser hello(Integer id){
        return sysUserService.findUserId(id);
    }

    @GetMapping("testMq")
    public String testMq(Integer userId){
        SysUser user =  sysUserService.findUserId(userId);
        if (user!=null){
            sender.sendTopic(user);
        }
        return "OK";
    }

    @GetMapping("testMqToFanout")
    public String testMqToFanout(Integer userId){
        SysUser user =  sysUserService.findUserId(userId);
        if (user!=null){
            sender.sendFanout(user);
        }
        return "OK-Fanout";
    }



}
