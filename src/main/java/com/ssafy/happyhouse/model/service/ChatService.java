package com.ssafy.happyhouse.model.service;

import com.ssafy.happyhouse.model.dto.SocketVo;

public interface ChatService {

	void saveChat(SocketVo result);

	void createRoom(String userId, int id);
}
