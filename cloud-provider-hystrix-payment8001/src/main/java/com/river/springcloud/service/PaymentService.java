package com.river.springcloud.service;

import com.river.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {
    public String paymentInfo_OK(Integer id);

    public String paymentInfo_TimeOut(Integer id);
}
