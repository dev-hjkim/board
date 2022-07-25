package com.example.board.repository;

import com.example.board.model.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardRepository {
    List<Board> getBoardList(Board board);
    int getTotalList();
}
