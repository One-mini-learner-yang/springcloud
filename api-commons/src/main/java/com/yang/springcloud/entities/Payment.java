package com.yang.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
/**
 * 一下注解使用后会自动添加一个全参和空参构造器
 */
@AllArgsConstructor
@NoArgsConstructor
public class Payment  implements Serializable {
    private Long id;
    private String serial;
}
