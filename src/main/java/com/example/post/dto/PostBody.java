package com.example.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PostBody {
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;
}
