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
@Table(name="vote")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Vote {

    @Id
    @Column(name = "voteId",nullable = false,length = 20)
    private String voteId;

    @Column(name="voteDate",nullable = false)
    private Date voteDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username",nullable = false)
    private Member member;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name= "choiceId",nullable = false)
    private Choice choice;

}
