package com.vikash.user_ms.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "recruiter")
public class Recruiter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long authUserId; // same id from Auth-Service
    private String name;
    private String companyName;

}
