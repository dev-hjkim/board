package com.example.auth.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Member {
    @JsonIgnore
    String memberNo;
    String userId;
    @JsonIgnore
    String password;
    String accessToken;
    String refreshToken;
    Date regDt;
    Date updDt;

    public Member(String id, String pwd) {
        this.userId = id;
        this.password = pwd;
    }
}
