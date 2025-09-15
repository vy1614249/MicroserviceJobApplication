package com.vikash.jobms.job.repository;


import com.vikash.jobms.job.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobRepository extends JpaRepository<Job,Long> {
    Optional<Job> findById(Long id);
    List<Job> findByCompanyId(Long companyId);
}
