package com.example.auth.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Date;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Member {
    @JsonIgnore
    private long memberNo;

    private String userId;

    @JsonIgnore
    private String password;

    private Date regDt;
    private Date updDt;

    @Builder
    public Member(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
}
