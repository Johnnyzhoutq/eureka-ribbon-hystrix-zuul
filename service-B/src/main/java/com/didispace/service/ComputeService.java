package com.didispace.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ComputeService {
	private final Logger logger = Logger.getLogger(getClass());
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "addServiceFallback")
    public String addService() {
    	logger.info("/add, host:3333");
        return restTemplate.getForEntity("http://service-A/add?a=10&b=20", String.class).getBody();
    }

    public String addServiceFallback() {
        return "error";
    }

}
