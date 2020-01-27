package com.udacity.course3.reviews;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//with this EnableMongoRepositories annotation, the JPA test cannot run.
//@EnableMongoRepositories
@SpringBootApplication
public class ReviewsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReviewsApplication.class, args);
    }


}
