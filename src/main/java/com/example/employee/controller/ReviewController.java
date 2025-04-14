package com.example.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee.model.Reviews;
import com.example.employee.service.ReviewService;

@RestController
@RequestMapping( path = "/api/review")
public class ReviewController {
    
    @Autowired
    private ReviewService reviewService;

    @PostMapping("/add")
    public Reviews insert(@RequestBody Reviews reviews){
        return reviewService.savReviews(reviews);
    }

    @GetMapping("/getAll")
    public List<Reviews> getAllReview(){
        return reviewService.getAllReview();
    }
}
