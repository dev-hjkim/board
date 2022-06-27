package com.example.auth.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Member {
    @JsonIgnore
    private String memberNo;
    private String userId;
    @JsonIgnore
    private String password;
    private Date regDt;
    private Date updDt;

    public Member(String id, String pwd) {
        this.userId = id;
        this.password = pwd;
    }

    public Member(String memberNo) {
        this.memberNo = memberNo;
    }
}
