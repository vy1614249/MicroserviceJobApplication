package com.vikash.user_ms.repository;

import com.vikash.user_ms.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill,Long> {
    List<Skill> findByUserId(Long userId);
}
