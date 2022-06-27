package com.example.board.repository;

import com.example.common.dto.PageRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardRepository {
    List<String> getBoardList(PageRequest pageRequest);
    int getTotalList();
}
