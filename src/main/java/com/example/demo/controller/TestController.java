package com.example.demo.controller;

import com.example.demo.model.SysUser;
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


    @GetMapping("hello")
    public Optional<SysUser> hello(Integer id){
        return sysUserService.findUserId(id);
    }


}
