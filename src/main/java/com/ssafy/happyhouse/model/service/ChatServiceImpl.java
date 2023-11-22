package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dao.ChatDao;
import com.ssafy.happyhouse.model.dto.ChatDto;
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

	@Override
	public List<ChatDto> getList(String userId) {
		return dao.getList(userId);
	}

}
