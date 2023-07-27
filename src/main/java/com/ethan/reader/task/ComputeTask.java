package com.ethan.reader.task;


import com.ethan.reader.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ComputeTask {
    Logger logger = LoggerFactory.getLogger(ComputeTask.class);
    @Resource
    private BookService bookService;
    @Scheduled(cron = "0 * * * * ?")
    public void updateScore(){
        bookService.updateScore();
        logger.info("已更新所有的图书评分");
    }
}
