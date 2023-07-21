package com.ethan.reader.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ethan.reader.entity.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class BookServiceTest {
    @Resource
    private BookService bookService;
    /**
     * 查询1号分类前10条数据,按热度排序
     */
    @Test
    public void selectPage1() {
        IPage<Book> page = bookService.selectPage(1l, "quantity", 1, 10);
        System.out.println("总页数:" + page.getPages());
        System.out.println("记录总数:" + page.getTotal());
        for(Book book : page.getRecords()){
            System.out.println(book.getCategoryId() + "-" + book.getBookName()
                    + "-" + book.getEvaluationQuantity() + "-" + book.getEvaluationScore());
        }
    }
    /**
     * 查询所有图书前10条数据,按分数排序
     */
    @Test
    public void selectPage2() {
        IPage<Book> page = bookService.selectPage(null, "score", 1, 10);
        System.out.println("总页数:" + page.getPages());
        System.out.println("记录总数:" + page.getTotal());
        for(Book book : page.getRecords()){
            System.out.println(book.getCategoryId() + "-" + book.getBookName()
                    + "-" + book.getEvaluationQuantity() + "-" + book.getEvaluationScore());
        }
    }
    /**
     * 查询所有图书第3页数据,按热度排序
     */
    @Test
    public void selectPage3() {
        IPage<Book> page = bookService.selectPage(null, "quantity", 3, 10);
        System.out.println("总页数:" + page.getPages());
        System.out.println("记录总数:" + page.getTotal());
        for(Book book : page.getRecords()){
            System.out.println(book.getCategoryId() + "-" + book.getBookName()
                    + "-" + book.getEvaluationQuantity() + "-" + book.getEvaluationScore());
        }
    }
}