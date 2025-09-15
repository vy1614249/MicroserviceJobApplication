package com.vikash.jobms.job.client;

import com.vikash.jobms.job.external.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient("companyms")
public interface CompanyClient {

    @GetMapping("/company/getCompany/{id}")
    Optional<Company> getCompanyById(@PathVariable("id") Long id);

    @GetMapping("/company/getCompanyByName/{name}")
    Company getCompanyByName(@PathVariable("name") String name);
}
