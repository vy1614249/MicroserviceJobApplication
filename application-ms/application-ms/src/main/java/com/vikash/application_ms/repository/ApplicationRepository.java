package com.vikash.application_ms.repository;

import com.vikash.application_ms.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    List<Application> findByUserId(Long userId);
    List<Application> findByJobId(Long jobId);
}

