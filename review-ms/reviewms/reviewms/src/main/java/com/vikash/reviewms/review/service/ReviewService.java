package com.vikash.reviewms.review.service;



import com.vikash.reviewms.review.dto.ReviewWithCompanyDTO;
import com.vikash.reviewms.review.model.Review;

import java.util.List;

public interface ReviewService {

    public String createReview(Review review);
    public String updateReview(Long id,Review review);
    public String deleteReview(Long id);
    public List<Review> getAllReviewOfCompany(Long companyId);
    public ReviewWithCompanyDTO getReviewById(Long reviewId);
}
