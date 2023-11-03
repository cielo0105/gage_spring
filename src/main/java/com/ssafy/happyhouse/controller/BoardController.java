package com.ssafy.happyhouse.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.BoardDto;
import com.ssafy.happyhouse.model.service.BoardService;
import com.ssafy.happyhouse.util.PageNavigation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
@Slf4j
public class BoardController {
	
	private final BoardService service;
	
	private int pgno;
	private String key;
	private String word;
	private String queryStrig;

	@GetMapping("/")
	public ResponseEntity<Map<String, Object>> test() {
		return handleSuccess("성공");
	}
	
	@GetMapping("/list")
	public ResponseEntity<Map<String, Object>> list(@RequestParam int pgno, @RequestParam String key, @RequestParam String word) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("pgno", pgno+"");
		map.put("key", key);
		map.put("word", word);
		
		List<BoardDto> list = service.listArticle(map);
		PageNavigation pageNavigation = service.makePageNavigation(map);
		Map<String, Object> result = new HashMap();
		result.put("list", list);
		result.put("pageNavigation", pageNavigation);
		return handleSuccess(result);
	}
	
	@GetMapping("/view/{no}")
	public ResponseEntity<Map<String, Object>> view(@PathVariable int no) {
		return handleSuccess(service.viewArticle(no));
	}
	
	

	private ResponseEntity<Map<String, Object>> handleSuccess(Object data) {
		Map<String, Object> result = new HashMap<>();
		result.put("success", true);
		result.put("data", data);
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}
}
