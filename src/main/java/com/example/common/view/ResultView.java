package com.example.common.view;

import com.example.common.dto.Result;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component("resultView")
public class ResultView extends MappingJackson2JsonView {
    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        super.render(model, request, response);

        Result result = (Result) model.get("result");
        response.setStatus(result.getStatus().value());
    }
}
