package com.ssafy.happyhouse.security.provider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.ssafy.happyhouse.model.dao.MemberDao;
import com.ssafy.happyhouse.model.dto.MemberDto;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

  private final MemberDao dao;

  @Override
  public Authentication authenticate(Authentication authentication){
    String id = authentication.getName();
    String password = authentication.getCredentials().toString();

    MemberDto user = dao.getInfo(id);
    
    if(! user.getUserPass().equals(password)) {
    	throw new RuntimeException();
    }

//    SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(
//        user.getAuthorities().getRole()); //"ROLE_USER" 와 같은 권한

    //임시 인증객체를 진짜 인증객체로 생성
    return new UsernamePasswordAuthenticationToken(id, password);
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(
        UsernamePasswordAuthenticationToken.class);
  }
}