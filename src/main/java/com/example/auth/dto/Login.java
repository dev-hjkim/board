package com.example.auth.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class Login {
    @NotEmpty
    String id;
    @NotEmpty
    String password;
}
