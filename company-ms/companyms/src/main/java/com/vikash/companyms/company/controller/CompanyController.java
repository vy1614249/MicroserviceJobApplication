package com.vikash.companyms.company.controller;


import com.vikash.companyms.company.model.Company;
import com.vikash.companyms.company.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/create")
    public String createCompany(@RequestBody Company company){
        return companyService.createCompany(company);
    }

    @GetMapping("/getCompany/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id){
        Company company=companyService.getCompanyById(id);
        if(company!=null){
            return new ResponseEntity<>(company, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/updateCompany/{id}")
    public String updateCompanyById(@PathVariable Long id,@RequestBody Company company){
        return companyService.updateCompany(id,company);
    }

    @DeleteMapping("/deleteCompany/{id}")
    public String deleteCompanyById(@PathVariable Long id){
        return companyService.deleteById(id);
    }

    @GetMapping("/getAllCompany")
    public List<Company> getAllCompany(){
        return companyService.getAll();
    }

    @GetMapping("/isvalid/{id}")
    public boolean isCompanyWithIdValid(@PathVariable Long id){
        return companyService.isCompanyWithIdValid(id);
    }
}
