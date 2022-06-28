package com.example.board.service;

import com.example.board.dto.BoardList;
import com.example.board.model.Board;

public interface BoardService {
    BoardList getBoardList(Board board);
}
