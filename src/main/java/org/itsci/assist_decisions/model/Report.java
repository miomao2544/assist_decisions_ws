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
@Table(name="report")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Report {

    @Id
    @Column(name = "reportId",nullable = false,length = 20)
    private String reportId;

    @Column(name="reportDate",nullable = false)
    private Date reportDate;

    @Lob
    @Column(name="reportComment",length = 1000,columnDefinition="LONGTEXT")
    private String reportComment;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "postId",nullable = false)
    private Post post;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "username",nullable = false)
    private Member member;
}
