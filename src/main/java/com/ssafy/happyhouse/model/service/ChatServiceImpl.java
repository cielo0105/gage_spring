package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dao.ChatDao;
import com.ssafy.happyhouse.model.dto.ChatDto;
import com.ssafy.happyhouse.model.dto.ChatLogDto;
import com.ssafy.happyhouse.model.dto.SocketVo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService{
	
	private final ChatDao dao;
	
	@Override
	public ChatLogDto saveChat(ChatLogDto chatLogDto) {
		dao.insertChatLog(chatLogDto);
		int logId = chatLogDto.getId();
		return dao.selectChatLog(logId);
	}

	@Override
	public int createRoom(String userId, int id) {
		// 같은 deal 있는지 확인
		ChatDto checkDto = dao.findChatByUserDeal(userId, id);
		// 있으면 create 안함.
		// 없으면 create
		System.out.println("checkDto== " + checkDto);
		if(checkDto==null) {
			ChatDto chatDto = ChatDto.builder().user2(userId).deal(id).build();
			dao.createRoom(chatDto);
			int result = chatDto.getIq();
			System.out.println("result: "+ result);
			ChatLogDto socketVo = ChatLogDto.builder().chatId(result).user(userId).content("채팅을 시작합니다.").build();
			dao.insertChatLog(socketVo);
			return result;
		}else {
			ChatDto dto = dao.findChatByUserDeal(userId, id);
			return dto.getId();
		}
	}

	@Override
	public List<ChatDto> getList(String userId) {
		return dao.getList(userId);
	}

	@Override
	public List<ChatLogDto> msgList(String chatId) {
		return dao.msgList(chatId);
	}
}
