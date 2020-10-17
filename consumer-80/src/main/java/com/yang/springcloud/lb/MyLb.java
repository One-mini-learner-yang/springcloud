package com.yang.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLb implements LoadBalance {
    private AtomicInteger atomicInteger=new AtomicInteger(0);
    public final int next(){
        int current;
        int next;
        do {
            current=this.atomicInteger.get();
            next= current>=2147483647?0:current+1;
        }while(!atomicInteger.compareAndSet(current,next));
        System.out.printf("******第%d次访问*******\n",next);
        return next;
    }
    @Override
    public ServiceInstance server(List<ServiceInstance> serviceInstances) {
        int index=next()%serviceInstances.size();
        return serviceInstances.get(index);
    }
}
