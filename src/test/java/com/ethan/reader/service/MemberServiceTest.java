package com.ethan.reader.service;

import com.ethan.reader.entity.Member;
import com.ethan.reader.entity.MemberReadState;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MemberServiceTest {
    @Resource
    private MemberService memberService;

    @Test
    public void createMember1() {
        memberService.createMember("imooc_4","test","i4");
    }
    @Test
    public void createMember2() {
        memberService.createMember("imooc_700","test","i700");
    }

    @Test
    public void checkLogin1() {
        Member member = memberService.checkLogin("imooc_487ss","123sd456");
        System.out.println(member);
    }
    @Test
    public void checkLogin2() {
        Member member = memberService.checkLogin("imooc_487","123456");
        System.out.println(member);
    }

    @Test
    public void selectMemberReadState() {
        MemberReadState memberReadState =memberService.selectMemberReadState(331l,5l);
        System.out.println(memberReadState);
    }
}