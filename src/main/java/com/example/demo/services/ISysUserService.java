package com.example.demo.services;

import com.example.demo.model.SysUser;

import java.util.Optional;

/**
 * @Author:wuxy
 * @Description:
 * @Date:2018/8/22
 */
public interface ISysUserService {

    /**
     * 根据UserId查询
     * @param userId
     * @return
     */
    SysUser findUserId(Integer userId);
}
