package com.example.demo.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author:wuxy
 * @Description:
 * @Date:2018/8/22
 */
@Entity
@Data
@Table(name = "sys_user")
public class SysUser implements Serializable{

    @Id
    private Integer id;

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String passWord;
}
