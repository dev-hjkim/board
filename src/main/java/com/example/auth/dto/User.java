package com.example.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class User {
    @NotEmpty
    String id;
    @NotEmpty
    String password;
    @NotEmpty
    String name;
}
