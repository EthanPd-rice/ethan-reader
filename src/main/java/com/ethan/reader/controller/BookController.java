package com.ethan.reader.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ethan.reader.entity.Book;
import com.ethan.reader.service.BookService;
import com.ethan.reader.utils.ResponseUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/book")
public class BookController {
    @Resource
    private BookService bookService;
    @GetMapping("/list")
    public ResponseUtils list(Long categoryId,String order,Integer page,Integer rows){
        ResponseUtils responseUtils = null;
        try{
            IPage<Book> p = bookService.selectPage(categoryId,order,page,10);
            responseUtils = new ResponseUtils().put("page",p);
        }catch (Exception e){
            e.printStackTrace();
            responseUtils = new ResponseUtils(e.getClass().getSimpleName(),e.getMessage());
        }
        return responseUtils;
    }

    @GetMapping("/id/{id}")
    public ResponseUtils selectById(@PathVariable("id") Long id){

        ResponseUtils responseUtils = null;
        try{
            Book book = bookService.selectById(id);
            responseUtils = new ResponseUtils().put("book",book);
        }catch(Exception e){
            e.printStackTrace();
            responseUtils =new ResponseUtils(e.getClass().getSimpleName(),e.getMessage());
        }
        return responseUtils;
    }
}
