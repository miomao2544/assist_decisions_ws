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
@Table(name="ban_type")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Ban_Type {
    @Id
    @Column(name = "banTypeId",nullable = false,length = 20)
    private String banTypeId;

    @Column(name="typeName",nullable = false,length = 50)
    private String typeName;

    @Column(name="numberOfDay",nullable = false,length = 6)
    private int numberOfDay;

}
