package com.example.auth.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class User {
    @NotEmpty
    String id;
    @NotEmpty
    String password;
    @NotEmpty
    String name;
}
