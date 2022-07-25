package com.example.board.dto;

import com.example.board.model.Board;
import com.example.common.dto.PageList;

import java.util.List;

public class BoardList extends PageList<Board> {
    public BoardList(int pageSize, int totalCount, List<Board> list) {
        super(pageSize, totalCount, list);
    }
}
