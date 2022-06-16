package com.example.board.service;

import com.example.board.dto.Board;
import com.example.common.dto.Page;

public interface BoardService {
    Board getBoardList(Page page);
}
