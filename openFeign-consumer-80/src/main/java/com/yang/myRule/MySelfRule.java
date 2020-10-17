package com.yang.myRule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
/**
 * 官方警告，该类一定要写在@Compontent的扫描包外，以达成定制的效果
 * 否则会被所有Ribbon客户端所共享
 */
public class MySelfRule {
    @Bean
    public IRule myRule(){
        return new RandomRule();
    }
}

