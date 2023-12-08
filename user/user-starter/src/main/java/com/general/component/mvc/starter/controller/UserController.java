package com.general.component.mvc.starter.controller;

import com.general.component.mvc.core.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 用户
 *
 * @author Daniel-SESA735395
 * @date 2023/12/8
 */
@Controller
@RequestMapping("/gc/user/v1")
public class UserController {

    @Resource
    private UserService userService;


}
