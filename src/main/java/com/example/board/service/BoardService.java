package com.example.board.service;

import com.example.board.model.Board;
import com.example.common.dto.PageList;

public interface BoardService {
    PageList<Board> getBoardList(Board board);
}
