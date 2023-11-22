package com.ssafy.happyhouse.model.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatLogDto {
	private int id;
	private int chatId;
	private String user;
	private String content;
	private Date createDate;
}
