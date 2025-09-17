package com.vikash.companyms.company.service;



import com.vikash.companyms.company.model.Company;

import java.util.List;

public interface CompanyService {
    public String createCompany(Company company);
    public String updateCompany(Long id, Company company);
    public Company getCompanyById(Long id);
    public Company searchCompanyByName(String name);
    public String deleteById(Long id);
    public List<Company> getAll();

    boolean isCompanyWithIdValid(Long id);
}
