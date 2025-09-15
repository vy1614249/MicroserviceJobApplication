package com.vikash.reviewms.review.controller;


import com.vikash.reviewms.review.dto.ReviewWithCompanyDTO;
import com.vikash.reviewms.review.model.Review;
import com.vikash.reviewms.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping("/create")
    public String createReview(@RequestBody Review review){
        return reviewService.createReview(review);
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<Object> getReviewById(@PathVariable Long id){
        ReviewWithCompanyDTO review=reviewService.getReviewById(id);
        if(review!=null){
            return new ResponseEntity<>(review, HttpStatus.FOUND);
        }else {
            return new ResponseEntity<>("Review with this id does not exist!",HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateById/{id}")
    public String updateReviewById(@PathVariable Long id,@RequestBody Review review){
        return reviewService.updateReview(id,review);
    }

    @DeleteMapping("/deleteById/{id}")
    public String deleteById(@PathVariable Long id){
        return reviewService.deleteReview(id);
    }

}
