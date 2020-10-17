package com.yang.springcloud.controller;

import com.yang.springcloud.entities.CommonResult;
import com.yang.springcloud.entities.Payment;
import com.yang.springcloud.entities.ServiceAndInstance;
import com.yang.springcloud.lb.MyLb;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class OrderController {
    @Resource
    private DiscoveryClient discoveryClient;
    @Autowired
    private RestTemplate restTemplate;
    @Resource
    private MyLb myLb;
//    private static final String PAYMENT_URL="http://localhost:8001";
    private static final String PAYMENT_URL="http://CLOUD-PAYMENT-SERVICE";
    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
    }
    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }
    @GetMapping("/consumer/payment/discovery")
    public CommonResult<ServiceAndInstance> discovery(){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/discovery",CommonResult.class);
    }
    @GetMapping("/consumer/payment/lb")
    public String lb(){
        List<ServiceInstance> serviceInstances=discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (serviceInstances==null||serviceInstances.size()==0){
            return null;
        }
        return restTemplate.getForObject(myLb.server(serviceInstances).getUri()+"/payment/lb",String.class);
    }
}
