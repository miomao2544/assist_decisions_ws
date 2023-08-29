package org.itsci.assist_decisions.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="choice")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Choice {

    @Id
    @Column(name = "choiceId",nullable = false,length = 20)
    private String choiceId;


    @Column(name="choiceName",nullable = false,length = 200)
    private String choiceName;

    @Column(name="choiceImage",nullable = false,length = 30)
    private String choiceImage;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name= "postId",referencedColumnName = "postId",nullable = false)
    private Post post;
}
