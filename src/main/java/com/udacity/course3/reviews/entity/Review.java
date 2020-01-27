package com.udacity.course3.reviews.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String content;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    @JsonIgnore
    private Product product;

    @OneToMany(mappedBy = "review")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Comment> comments;

    public Review(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
