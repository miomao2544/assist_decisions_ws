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
@Table(name="post")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Post {

    @Id
    @Column(name = "postId",nullable = false,length = 20)
    private String postId;

    @Column(name="postImage",nullable = false,length = 30)
    private String postImage;

    @Column(name="title",nullable = false,length = 50)
    private String title;

    @Lob
    @Column(name="description",length = 1000,columnDefinition="LONGTEXT")
    private String description;

    @Column(name="postPoint",nullable = false,length = 4)
    private Double postPoint;

    @Column(name="dateStart",nullable = false)
    private Date dateStart;

    @Column(name="dateStop",nullable = false)
    private Date dateStop;

    @Column(name="result",nullable = false,length = 1)
    private String result;

    @Column(name="qtyMax",nullable = false,length = 4)
    private int qtyMax;

    @Column(name="qtyMin",nullable = false,length = 4)
    private int qtyMin;

    @Column(name="avgPoint",nullable = false,length = 4)
    private Double avgPoint;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "username",nullable = false)
    private Member member;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "interestId",nullable = false)
    private Interest interest;

}
