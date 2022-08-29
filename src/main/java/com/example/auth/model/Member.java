package com.example.auth.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_member")
@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Member {
    @Id
    @Column(name = "member_no")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private long memberNo;

    @Column(name = "user_id")
    private String userId;

    @JsonIgnore
    @Column(name = "password")
    private String password;

    @Column(name = "reg_dt")
    private Date regDt;
    @Column(name = "upd_dt")
    private Date updDt;

    @Builder
    public Member(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
}
