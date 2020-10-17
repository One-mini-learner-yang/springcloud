package com.yang.springcloud.service;

import com.yang.springcloud.entities.Payment;


public interface PaymentService {
    public int create(Payment payment);
    public Payment getPaymentById(Long id);
}
