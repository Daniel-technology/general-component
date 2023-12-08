package com.general.component.mvc.core.model;

import com.general.component.common.base.BaseEntity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 用户
 *
 * @author Daniel-SESA735395
 * @date 2023/12/8
 */
public class User extends BaseEntity {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    /**
     * 账号
     */
    private String account;
    /**
     * 密码
     */
    private String password;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机
     */
    private String phone;
    /**
     * 生日
     */
    private Date birthday;
    /**
     * 性别;1-男 2-女 3-未知
     */
    private Integer sex;
    /**
     * 名称
     */
    private String name;
    /**
     * 头像
     */
    private String avatar;
}
