package com.ssafy.happyhouse.model.service;

import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dao.MemberDao;
import com.ssafy.happyhouse.model.dto.MemberDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	
	private final MemberDao dao;
	
	public void registerMember(MemberDto memberDto) {
		dao.registerMember(memberDto);
	}

	@Override
	public MemberDto login(String userId, String userPass) {
		return dao.login(userId, userPass);
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
