package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.entity.Comment;
import com.udacity.course3.reviews.entity.Review;
import com.udacity.course3.reviews.entity.mongo.ReviewMongo;
import com.udacity.course3.reviews.repository.CommentRepository;
import com.udacity.course3.reviews.repository.ReviewMongoRepository;
import com.udacity.course3.reviews.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * Spring REST controller for working with comment entity.
 */
@RestController
@RequestMapping("/comments")
public class CommentsController {

    // TODO: Wire needed JPA repositories here
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ReviewMongoRepository reviewMongoRepository;

    /**
     * Creates a comment for a review.
     * <p>
     * 1. Add argument for comment entity. Use {@link RequestBody} annotation.
     * 2. Check for existence of review.
     * 3. If review not found, return NOT_FOUND.
     * 4. If found, save comment.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.POST)
    public ResponseEntity<Comment> createCommentForReview(@PathVariable("reviewId") Integer reviewId,
                                                          @RequestBody @NotNull Comment comment) {
        Optional<ReviewMongo> optional = reviewMongoRepository.findById(reviewId);
        if(optional.isPresent()) {
            ReviewMongo reviewMongo = optional.get();
            reviewMongo.addComments(comment);
            reviewMongoRepository.save(reviewMongo);
        } else {
            return ResponseEntity.notFound().build();
        }

        Optional<Review> review = reviewRepository.findById(reviewId);
        if (review.isPresent()) {
            comment.setReview(review.get());
            commentRepository.save(comment);
        } else {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }

    /**
     * List comments for a review.
     * <p>
     * 2. Check for existence of review.
     * 3. If review not found, return NOT_FOUND.
     * 4. If found, return list of comments.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.GET)
    public List<Comment> listCommentsForReview(@PathVariable("reviewId") Integer reviewId) {
//        Review review = new Review();
//        review.setId(reviewId);
//
//        return commentRepository.findAllByReview(review);
        List<Comment> comments = new ArrayList<>();
        Optional<ReviewMongo> review = reviewMongoRepository.findById(reviewId);
        if (review.isPresent()) {
            comments.addAll(review.get().getComments());
        }

        return comments;
    }
}
