package com.corn.aservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.corn"})
public class AserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AserviceApplication.class, args);
    }

}
