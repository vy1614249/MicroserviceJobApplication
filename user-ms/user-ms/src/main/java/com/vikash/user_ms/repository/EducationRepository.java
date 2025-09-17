package com.vikash.user_ms.repository;

import com.vikash.user_ms.model.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationRepository extends JpaRepository<Education,Long> {
    List<Education> findByUserId(Long userId);
}
