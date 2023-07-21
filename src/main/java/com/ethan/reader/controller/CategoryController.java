package com.ethan.reader.controller;

import com.ethan.reader.entity.Category;
import com.ethan.reader.service.CategoryService;
import com.ethan.reader.utils.ResponseUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Resource
    private CategoryService categoryService;
    @GetMapping("/list")
    public ResponseUtils list(){
        ResponseUtils responseUtils = null;
        try{
            List<Category> categories= categoryService.selectAll();
            responseUtils = new ResponseUtils().put("list",categories);
        }catch (Exception e){
            e.printStackTrace();
            responseUtils = new ResponseUtils(e.getClass().getSimpleName(),e.getMessage());
        }
        return responseUtils;
    }
}