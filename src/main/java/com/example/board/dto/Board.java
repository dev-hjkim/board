package com.example.board.dto;

import com.example.common.dto.Page;
import com.example.common.dto.Pagenation;

import java.util.List;

public class Board extends Pagenation<String> {
    public Board(Page page, List<String> list) {
        super(page, list);
    }
}
