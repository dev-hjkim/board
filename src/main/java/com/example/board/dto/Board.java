package com.example.board.dto;

import com.example.common.dto.Pagenation;

import java.util.List;

public class Board extends Pagenation<String> {
    public Board(int pageSize, int totalCount, List<String> list) {
        super(pageSize, totalCount, list);
    }
}
