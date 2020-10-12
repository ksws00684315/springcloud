package com.river.springcloud.controller;

import com.river.springcloud.entities.CommonResult;
import com.river.springcloud.entities.Payment;
import com.river.springcloud.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PaymentController {

    Logger log = LoggerFactory.getLogger(PaymentController.class);

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("******插入结果："+result+",端口为："+serverPort);

        if(result > 0) {
            return new CommonResult(200,"插入数据库成功",result);
        } else {
            return new CommonResult(444,"插入数据库失败",null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {

        Payment payment = paymentService.getPaymentById(id);
        log.info("******查询成功,端口为："+serverPort);

        if(payment !=null) {
            return new CommonResult(200,"查询成功，端口号为:"+serverPort,payment);
        } else {
            return new CommonResult(444,"没有对应ID的记录"+id,null);
        }
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB(){
        return  serverPort;
    }

    @GetMapping(value = "/payment/discovery")
    public Object discovery(){
        //获取已经注册的服务名称
        List<String> services = discoveryClient.getServices();
        for(String element : services){
            log.info("*******element:"+element);
        }

        //获取某个服务的所有实例
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for(ServiceInstance instance : serviceInstances){
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }

        return this.discoveryClient;
    }

}
