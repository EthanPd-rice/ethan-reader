package com.ethan.reader.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ethan.reader.entity.Evaluation;

import java.util.List;
import java.util.Map;

public interface EvaluationMapper extends BaseMapper<Evaluation> {
    public List<Map> selectByBookId(Long bookId);
}
