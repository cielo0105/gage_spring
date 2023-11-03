package com.ssafy.happyhouse.model.service;

import com.ssafy.happyhouse.model.dto.MemberDto;

public interface MemberService {
	void registerMember(MemberDto memberDto);

	MemberDto login(String userId, String userPass);

	MemberDto getInfo(String userId);
	
	void modifyMember(MemberDto memberDto);

	void deleteMember(String userId);
}
