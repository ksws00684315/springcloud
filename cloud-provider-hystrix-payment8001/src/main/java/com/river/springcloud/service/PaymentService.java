package com.river.springcloud.service;

import com.river.springcloud.entities.Payment;

public interface PaymentService {
    public String paymentInfo_OK(Integer id);

    public String paymentInfo_TimeOut(Integer id);

    public String paymentInfo_TimeoutHandler(Integer id);
}
