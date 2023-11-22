package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.dto.ChatDto;
import com.ssafy.happyhouse.model.dto.SocketVo;

public interface ChatService {

	void saveChat(SocketVo result);

	void createRoom(String userId, int id);

	List<ChatDto> getList(String userId);
}
