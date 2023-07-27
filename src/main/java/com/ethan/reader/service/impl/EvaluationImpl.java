package com.ethan.reader.service.impl;

import com.ethan.reader.entity.Evaluation;
import com.ethan.reader.mapper.EvaluationMapper;
import com.ethan.reader.service.EvaluationService;
import com.ethan.reader.utils.ResponseUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Evaluation evaluate(Long memberId, Long bookId, Integer score, String content) {
        Evaluation evaluation = new Evaluation();
        evaluation.setMemberId(memberId);
        evaluation.setBookId(bookId);
        evaluation.setContent(content);
        evaluation.setScore(score);
        evaluation.setCreateTime(new Date());
        evaluation.setState("enable");//enable代表可用，评论会显示
        evaluation.setEnjoy(0);//点赞数为0
        evaluationMapper.insert(evaluation);
        return evaluation;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Evaluation enjoy(Long evaluationId) {
        Evaluation evaluation = evaluationMapper.selectById(evaluationId);
        evaluation.setEnjoy(evaluation.getEnjoy() + 1);
        evaluationMapper.updateById(evaluation);
        return evaluation;
    }
}
