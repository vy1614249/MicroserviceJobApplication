package com.vikash.user_ms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private String location;
    private String headline;
    private String about;
    //private String resumeUrl;
    private List<SkillDto> skills;
    private List<EducationDto> educationList;
}
