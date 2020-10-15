package com.river.springcloud.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="5000")
    })
    public String paymentInfo_TimeOut(Integer id) {
        try{
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //int a = 10/0;
        return "线程池"+Thread.currentThread().getName()+",接口8001,paymentInfo_TimeOut_ID:"+id;
    }

    @Override
    public String paymentInfo_TimeoutHandler(Integer id) {
        return "调用8001支付接口超时或异常";
    }
}
