package com.example.board.dto;

import com.example.common.dto.PageList;

import java.util.List;

public class Board extends PageList<String> {
    public Board(int pageSize, int totalCount, List<String> list) {
        super(pageSize, totalCount, list);
    }
}
