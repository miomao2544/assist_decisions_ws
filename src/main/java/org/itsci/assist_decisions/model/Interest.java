package org.itsci.assist_decisions.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="interest")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Interest {

    @Id
    @Column(name = "interestId",nullable = false,length = 20)
    private String interestId;

    @Column(name="interestName",nullable = false,length = 100)
    private String interestName;

    public Interest(String interestName) {
        this.interestName = interestName;
    }
}
