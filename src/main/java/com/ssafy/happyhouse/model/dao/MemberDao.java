package com.ssafy.happyhouse.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.MemberDto;

@Mapper
public interface MemberDao {
	void registerMember(MemberDto memberDto);

	MemberDto login(String userId, String userPass);

	MemberDto getInfo(String userId);
	
	void modifyMember(MemberDto memberDto);

	void deleteMember(String userId);
}
