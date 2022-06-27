package com.example.board.service.impl;

import com.example.board.dto.Board;
import com.example.board.repository.BoardRepository;
import com.example.board.service.BoardService;
import com.example.common.dto.PageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public Board getBoardList(PageRequest pageRequest) {
        int totalCount = boardRepository.getTotalList();
        List<String> boardList = boardRepository.getBoardList(pageRequest);
        return new Board(pageRequest.getPageSize(), totalCount, boardList);
    }
}
