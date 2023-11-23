package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.dto.ChatDto;
import com.ssafy.happyhouse.model.dto.ChatLogDto;
import com.ssafy.happyhouse.model.dto.SocketVo;

public interface ChatService {

	ChatLogDto saveChat(ChatLogDto result);

	int createRoom(String userId, int id);

	List<ChatDto> getList(String userId);

	List<ChatLogDto> msgList(String chatId);
}
