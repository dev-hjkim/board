package com.example.board.model;

import com.example.common.model.Timestamp;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Board extends Timestamp {
    private String boardNo;
    private String name;
}
