package com.docker.demo.validation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 用户控制器
 *
 * @author DUCHONG
 * @since 2020-08-24 23:41
 **/
@RestController
@Slf4j
public class UserController {

    /**
     * 直接抛异常，捕获异常处理
     * @param userRequest
     * @return
     */
    @PostMapping("/user/register")
    public String registerUser1(@Valid @RequestBody UserRequest userRequest){

        return "register success"  ;
    }

    /**
     * 校验结果放进bindingResult里,自行处理
     * @param userRequest
     * @param bindingResult
     * @return
     */
    @PostMapping("/user/register2")
    public String registerUser2(@Valid @RequestBody UserRequest userRequest, BindingResult bindingResult){

        return "register success"  ;
    }
}
