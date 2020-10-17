package com.yang.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Slf4j
public class PaymentController {
    @Value("${server.port}")
    private String port;
    @RequestMapping("/payment/zk")
    public String paymentZk(){
        return "springcloud with zookeeper："+port+"\t"+ UUID.randomUUID().toString();
    }
}
