package com.vikash.application_ms.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("companyms")
public interface CompanyClient {
    @GetMapping("/isvalid/{id}")
    public boolean isCompanyWithIdValid(@PathVariable Long id);
}
