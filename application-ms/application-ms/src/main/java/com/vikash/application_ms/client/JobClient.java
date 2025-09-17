package com.vikash.application_ms.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("jobms")
public interface JobClient {
    @GetMapping("/job/isvalid/{id}")
    boolean isJobWithIdValid(@PathVariable("id") Long id);
}
