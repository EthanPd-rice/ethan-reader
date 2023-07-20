package com.ethan.reader.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ethan.reader.entity.TestTable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestMapperTestor {
    @Resource
    private TestMapper testMapper;
    @Test
    public void testInsertSample() {
        testMapper.insertSample();
    }
    @Test
    public void testInsert(){
        TestTable test = new TestTable();
        test.setContent("测试数据");
        int count = testMapper.insert(test);
        System.out.println("本次新增"+count+"条数据");
    }
    @Test
    public void testUpdate(){
        TestTable test = new TestTable();
        test.setId(2);
        test.setContent("爹修改了");
        testMapper.updateById(test);
    }
    @Test
    public void testDelete(){
        TestTable test = new TestTable();
        testMapper.deleteById(2);
    }
    @Test
    public void testSelectById(){
        TestTable test = testMapper.selectById(21);
        System.out.println(test);
    }
    @Test
    public void testSelectList(){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.gt("id",21);
        //多条件
        wrapper.eq("content","测试内容");
        List<TestTable> list = testMapper.selectList(wrapper);
        System.out.println(list.size());
    }
    @Test
    public void testPagination(){
        //Page第一个参数，当前查询第几页 第二个参数表示每页多少记录
        //Page(4,2)对应的sql语句LIMIT 6, 2 表示你要跳过前6行，然后获取接下来的2行数据。
        IPage page = new Page(4,2);
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.gt("id",21);
        wrapper.eq("content","测试内容");
        //selectPage第一个参数分页对象，第二个参数分页构造器
        page = testMapper.selectPage(page,wrapper);
        System.out.println("总页数"+page.getPages());
        System.out.println("总记录数"+page.getTotal());
        System.out.println("当前页数据"+page.getRecords());
    }
}