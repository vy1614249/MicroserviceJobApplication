package com.vikash.companyms.company.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
//    @JsonIgnore
//    @OneToMany(mappedBy = "company",fetch=FetchType.EAGER,cascade = CascadeType.ALL)
//    private List<Job> jobs;

//    @JsonManagedReference
//    @OneToMany(mappedBy = "company",fetch = FetchType.EAGER, orphanRemoval = true)
//    private List<Review> reviews;
}
