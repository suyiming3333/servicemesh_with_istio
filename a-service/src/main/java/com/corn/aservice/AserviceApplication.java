package com.corn.aservice;

import com.corn.aservice.feign.BserviceFeignClient;
import feign.Contract;
import feign.Feign;
import feign.httpclient.ApacheHttpClient;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.corn"})
public class AserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AserviceApplication.class, args);
    }

    @Bean
    public BserviceFeignClient buildBserviceFeignClient() {
        return Feign.builder()
                .client(new ApacheHttpClient())
                //.contract(new SpringMvcContract())
                //.decoder(new JacksonDecoder())
                //.encoder(new JacksonEncoder())
                .target(BserviceFeignClient.class, "http://b-service:8080");
    }

}
