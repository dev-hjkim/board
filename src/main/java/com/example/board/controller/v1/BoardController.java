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

    @GetMapping(value="")
    public Result getBoardList(@RequestHeader String userSeq) {
        logger.info("getBoardList ::: {}", userSeq);

        return null;
    }
}
