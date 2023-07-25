package com.ethan.reader.service;

import com.ethan.reader.entity.Member;

public interface MemberService {
    public Member createMember(String username,String password,String nickname);
    public Member checkLogin(String username,String password);
    public Member selectById(Long memberId);
}
