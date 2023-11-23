package com.ssafy.happyhouse.security.provider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.ssafy.happyhouse.model.dao.MemberDao;
import com.ssafy.happyhouse.model.dto.MemberDto;
import com.ssafy.happyhouse.model.service.MemberService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

  private final MemberDao dao;

  @Override
  public Authentication authenticate(Authentication authentication){
    String userId = authentication.getName();
    String password = authentication.getCredentials().toString();
    
    System.out.println("받아온데이터"+userId+password);
    
    MemberDto user = dao.getInfo(userId);
    
    System.out.println("userdto" + user);
    
    if(! user.getUserPass().equals(password)) {
    	throw new RuntimeException();
    }

//    SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(
//        user.getAuthorities().getRole()); //"ROLE_USER" 와 같은 권한

    //임시 인증객체를 진짜 인증객체로 생성
    return new UsernamePasswordAuthenticationToken(userId, password);
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(
        UsernamePasswordAuthenticationToken.class);
  }
}