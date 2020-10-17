package com.yang.springcloud.controller;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.yang.springcloud.service.TimeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
//@DefaultProperties(ignoreExceptions = NumberFormatException.class,defaultFallback = "getInfo_time_outHandler")
public class PaymentController {
    @Autowired
    private TimeService timeService;
    @GetMapping("/payment/timeOk")
    public String time_ok(){
        return timeService.getInfo_Ok();
    }
    @GetMapping("/payment/timeOut")
    @HystrixCommand(fallbackMethod = "getInfo_time_outHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3500")})
    public String time_out() throws InterruptedException {
        return timeService.getInfo_TimeOut();
    }


    @GetMapping("/payment/cBreaker/{id}")
    @HystrixCommand(fallbackMethod = "error_Handler",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")
    })
    public String cBreaker(@PathVariable("id")int id){
        if (id<0){
            throw new NumberFormatException();
        }
        String serial= IdUtil.simpleUUID();
        return serial+"O(∩_∩)O";
    }
    public String getInfo_time_outHandler(){
        return "支付接口当前网络拥挤，请稍后重试"+Thread.currentThread().getName();
    }
    public String error_Handler(@PathVariable("id")int id){
        return id+"不能为负数，请稍后重试";
    }
}
