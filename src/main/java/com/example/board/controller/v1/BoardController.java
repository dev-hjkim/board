package com.example.board.controller.v1;

import com.example.board.service.BoardService;
import com.example.common.dto.Result;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value="/v1/board")
@RequiredArgsConstructor
public class BoardController {
    final Logger logger = LoggerFactory.getLogger(BoardController.class);

    private final BoardService boardService;

    @GetMapping(value="/lists")
    public Result getBoardList(@RequestHeader String userSeq) {
        logger.info("getBoardList ::: userSeq {}", userSeq);

        return null;
    }

    @GetMapping(value="/interceptor/lists")
    public Result getBoardListWithInterceptor(@RequestAttribute String userSeq) {
        logger.info("getBoardListWithInterceptor ::: userSeq {}", userSeq);

        return null;
    }
}
