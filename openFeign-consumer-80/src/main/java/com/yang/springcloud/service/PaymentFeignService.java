package com.yang.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {
    @GetMapping("/payment/lb")
    public String lb();

    @GetMapping("/payment/timeOk")
    public String time_ok();

    @GetMapping("/payment/timeOut")
    public String time_out();

}
