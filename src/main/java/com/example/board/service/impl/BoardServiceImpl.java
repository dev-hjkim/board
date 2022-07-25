package com.example.board.service.impl;

import com.example.board.dto.BoardList;
import com.example.board.model.Board;
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
    public BoardList getBoardList(Board board) {
        int totalCount = boardRepository.getTotalList();
        List<Board> boardList = boardRepository.getBoardList(board);
        return new BoardList(board.getPageSize(), totalCount, boardList);
    }
}
