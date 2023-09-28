package org.itsci.assist_decisions.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="history_ban")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class History_Ban {

    @Id
    @Column(name = "historyId",nullable = false,length = 20)
    private String historyId;

    @Column(name="banComment",length = 1000,columnDefinition="LONGTEXT")
    private String banComment;

    @Column(name="banDate",nullable = false)
    private Date banDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "banTypeId",nullable = false)
    private Ban_Type banType;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "username",nullable = false)
    private Member member;
}
