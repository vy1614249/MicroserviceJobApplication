package com.vikash.companyms.company.repository;


import com.vikash.companyms.company.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {
    Optional<Company> findByName(String name);
}
