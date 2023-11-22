package com.ssafy.happyhouse.model.service;

import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dao.ChatDao;
import com.ssafy.happyhouse.model.dto.SocketVo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService{
	
	private final ChatDao dao;
	
	@Override
	public void saveChat(SocketVo socketVo) {
		dao.insert(socketVo);
	}

	@Override
	public void createRoom(String userId, int id) {
		dao.createRoom(userId, id);
	}

}
