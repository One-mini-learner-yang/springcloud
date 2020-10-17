package com.yang.springcloud.controller;

import com.yang.springcloud.entities.CommonResult;
import com.yang.springcloud.entities.Payment;
import com.yang.springcloud.entities.ServiceAndInstance;
import com.yang.springcloud.service.impl.PaymentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
/**
 * 不需要再写Logger log=LoggerFactory.getLogger
 */
@Slf4j
public class PaymentController {
    @Resource
    private PaymentServiceImpl paymentService;
    @Resource
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverPort;
    /**
     * @RequestBody：接收json字符串，请求体中数据，用于post中（注意，格式为application/json,前端传参默认格式为application/x-www-form-urlencoded）
     * 注意，若以默认格式进行最终还是转换成key1=value1&key2=value2的格式
     * @RequestParm：接收请求头中数据，即格式为xxx?username=123&password=456
     */
    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result=paymentService.create(payment);
        log.info("插入结果："+result);
        if (result>0){
            return new CommonResult(200,"插入数据库成功,serverPort:"+serverPort,result);
        }else {
            return new CommonResult(444,"插入数据库失败",result);
        }
    }
    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id")Long id){
        Payment payment=paymentService.getPaymentById(id);
        log.info("查询到的结果："+payment);
        if(payment!=null){
            return new CommonResult(200,"查询成功,serverPort:"+serverPort,payment);
        }else {
            return new CommonResult(444,"没有对应记录，id："+id,null);
        }
    }


    @GetMapping("/payment/lb")
    public String lb(){
        return serverPort;
    }
    @GetMapping("/payment/discovery")
    public CommonResult<ServiceAndInstance> discovery(){
        ServiceAndInstance serviceAndInstance=new ServiceAndInstance();
        List<String> services=discoveryClient.getServices();
        serviceAndInstance.setServers(services);
        for (String service:services){
            log.info("~~~~~~~services："+service);
        }
        List<ServiceInstance> instances=discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (instances==null)
        {
            serviceAndInstance.setInstances(null);
        }else {
            serviceAndInstance.setInstances(instances);
        }
        for (ServiceInstance serviceInstance:instances){
            log.info("~~~~~~instances："+serviceInstance);
        }
        if (serviceAndInstance!=null){
            return new CommonResult<>(200,"发现服务成功，serverPort："+serverPort,serviceAndInstance);
        }else {
            return new CommonResult<>(444,"注册中心没有服务以及相应实例",null);
        }
    }
}
