package com.example.board.controller.v1;

import com.example.board.service.BoardService;
import com.example.common.dto.Result;
import com.example.common.dto.ResultType;
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
        System.out.println("getBoardList Method");
        System.out.println(userSeq);
        return new Result(ResultType.OK, null);
    }

    @GetMapping(value="/interceptor/lists")
    public Result getBoardListWithInterceptor(@RequestAttribute String userSeq) {
        System.out.println("getBoardListWithInterceptor Method");
        System.out.println(userSeq);
        return new Result(ResultType.OK, null);
    }
}
