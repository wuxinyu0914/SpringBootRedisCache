package com.example.demo.dao;

import com.example.demo.model.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author:wuxy
 * @Description:
 * @Date:2018/9/3
 */
public interface SysUserRepository extends JpaRepository<SysUser,Integer> {


}
