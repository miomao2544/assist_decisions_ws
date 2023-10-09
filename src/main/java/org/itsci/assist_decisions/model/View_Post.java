package org.itsci.assist_decisions.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="view_post")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class View_Post {

    @Id
    @Column(name = "viewPostId",nullable = false,length = 20)
    private String viewPostId;

    @Column(name="viewPost",nullable = false)
    private Date viewPost;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "postId",nullable = false)
    private Post post;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "username",nullable = false)
    private Member member;
}
