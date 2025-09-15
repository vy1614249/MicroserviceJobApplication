package com.vikash.reviewms.review.dto;

import com.vikash.reviewms.review.external.Company.Company;
import com.vikash.reviewms.review.model.Review;
import lombok.Data;

@Data
public class ReviewWithCompanyDTO {
    private Company company;
    private Review review;
}
