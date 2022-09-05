package com.example.board.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Date;

@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Board {
    private String boardNo;
    private String name;

    private Date regDt;
    private Date updDt;
}
