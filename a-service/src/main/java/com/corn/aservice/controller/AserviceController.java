package com.corn.aservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author suyiming3333
 * @version 1.0
 * @className: AserviceController
 * @description: TODO
 * @date 2022/8/2 10:12
 */


@RestController
public class AserviceController {


    @GetMapping("/invokeb")
    public String invokeb(){
        System.out.println("a invoke b");
        return "a invoke b";
    }

    @Value("${javahome}")
    private String javahome;


    @GetMapping("/invoke")
    public String invoke(){
        System.out.println("invoke");
        return "response from svc-a";
}

    @GetMapping("/health")
    public String health() {
        return "success";
    }
}
