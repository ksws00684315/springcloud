package com.river.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLB implements LoadBalance{

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    //private final static long maxCount = 2147483647;

    public final int getAndIncrement(){
        int currnet;
        int next;
        do {
            currnet = this.atomicInteger.get();
            //判断计数是否大于Interger最大值，防止越界
            next = currnet >= Integer.MAX_VALUE ? 0 : currnet + 1;
        } while (!this.atomicInteger.compareAndSet(currnet,next));
        return next;
    }

    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        int index = getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
