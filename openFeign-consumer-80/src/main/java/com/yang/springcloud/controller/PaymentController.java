package com.yang.springcloud.controller;

import com.yang.springcloud.service.PaymentFeignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class PaymentController {
    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/lb")
    public String lb(){
        return paymentFeignService.lb();
    }


    @GetMapping("/consumer/payment/timeOk/{id}")
    public String time_ok(){
        return paymentFeignService.time_ok();
    }


    @GetMapping("/consumer/payment/timeOut")
    public String time_out(){
        return paymentFeignService.time_out();
    }
}
