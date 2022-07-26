package com.example.board.service.impl;

import com.example.board.model.Board;
import com.example.board.repository.BoardRepository;
import com.example.board.service.BoardService;
import com.example.common.dto.PageList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public PageList<Board> getBoardList(Board board) {
        int totalCount = boardRepository.getTotalList();
        List<Board> boardList = boardRepository.getBoardList(board);
        return new PageList<>(board.getPageSize(), totalCount, boardList);
    }
}
