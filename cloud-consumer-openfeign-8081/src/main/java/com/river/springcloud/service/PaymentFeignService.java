package com.river.springcloud.service;

import com.river.springcloud.entities.CommonResult;
import com.river.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-HYSTRIX-PAYMENT-SERVICE",fallback = PaymentFeiginFallBackService.class)
public interface PaymentFeignService {
    /*@GetMapping("/payment/hystrix/timeout/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id);*/

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id);

}
