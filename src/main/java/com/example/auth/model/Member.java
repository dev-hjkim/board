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
    String seq;
    String id;
    @JsonIgnore
    String pwd;
    String name;
    String accessToken;
    String refreshToken;
    Date regDtm;
    Date modDtm;

    public Member(String id, String pwd, String name) {
        this.id = id;
        this.pwd = pwd;
        this.name = name;
    }

    public Member(String id, String pwd) {
        this.id = id;
        this.pwd = pwd;
    }
}