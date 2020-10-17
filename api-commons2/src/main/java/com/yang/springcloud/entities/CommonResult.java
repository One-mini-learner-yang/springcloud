package com.yang.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    /**
     * 状态码
     * 描述
     * 数据信息
     */
    private Integer code;
    private String message;
    private T data;
    public CommonResult(Integer code,String message){
            this.code=code;
            this.message=message;
    }
}
