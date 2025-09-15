package com.vikash.reviewms.review.service;


import com.vikash.reviewms.review.dto.ReviewWithCompanyDTO;
import com.vikash.reviewms.review.external.Company.Company;
import com.vikash.reviewms.review.model.Review;
import com.vikash.reviewms.review.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService{
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private RestClient restClient;

    @Override
    public String createReview(Review review) {
        Long companyId=review.getCompanyId();
        Company company=restClient.get()
                .uri("http://companyms:8082/company/getCompany/"+companyId)
                .retrieve()
                .body(Company.class);
        if(company==null){
            return "Enter the correct companyId for review";
        }

        try{
            reviewRepository.save(review);
            return "Review saved successfully!";
        } catch (Exception e) {
            return "Review could not be saved!";
        }
    }

    @Override
    public String updateReview(Long id, Review review) {
        ReviewWithCompanyDTO reviewWithCompanyDTOExist=getReviewById(id);

        if(reviewWithCompanyDTOExist==null){
            return "Review does not exist!";
        }else {
         Review reviewExist=reviewWithCompanyDTOExist.getReview();
         reviewExist.setTitle(review.getTitle());
         reviewExist.setDescription(reviewExist.getDescription());
         reviewExist.setCompanyId(review.getCompanyId());
            try{
                reviewRepository.save(reviewExist);
                return "Review updated successfully!";
            }catch (Exception e){
                return "Something went wrong in saving!";
            }
        }
    }

    @Override
    public String deleteReview(Long id) {
        ReviewWithCompanyDTO review=getReviewById(id);
        if(review==null){
            return "Review with this id does not exist!";
        }else {
            reviewRepository.deleteById(id);
            return "Review deleted successfully!";
        }
    }

    @Override
    public List<Review> getAllReviewOfCompany(Long companyId) {
        return reviewRepository.findAllByCompanyId(companyId);
    }

    @Override
    public ReviewWithCompanyDTO getReviewById(Long reviewId) {

        Optional<Review> reviewOptional=reviewRepository.findById(reviewId);
        if(reviewOptional.isPresent()){
            Long companyId=reviewOptional.get().getCompanyId();
            Company company=restClient.get()
                    .uri("http://companyms:8082/company/getCompany/"+companyId)
                    .retrieve()
                    .body(Company.class);
            ReviewWithCompanyDTO reviewWithCompanyDTO=new ReviewWithCompanyDTO();
            reviewWithCompanyDTO.setReview(reviewOptional.get());
            reviewWithCompanyDTO.setCompany(company);
            return reviewWithCompanyDTO;
        }
        return null;
    }
}
