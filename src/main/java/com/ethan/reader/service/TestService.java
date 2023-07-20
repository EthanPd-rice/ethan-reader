package com.ethan.reader.service;

import com.ethan.reader.mapper.TestMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class TestService {
    @Resource
    private TestMapper testMapper;
    @Transactional(rollbackFor = Exception.class)
    public void batchImport(){
        for(int i=0;i<10;i++){
//            if(i==3){
//                throw new RuntimeException("错误");
//            }
            testMapper.insertSample();
        }
    }
}
