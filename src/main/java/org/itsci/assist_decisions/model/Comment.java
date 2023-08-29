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
@Table(name="comment")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Comment {
    @Id
    @Column(name = "commentId",nullable = false,length = 20)
    private String commentId;

    @Lob
    @Column(name="comment",nullable = false,length = 1000,columnDefinition="LONGTEXT")
    private String comment;
    @Column(name="commentDate",nullable = false)
    private Date commentDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "username",nullable = false)
    private Member member;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "postId",nullable = false)
    private Post post;
}
