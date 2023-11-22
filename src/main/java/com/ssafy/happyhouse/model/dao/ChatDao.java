package com.ssafy.happyhouse.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.ChatDto;
import com.ssafy.happyhouse.model.dto.SocketVo;

@Mapper
public interface ChatDao {
	void insert(SocketVo socketVo);

	void createRoom(String userId, int id);

	List<ChatDto> getList(String userId);
}
