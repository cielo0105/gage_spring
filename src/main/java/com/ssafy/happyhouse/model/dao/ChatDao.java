package com.ssafy.happyhouse.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.happyhouse.model.dto.ChatDto;
import com.ssafy.happyhouse.model.dto.ChatLogDto;

@Mapper
public interface ChatDao {
	int insertChatLog(ChatLogDto socketVo);

	int createRoom(ChatDto chatDto);

	List<ChatDto> getList(String userId);

	List<ChatLogDto> msgList(String chatId);

	ChatLogDto selectChatLog(int logId);

	int getDealId(int id);

	ChatDto findChatByUserDeal(@Param("userId") String userId,@Param("id")  int id);
}
