package com.vikash.companyms.company.service;

import com.vikash.companyms.company.CompanyRepository;
import com.vikash.companyms.company.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService{
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public String createCompany(Company company) {
        String cname=company.getName();
        Optional<Company> companyExist=companyRepository.findByName(cname);
        if(companyExist.isEmpty()){
            companyRepository.save(company);
            return "Company created successfully!";
        }
        return "Company with same name already exist!";
    }

    @Override
    public String updateCompany(Long id, Company company) {
        Company companyExist=getCompanyById(id);
        if(company!=null){
            companyExist.setName(company.getName());
            companyExist.setDescription(company.getDescription());
            companyRepository.save(companyExist);
            return "Company updated successfully!";
        }
        return "Company does not exist!";
    }

    @Override
    public Company getCompanyById(Long id) {
        Optional<Company> company=companyRepository.findById(id);
        return company.orElse(null);
    }

    @Override
    public Company searchCompanyByName(String name) {
        Optional<Company> companyExist=companyRepository.findByName(name);
        return companyExist.orElse(null);
    }

    @Override
    public String deleteById(Long id) {
        Company company=getCompanyById(id);
        if(company!=null){
            companyRepository.deleteById(id);
            return "Company deleted successfully!";
        }
        return "Company does not exist!";
    }

    @Override
    public List<Company> getAll() {
        return companyRepository.findAll();
    }
}
