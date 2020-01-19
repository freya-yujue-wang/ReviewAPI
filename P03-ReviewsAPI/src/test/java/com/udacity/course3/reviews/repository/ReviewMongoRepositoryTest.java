package com.udacity.course3.reviews.repository;

import com.udacity.course3.reviews.entity.Comment;
import com.udacity.course3.reviews.entity.Review;
import com.udacity.course3.reviews.entity.mongo.ReviewMongo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
@ExtendWith(SpringExtension.class)
class ReviewMongoRepositoryTest {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private ReviewMongoRepository reviewMongoRepository;

    @Test
    public void saveReview() {
        Review review  = new Review();
        review.setId(1);
        review.setTitle("testTitle");
        review.setContent("testContent");

        List<Comment> comments = new ArrayList<>();
        int id = 1;
        for (int i = 0; i < 3; i++) {
            comments.add(new Comment(id, "test", "test"));
            id++;
        }
        review.setComments(comments);

        mongoTemplate.save(review, "Reviews");

        assertNotEquals(reviewMongoRepository.findAll().size(), 0);
        assertEquals(review.getTitle(), reviewMongoRepository.findById(1).get().getTitle());
        assertEquals(review.getContent(), reviewMongoRepository.findById(1).get().getContent());
        assertEquals(review.getId(), reviewMongoRepository.findById(1).get().getId());
        assertEquals(review.getComments(), reviewMongoRepository.findById(1).get().getComments());
    }

    @Test
    public void findReviewsCommentsNumEquals() {
        Review review  = new Review();
        review.setId(1);
        review.setTitle("testTitle");
        review.setContent("testContent");

        List<Comment> comments = new ArrayList<>();
        int id = 1;
        for (int i = 0; i < 3; i++) {
            comments.add(new Comment(id, "test", "test"));
            id++;
        }
        review.setComments(comments);
        mongoTemplate.save(review, "Reviews");

        List<ReviewMongo> reviewMongoList = reviewMongoRepository.findReviewsCommentsNumEquals(3);

        assertNotEquals(reviewMongoRepository.findAll().size(), 0);
        assertEquals(review.getTitle(), reviewMongoList.get(0).getTitle());
        assertEquals(review.getContent(), reviewMongoList.get(0).getContent());
        assertEquals(review.getId(), reviewMongoList.get(0).getId());
        assertEquals(review.getComments(), reviewMongoList.get(0).getComments());
    }


}
