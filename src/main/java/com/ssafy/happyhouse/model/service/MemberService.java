package com.ssafy.happyhouse.model.service;

import com.ssafy.happyhouse.model.dto.MemberDto;
import com.ssafy.happyhouse.security.dto.TokenDto;

public interface MemberService {
	void registerMember(MemberDto memberDto);

	TokenDto login(String userId, String userPass);

	MemberDto getInfo(String userId);
	
	void modifyMember(MemberDto memberDto);

	void deleteMember(String userId);
}
