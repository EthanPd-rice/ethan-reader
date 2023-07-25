package com.ethan.reader.controller;


import com.ethan.reader.entity.Member;
import com.ethan.reader.service.MemberService;
import com.ethan.reader.utils.ResponseUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/member")
public class MemberController {
    @Resource
    private MemberService memberService;
    @PostMapping("/registe")
    public ResponseUtils registe(String username, String password, String nickname, String vc, HttpServletRequest request){
        String verifyCode = (String)request.getSession().getAttribute("kaptchaVerifyCode");
        ResponseUtils responseUtils = null;
        if(vc == null || verifyCode == null || !vc.equals(verifyCode)){
            responseUtils = new ResponseUtils("VerifyCodeError","验证码错误，请重新输入");
        }else{
            try {
                memberService.createMember(username, password, nickname);
                responseUtils = new ResponseUtils();
            }catch (Exception e){
                e.printStackTrace();
                responseUtils = new ResponseUtils(e.getClass().getSimpleName(),e.getMessage());
            }
        }
        return responseUtils;
    }

    @PostMapping("/check_login")
    public ResponseUtils checkLogin(String username, String password, String nickname, String vc,HttpServletRequest request){
        String verifyCode = (String)request.getSession().getAttribute("kaptchaVerifyCode");
        ResponseUtils responseUtils = null;
        if(vc == null || !vc.equals(verifyCode)){
            responseUtils = new ResponseUtils("VerifyCodeError","验证码错误，请重新输入");
        }else{
            try {
                Member member = memberService.checkLogin(username, password);
                member.setPassword(null);
                member.setSalt(null);
                responseUtils = new ResponseUtils().put("member",member);
            }catch (Exception e){
                e.printStackTrace();
                responseUtils = new ResponseUtils(e.getClass().getSimpleName(),e.getMessage());
            }
        }
        return responseUtils;
    }

    @GetMapping("select_by_id")
    public ResponseUtils selectById(Long memberId){
        ResponseUtils responseUtils = null;
        try{
            Member member = memberService.selectById(memberId);
            responseUtils = new ResponseUtils().put("member",member);
        }catch(Exception ex){
            ex.printStackTrace();
            responseUtils = new ResponseUtils(ex.getClass().getSimpleName(),ex.getMessage());
        }
        return responseUtils;
    }
}
