package com.example.demo.services.impl;

import com.example.demo.dao.SysUserRepository;
import com.example.demo.model.SysUser;
import com.example.demo.services.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @Author:wuxy
 * @Description:
 * @Date:2018/8/22
 */
@Service
public class SysUserService implements ISysUserService{

    @Autowired
    private SysUserRepository userRepository;

    @Cacheable(value = "userId",unless="#result == null")
    @Override
    public SysUser findUserId(Integer userId) {
        Optional<SysUser> user = userRepository.findById(userId);
        if(user.isPresent()){
           return user.get();
        }
        return null;
    }
}
