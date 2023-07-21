package com.ethan.reader.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ethan.reader.entity.Category;
import com.ethan.reader.mapper.CategoryMapper;
import com.ethan.reader.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;
    @Override
    public List<Category> selectAll() {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.orderByAsc("category_id");
        return categoryMapper.selectList(wrapper);
    }
}
