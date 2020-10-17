package com.yang.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
@FeignClient(name = "CLOUD-GATEWAY")
public interface HyStrixService {


    @GetMapping("/payment/lb")
    public String lb();

}
