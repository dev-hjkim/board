package com.example.common.view;

import com.example.common.dto.Result;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component("resultView")
public class ResultView extends AbstractView {
    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Result result = (Result) model.get("result");
        response.setStatus(result.getStatus().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    }
}
