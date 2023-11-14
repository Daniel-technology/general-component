package com.general.component.web.controller.log;

import com.general.component.log.model.SensitiveRegex;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description: 日志demo
 * @author: liuyan
 * @create: 2022−10-20 9:22 PM
 */
@RestController
@RequestMapping("/log")
public class LogController {

    @Resource
    private SensitiveRegex sensitiveRegex;

    @GetMapping("/v1/get")
    public Object get(String one, String number) {
        sensitiveRegex.getPatternReg();
        return null;
    }


}
