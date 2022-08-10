package com.example.board.repository;

import com.example.board.model.Board;
import com.example.common.dto.PageRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardRepository {
    List<Board> getBoardList(PageRequest pageRequest);
    int getTotalList();
    boolean isExist(long boardNo);
}
