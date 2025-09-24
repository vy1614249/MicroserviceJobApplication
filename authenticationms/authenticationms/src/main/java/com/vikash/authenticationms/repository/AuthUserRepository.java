package com.vikash.authenticationms.repository;

import com.vikash.authenticationms.model.AuthUser;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AuthUserRepository extends JpaRepository<AuthUser,Long> {
    boolean existsByEmail(@NotNull @NotBlank String email);

    Optional<AuthUser> findByEmail(String username);
}
