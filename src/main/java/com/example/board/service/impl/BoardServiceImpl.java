package com.example.board.service.impl;

import com.example.board.dto.Board;
import com.example.board.repository.BoardRepository;
import com.example.board.service.BoardService;
import com.example.common.dto.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public Board getBoardList(Page page) {
        List<String> boardList = boardRepository.getBoardList(page);
        return new Board(page, boardList);
    }
}
