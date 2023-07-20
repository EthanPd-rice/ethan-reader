package com.ethan.reader.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ethan.reader.entity.TestTable;

public interface TestMapper extends BaseMapper<TestTable> {
    public void insertSample();
}
