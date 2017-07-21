package com.dc.base.appA.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "micro_serviceA", fallback = ServiceAClient.ServiceAClientFallback.class)
public interface ServiceAClient {

    @GetMapping(value = "/")
    String printServiceA();

    @Component
    class ServiceAClientFallback implements ServiceAClient {

        private static final Logger LOGGER = LoggerFactory.getLogger(ServiceAClientFallback.class);

        @Override
        public String printServiceA() {
            LOGGER.info("异常发生，进入fallback方法");
            return "SERVICE A FAILED! - FALLING BACK";
        }
    }
}