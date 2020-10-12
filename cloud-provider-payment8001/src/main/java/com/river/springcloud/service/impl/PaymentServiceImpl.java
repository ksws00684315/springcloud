package com.river.springcloud.service.impl;

import com.river.springcloud.dao.PaymentDao;
import com.river.springcloud.service.PaymentService;
import com.river.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentDao paymentDao;

    public int create(Payment payment) {
        return this.paymentDao.create(payment);
    };

    public Payment getPaymentById(@Param("id") Long id) {
        return this.paymentDao.getPaymentById(id);
    };
}
