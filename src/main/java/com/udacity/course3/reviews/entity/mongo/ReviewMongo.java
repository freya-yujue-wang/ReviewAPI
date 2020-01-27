package com.udacity.course3.reviews.entity.mongo;

import com.udacity.course3.reviews.entity.Comment;
import com.udacity.course3.reviews.entity.Review;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document("Reviews")
public class ReviewMongo {

    @Id
    private int id;
    private String title;
    private String content;

    private List<Comment> comments = new ArrayList<>();

    public ReviewMongo() {

    }

    public ReviewMongo(int id, String title, String content, List<Comment> comments) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.comments = comments;
    }

    public ReviewMongo(Review review) {
        this.id = review.getId();
        this.title = review.getTitle();
        this.content = review.getContent();
        this.comments = review.getComments();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void addComments(Comment comment){
        this.comments.add(comment);
    }

    @Override
    public String toString() {
        return "ReviewMongo{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", comments=" + comments +
                '}';
    }
}
