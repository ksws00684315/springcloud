package com.river.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Order8081Main {
    public static void main(String[] args) {
        SpringApplication.run(Order8081Main.class,args);
    }
}
