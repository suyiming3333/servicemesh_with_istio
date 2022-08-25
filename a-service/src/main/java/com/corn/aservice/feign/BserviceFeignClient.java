package com.corn.aservice.feign;

import feign.RequestLine;
import org.springframework.web.bind.annotation.GetMapping;


public interface BserviceFeignClient {

    @RequestLine("GET /invoke")
    String invokeB();
}
