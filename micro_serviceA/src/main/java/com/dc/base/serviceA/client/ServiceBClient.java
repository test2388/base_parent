package com.dc.base.serviceA.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "micro-serviceB", fallback = ServiceBClient.ServiceBClientFallback.class)
public interface ServiceBClient {

	@RequestMapping("/")
    String printServiceB();

    @Component
    class ServiceBClientFallback implements ServiceBClient {

        private static final Logger LOGGER = LoggerFactory.getLogger(ServiceBClientFallback.class);

        @Override
        public String printServiceB() {
            LOGGER.info("异常发生，进入fallback方法");
            return "SERVICE B FAILED! - FALLING BACK";
        }
    }
}