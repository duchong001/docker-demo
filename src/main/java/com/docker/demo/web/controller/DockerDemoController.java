package com.docker.demo.web.controller;

import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * demo Controller
 *
 * @author DUCHONG
 * @since 2020-08-18 14:53
 **/
@Slf4j
@RestController
public class DockerDemoController {

    @GetMapping("helloDocker")
    public String helloDocker(){

        return "Hello Docker";
    }
}
