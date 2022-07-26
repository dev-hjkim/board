package com.example.board.controller.v1;

import com.example.board.model.Board;
import com.example.board.service.BoardService;
import com.example.common.dto.PageList;
import com.example.common.dto.PageRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value="/v1/boards")
@RequiredArgsConstructor
public class BoardController {
    final Logger logger = LoggerFactory.getLogger(BoardController.class);

    private final BoardService boardService;

    /**
     * 게시판 목록 조회
     *
     * @author hjkim
     * @param  request-pageIndex(nullable), pageSize(nullable)
     * @return Board-totalCount, totalPage, list
     */
    @GetMapping(value="")
    public PageList<Board> getBoardList(PageRequest request) {
        logger.info("getBoardList ::: {}", request);

        Board board = Board.builder()
                .startPage(request.getStartPage())
                .pageSize(request.getPageSize())
                .build();
        return boardService.getBoardList(board);
    }
}
