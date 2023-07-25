package com.ethan.reader.service.impl;

import com.ethan.reader.mapper.EvaluationMapper;
import com.ethan.reader.service.EvaluationService;
import com.ethan.reader.utils.ResponseUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
public class EvaluationImpl implements EvaluationService {
    @Resource
    private EvaluationMapper evaluationMapper;
    ResponseUtils responseUtils = null;
    @Override
    public List<Map> selectByBookId(Long bookId) {
        return evaluationMapper.selectByBookId(bookId);
    }
}
