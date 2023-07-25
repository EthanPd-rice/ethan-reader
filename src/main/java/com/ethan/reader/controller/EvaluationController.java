package com.ethan.reader.controller;

import com.ethan.reader.service.EvaluationService;
import com.ethan.reader.utils.ResponseUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/evaluation")
public class EvaluationController {
    @Resource
    private EvaluationService evaluationService;
    @GetMapping("/list")
    public ResponseUtils list(Long bookId){
        ResponseUtils responseUtils = null;
        try{
            List<Map> maps = evaluationService.selectByBookId(bookId);
            responseUtils = new ResponseUtils().put("list",maps);
        }catch (Exception e){
            e.printStackTrace();
            responseUtils = new ResponseUtils(e.getClass().getSimpleName(),e.getMessage());
        }
        return responseUtils;
    }

}
