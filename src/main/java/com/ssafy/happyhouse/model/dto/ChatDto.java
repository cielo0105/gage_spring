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
public class ChatDto {
	private int id;
	private String user1;
	private String user2;
	private String lastMsg;
	private Date lastSend;
}
