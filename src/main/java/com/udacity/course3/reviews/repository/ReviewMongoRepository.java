package com.udacity.course3.reviews.repository;

import com.udacity.course3.reviews.entity.mongo.ReviewMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ReviewMongoRepository extends MongoRepository<ReviewMongo, Integer> {

    @Query("{comments : {$size : ?0}}")
    List<ReviewMongo> findReviewsCommentsNumEquals(int num);
}
