package com.udacity.course3.reviews.repository;

import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ReviewRepository extends JpaRepository<Review, Integer> {

    @Query("select new Review(id, title, content) from Review where product.productName=:productName")
    List<Review> findAllByProductName(String productName);

    List<Review> findAllByProduct(Product product);
}
