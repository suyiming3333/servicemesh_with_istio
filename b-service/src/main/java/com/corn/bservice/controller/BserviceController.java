package com.corn.bservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author suyiming3333
 * @version 1.0
 * @className: AserviceController
 * @description: TODO
 * @date 2022/8/2 10:12
 */

@RestController
public class BserviceController {


    @GetMapping("/invoke")
    public String invoke(HttpServletRequest request, HttpServletResponse response){
        System.out.println("invoke b service");
        return "invoke b svc finish";
    }
}
