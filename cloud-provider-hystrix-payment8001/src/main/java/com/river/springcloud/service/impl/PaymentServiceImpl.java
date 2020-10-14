package com.river.springcloud.service.impl;

import com.river.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "线程池"+Thread.currentThread().getName()+",paymentInfo_OK_ID:"+id;
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        try{
            TimeUnit.MILLISECONDS.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池"+Thread.currentThread().getName()+",paymentInfo_TimeOut_ID:"+id;
    }
}
