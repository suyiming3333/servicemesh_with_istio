package com.corn.aservice.controller;

import com.corn.aservice.feign.BserviceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    private BserviceFeignClient bserviceFeignClient;

    @Value("${service-b}")
    private String serviceUrl;


    @GetMapping("/urlConfig")
    public String serviceUrl(){
        System.out.println("urlConfig");
        return serviceUrl;
    }


    @GetMapping("/invokeb")
    public String invokeb(){
        System.out.println("a invoke b");
        return bserviceFeignClient.invokeB();
    }

    @Value("${javahome}")
    private String javahome;


    @GetMapping("/invoke")
    public String invoke(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("invoke");
        return "response from svc-a";
}

    @GetMapping("/health")
    public String health() {
        return "success";
    }
}
