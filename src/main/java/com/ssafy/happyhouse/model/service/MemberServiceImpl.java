package com.ssafy.happyhouse.model.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dao.MemberDao;
import com.ssafy.happyhouse.model.dto.MemberDto;
import com.ssafy.happyhouse.security.dto.TokenDto;
import com.ssafy.happyhouse.security.provider.TokenProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	
	private final AuthenticationManager authenticationManager;
	private final TokenProvider tokenProvider;
	private final MemberDao dao;
	
	public void registerMember(MemberDto memberDto) {
		dao.registerMember(memberDto);
	}

	@Override
	public TokenDto login(String userId, String userPass) {
		
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userId, userPass);
		Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return tokenProvider.createToken(authentication);
	}

	@Override
	public MemberDto getInfo(String userId) {
		return dao.getInfo(userId);
	}

	@Override
	public void modifyMember(MemberDto memberDto) {
		dao.modifyMember(memberDto);
	}

	@Override
	public void deleteMember(String userId) {
		dao.deleteMember(userId);
	}

}
