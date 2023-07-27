package com.ethan.reader.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ethan.reader.entity.Book;

import java.util.Map;

public interface BookMapper extends BaseMapper<Book> {
    public void updateScore();
    //自定义查询方法时，返回值必须要为IPage,传入参数也要为IPage,MyBatis才会为你分类
    public IPage<Map> selectBookMap(IPage page);
}
