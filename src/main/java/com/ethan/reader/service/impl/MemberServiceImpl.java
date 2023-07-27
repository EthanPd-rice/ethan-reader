package com.ethan.reader.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ethan.reader.entity.Member;
import com.ethan.reader.entity.MemberReadState;
import com.ethan.reader.mapper.MemberMapper;
import com.ethan.reader.mapper.MemberReadStateMapper;
import com.ethan.reader.service.MemberService;
import com.ethan.reader.service.exception.MemberException;
import com.ethan.reader.utils.Md5Utils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
public class MemberServiceImpl implements MemberService {
    @Resource
    private MemberMapper memberMapper;
    @Resource
    private MemberReadStateMapper memberReadStateMapper;
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Member createMember(String username, String password, String nickname) {
        QueryWrapper<Member> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        List<Member> members = memberMapper.selectList(wrapper);
        if(members.size()>0){
            throw new MemberException("用户已存在");
        }
        int salt = new Random().nextInt(1000) + 1000;
        String md5 = Md5Utils.md5Digest(password,salt);
        Member member = new Member();
        member.setSalt(salt);
        member.setPassword(md5);
        member.setNickname(nickname);
        member.setUsername(username);
        member.setCreateTime(new Date());
        memberMapper.insert(member);
        return member;
    }

    @Override
    public Member checkLogin(String username, String password) {
        QueryWrapper<Member> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        Member member = memberMapper.selectOne(wrapper);
        if(member == null){
            throw new MemberException("用户不存在");
        }
        String md5 = Md5Utils.md5Digest(password,member.getSalt());
        if(!md5.equals(member.getPassword())){
            throw new MemberException("您输入的密码有误");
        }
        return member;
    }

    @Override
    public Member selectById(Long memberId) {
        return memberMapper.selectById(memberId);
    }

    @Override
    public MemberReadState selectMemberReadState(Long memberId, Long bookId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("member_id",memberId);
        wrapper.eq("book_id",bookId);
        MemberReadState memberReadState = memberReadStateMapper.selectOne(wrapper);
        return memberReadState;
    }



    @Override
    public MemberReadState updateMemberReadState(Long memberId, Long bookId, Integer readState) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("member_id",memberId);
        wrapper.eq("book_id",bookId);
        MemberReadState memberReadState = memberReadStateMapper.selectOne(wrapper);
        if(memberReadState == null){
            memberReadState = new MemberReadState();
            memberReadState.setMemberId(memberId);
            memberReadState.setBookId(bookId);
            memberReadState.setReadState(readState);
            memberReadState.setCreateTime(new Date());
            memberReadStateMapper.insert(memberReadState);
        }else{
            memberReadState.setReadState(readState);
            memberReadState.setCreateTime(new Date());
            memberReadStateMapper.updateById(memberReadState);
        }
        return memberReadState;
    }

}
