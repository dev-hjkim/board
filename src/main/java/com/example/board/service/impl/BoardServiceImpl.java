package com.example.board.service.impl;

import com.example.board.dto.Board;
import com.example.board.repository.BoardRepository;
import com.example.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public Board getBoardList() {
        List<String> boardList = boardRepository.getBoardList();
        return new Board(boardList);
    }
}
