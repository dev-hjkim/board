package com.example.board.controller.v1;

import com.example.board.service.BoardService;
import com.example.common.dto.Result;
import com.example.common.dto.ResultType;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value="/v1/board")
@RequiredArgsConstructor
public class BoardController {
    final Logger logger = LoggerFactory.getLogger(BoardController.class);

    private final BoardService boardService;

    @GetMapping(value="/lists")
    public ResponseEntity<Object> getBoardList(@RequestHeader String userSeq) {
        logger.debug("getBoardList ::: userSeq {}", userSeq);

        Result result;
        result = new Result(ResultType.OK, null);
        return new ResponseEntity<>(result, result.parseHttpCode());
    }

    @GetMapping(value="/interceptor/lists")
    public ResponseEntity<Object> getBoardListWithInterceptor(@RequestAttribute String userSeq) {
        logger.debug("getBoardListWithInterceptor ::: userSeq {}", userSeq);

        Result result;
        result = new Result(ResultType.OK, null);
        return new ResponseEntity<>(result, result.parseHttpCode());
    }
}
