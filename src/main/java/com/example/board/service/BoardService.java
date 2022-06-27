package com.example.board.service;

import com.example.board.dto.Board;
import com.example.common.dto.PageRequest;

public interface BoardService {
    Board getBoardList(PageRequest pageRequest);
}
