package com.udacity.course3.reviews.repository;

import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.entity.Review;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ReviewRepositoryTest {

    @Autowired
    private DataSource _dataSource;
    @Autowired
    private JdbcTemplate _jdbcTemplate;
    @Autowired
    private TestEntityManager _entityManager;
    @Autowired
    private ReviewRepository _reviewRepository;

    @Test
    public void injectedComponentAreNotNull() {
        assertThat(_dataSource).isNotNull();
        assertThat(_jdbcTemplate).isNotNull();
        assertThat(_entityManager).isNotNull();
        assertThat(_reviewRepository).isNotNull();
    }

    @Test
    public void findAllByProductName() {
        Product product = new Product();
        product.setProductName("phone");
        product.setDescription("brand new one");
        _entityManager.persist(product);

        Review review1 = new Review();
        review1.setTitle("review1");
        review1.setContent("very good1");
        review1.setProduct(product);
        _entityManager.persist(review1);

        Review review2 = new Review();
        review2.setTitle("review2");
        review2.setContent("very good2");
        review2.setProduct(product);
        _entityManager.persist(review2);

        List<Review> allReviews = _reviewRepository.findAllByProductName(product.getProductName());
        assertEquals(allReviews.size(), 2);
        assertEquals(allReviews.get(0).getTitle(), "review1");
        assertEquals(allReviews.get(0).getContent(), "very good1");

    }

    @Test
    public void findAllByProduct() {
        Product product = new Product();
        product.setProductName("phone");
        product.setDescription("brand new one");
        _entityManager.persist(product);

        Review review1 = new Review();
        review1.setTitle("review1");
        review1.setContent("very good1");
        review1.setProduct(product);
        _entityManager.persist(review1);

        Review review2 = new Review();
        review2.setTitle("review2");
        review2.setContent("very good2");
        review2.setProduct(product);
        _entityManager.persist(review2);

        List<Review> allReviews = _reviewRepository.findAllByProduct(product);
        assertEquals(allReviews.size(), 2);
        assertEquals(allReviews.get(0).getTitle(), "review1");
        assertEquals(allReviews.get(0).getContent(), "very good1");
    }
}
