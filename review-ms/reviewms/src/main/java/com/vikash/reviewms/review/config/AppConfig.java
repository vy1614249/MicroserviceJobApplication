package com.vikash.reviewms.review.config;

import org.modelmapper.ModelMapper;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class AppConfig {

    @LoadBalanced
    @Bean
    public RestClient restClient(){
        return RestClient.create();
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }


}
