package com.example.auth.model;

import com.example.common.model.Timestamp;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Member extends Timestamp {
    @JsonIgnore
    private long memberNo;

    private String userId;

    @JsonIgnore
    private String password;

    @Builder
    public Member(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
}
