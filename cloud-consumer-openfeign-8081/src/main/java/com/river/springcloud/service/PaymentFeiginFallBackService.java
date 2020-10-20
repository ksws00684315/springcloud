package com.river.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFeiginFallBackService implements PaymentFeignService{
    @Override
    public String paymentInfo_OK(Integer id) {
        return "paymentInfo_OK fallback";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "paymentInfo_TimeOut fallback";
    }
}
