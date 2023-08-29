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
@Table(name="member")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Member {

    @Id
    @Column(name = "username",nullable = false,length = 12)
    private String username;

    @Column(name="password",nullable = false,length = 255)
    private String password;

    @Column(name="nickname",nullable = false,length = 100)
    private String nickname;

    @Column(name="gender",nullable = false,length = 1)
    private String gender;

    @Column(name="firstname",nullable = false,length = 100)
    private String firstname;

    @Column(name="lastname",nullable = false,length = 100)
    private String lastname;

    @Column(name="email",nullable = false,length = 60)
    private String email;

    @Column(name="tel",nullable = false,length = 13)
    private String tel;

    @Column(name="image",nullable = false,length = 30)
    private String image;

    @Column(name="status",nullable = false,length = 20)
    private String status;

    @Column(name="point",nullable = false,length = 10)
    private Integer point;

    @Column(name="adminstatus",nullable = false,length = 1)
    private Boolean adminstatus;

    @ManyToMany(targetEntity = Interest.class)
    @JoinTable(name = "memberinterest",
            joinColumns= {@JoinColumn(name = "username",nullable = false)},
            inverseJoinColumns= { @JoinColumn(name = "interestId",nullable = false)})
    private Set<Interest> interests = new HashSet<>();

    public Member(String username, String password, String nickname, String gender, String firstname, String lastname, String email, String tel, String image, String status, Integer point, Boolean adminstatus) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.gender = gender;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.tel = tel;
        this.image = image;
        this.status = status;
        this.point = point;
        this.adminstatus = adminstatus;
    }
}
