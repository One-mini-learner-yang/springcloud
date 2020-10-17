package com.yang.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBalance {
    ServiceInstance server(List<ServiceInstance> serviceInstances);
}
