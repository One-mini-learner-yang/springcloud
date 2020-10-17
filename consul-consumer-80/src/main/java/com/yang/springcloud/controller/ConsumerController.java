package com.yang.springcloud.controller;

import com.yang.springcloud.entities.CommonResult;
import com.yang.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class ConsumerController {
    @Resource(name = "RestTemplate")
    private RestTemplate restTemplate;

    private static final String PAYMENT_URL="HTTP://payment-service";
    @GetMapping("/consumer/payment/consul")
    public CommonResult<String> consul(){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/consul",CommonResult.class);
    }
}
