package com.yang.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yang.springcloud.service.HyStrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.RequestWrapper;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "defaultF")
public class ConsumerController {
    @Autowired
    private HyStrixService hyStrixService;

    @HystrixCommand
    @GetMapping("/consumer/payment/lb")
    public String time_ok(){
        return hyStrixService.lb();
    }
//    @GetMapping("/consumer/payment/timeOut")
//    public String time_out(){
//        return hyStrixService.time_out();
//    }
    public String defaultF(){
        return "降级处理！";
    }
}
