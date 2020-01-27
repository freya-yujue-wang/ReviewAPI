package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.entity.Review;
import com.udacity.course3.reviews.entity.mongo.ReviewMongo;
import com.udacity.course3.reviews.repository.ProductRepository;
import com.udacity.course3.reviews.repository.ReviewMongoRepository;
import com.udacity.course3.reviews.repository.ReviewRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * Spring REST controller for working with review entity.
 */
@RestController
public class ReviewsController {

    // TODO: Wire JPA repositories here
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ReviewMongoRepository reviewMongoRepository;

    /**
     * Creates a review for a product.
     * <p>
     * 1. Add argument for review entity. Use {@link RequestBody} annotation.
     * 2. Check for existence of product.
     * 3. If product not found, return NOT_FOUND.
     * 4. If found, save review.
     *
     * @param productId The id of the product.
     * @return The created review or 404 if product id is not found.
     */
    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.POST)
    public ResponseEntity<Review> createReviewForProduct(@PathVariable("productId") Integer productId,
                                                         @RequestBody @NotNull Review review) {
        Optional<Product> optional = productRepository.findById(productId);
        if (optional.isPresent()) {
            review.setProduct(optional.get());
            Review newReview = reviewRepository.save(review);
            ReviewMongo reviewMongo = new ReviewMongo(newReview);
            reviewMongoRepository.save(reviewMongo);

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Lists reviews by product.
     *
     * @param productId The id of the product.
     * @return The list of reviews.
     */
    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.GET)
    public ResponseEntity<List<ReviewMongo>> listReviewsForProduct(@PathVariable("productId") Integer productId) {
        Product product = new Product();
        product.setId(productId);
        List<Review> allReviews = reviewRepository.findAllByProduct(product);
        List<ReviewMongo> mongoReviews = new ArrayList<>();
        for (Review review: allReviews) {
            Optional<ReviewMongo> reviewMongo = reviewMongoRepository.findById(review.getId());
            if (reviewMongo.isPresent()){
                mongoReviews.add(reviewMongo.get());
            }
        }
        return ResponseEntity.ok(mongoReviews);
    }

    /**
     * List Reviews by product name
     */
    @GetMapping("/reviews/{name}")
    public List<Review> listReviewsByProductId(@PathVariable("name") String name) {
        List<Review> allReviews = reviewRepository.findAllByProductName(name);
        return allReviews;
    }

    /**
     * List Reviews by comments number
     */
    @GetMapping("/reviews/commentsNum/{num}")
    public List<ReviewMongo> listReviewsByCommentsNumber(@PathVariable("num") int num) {
        List<ReviewMongo> allReviews = reviewMongoRepository.findReviewsCommentsNumEquals(num);
        return allReviews;
    }

}
