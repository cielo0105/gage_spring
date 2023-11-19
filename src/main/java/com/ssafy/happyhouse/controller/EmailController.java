package com.ssafy.happyhouse.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.EmailMessage;
import com.ssafy.happyhouse.model.dto.MemberDto;
import com.ssafy.happyhouse.model.service.EmailService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = { "*" }, methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.POST }, maxAge = 6000)
@RequestMapping("/send-mail")
@RestController
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;


	// 임시 비밀번호 발급 
    @PostMapping("/password")
    public ResponseEntity sendPasswordMail(@RequestBody Map<String, String> data) {
    	String email = data.get("email");
    	
        EmailMessage emailMessage = EmailMessage.builder()
                .to(email)
                .subject("[GAGE] 임시 비밀번호 발급")
                .build();

        emailService.sendMail(emailMessage, "임시 비밀번호");

        return ResponseEntity.ok().build();
    }

	// 회원가입 이메일 인증 - 요청 시 body로 인증번호 반환하도록 작성하였음
    @PostMapping("/email")
    public ResponseEntity sendJoinMail(@RequestBody Map<String, String> data) {
    	String email = data.get("email");
    	
        EmailMessage emailMessage = EmailMessage.builder()
                .to(email)
                .subject("[GAGE] 이메일 인증을 위한 인증 코드 발송")
                .build();

        String code = emailService.sendMail(emailMessage, "이메일 인증번호");
        return ResponseEntity.ok(code);
    }
}