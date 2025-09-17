package com.vikash.application_ms.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-ms", url = "http://localhost:8085")
public interface UserClient {

    @GetMapping("/users/isvalid/{id}")
    boolean isUserWithIdValid(@PathVariable("id") Long id);
}

