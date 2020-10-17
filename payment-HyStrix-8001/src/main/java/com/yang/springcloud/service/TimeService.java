package com.yang.springcloud.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TimeService {
    public String getInfo_Ok(){
        return "线程池"+Thread.currentThread().getName()+"O(∩_∩)O";
    }
    public String getInfo_TimeOut() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        return "线程池"+Thread.currentThread().getName()+"/(ㄒoㄒ)/~~";
    }
}
