package com.yang.springcloud.controller;

import com.yang.springcloud.entities.CommonResult;
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
    @RequestMapping("/payment/consul")
    public CommonResult<String> paymentZk(){
        return new CommonResult<String>(200,"访问成功","springcloud with consul："+port+"\t"+ UUID.randomUUID().toString());
    }
}
