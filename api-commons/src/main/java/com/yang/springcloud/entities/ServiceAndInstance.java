package com.yang.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceAndInstance {
    private List<String> servers;
    private List<ServiceInstance> instances;
}
