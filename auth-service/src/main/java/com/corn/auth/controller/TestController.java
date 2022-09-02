package com.corn.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * @author suyiming3333
 * @version 1.0
 * @className: TestController
 * @description: TODO
 * @date 2022/8/9 9:31
 */

@RestController
public class TestController {




    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/hello2")
    public String hello2(){
        return "hello2";
    }

    //admin权限才能访问
    @RolesAllowed("admin")
    @GetMapping("/roleTest")
    public String roleTest(){
        return "roleTest";
    }
}
