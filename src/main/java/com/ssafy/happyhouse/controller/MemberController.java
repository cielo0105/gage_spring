package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.MemberDto;
import com.ssafy.happyhouse.model.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService service;
    
    @PostMapping("/regist")
    public ResponseEntity<Map<String, Object>> regist(MemberDto memberDto) {
        service.registerMember(memberDto);
        return handleSuccess(memberDto);
    }
    
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestParam String userId, @RequestParam String userPass, HttpSession session) {
        MemberDto result = service.login(userId, userPass);
        if(result != null) {
        	session.setAttribute("user", result);
        }
    	return handleSuccess(service.login(userId, userPass));
    }
    
    @PostMapping("/logout")
    public ResponseEntity<Map<String, Object>> logout(HttpSession session) {
        session.invalidate();
        return handleSuccess("로그아웃 성공");
    }
    
    @GetMapping("/info/{userId}")
    public ResponseEntity<Map<String, Object>> info(@PathVariable String userId) {
        return handleSuccess(service.getInfo(userId));
    }
    
    
    
    @PutMapping("/edit")
    public ResponseEntity<Map<String, Object>> modify(@RequestBody MemberDto memberDto, HttpSession session) {
    	
    	System.out.println("00000000000000000000000000000000"+memberDto.getUserName());
        service.modifyMember(memberDto);
        session.setAttribute("user", memberDto);
        return handleSuccess(memberDto);
    }
    
    @DeleteMapping("/{userId}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable String userId, HttpSession session) {
        service.deleteMember(userId);
        session.invalidate();
        return handleSuccess(userId);
    }
    
    private ResponseEntity<Map<String, Object>> handleSuccess(Object data) {
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("data", data);
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }
    
}