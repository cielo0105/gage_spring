package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.ChatDto;
import com.ssafy.happyhouse.model.dto.SocketVo;
import com.ssafy.happyhouse.model.service.ChatService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE} , maxAge = 6000)
@RequestMapping("/chat")
@RequiredArgsConstructor
@Slf4j
@RestController
public class ChatController {
	
	private final ChatService service;
	
	@MessageMapping("/receive/{id}")
	@SendTo("/send/{id}")
	public ResponseEntity<Map<String, Object>> SocketHandler(SocketVo socketVo, @DestinationVariable String id) {
		String userId = socketVo.getUserId();
		String content = socketVo.getContent();
		
		SocketVo result = new SocketVo(userId, content);
		System.out.println(result);
		
//		service.saveChat(result);
		
		return handleSuccess(result);
	}
	
	@PostMapping("/chat")
	public ResponseEntity<Map<String, Object>> createRoom(String userId, int id){
		service.createRoom(userId, id);
		return handleSuccess("성공");
	}
	
	@GetMapping("/chat")
	public ResponseEntity<Map<String, Object>> chatList(String userId){
		List<ChatDto> list = service.getList(userId);
		return handleSuccess(list);
	}
	
	private ResponseEntity<Map<String, Object>> handleSuccess(Object data) {
		Map<String, Object> result = new HashMap<>();
		result.put("success", true);
		result.put("data", data);
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}
}
