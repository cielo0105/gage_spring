package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.service.NewsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = { "*" }, methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.POST }, maxAge = 6000)
@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
@Slf4j
public class NewsController {
	
	private final NewsService service;
	
	@GetMapping("/")
	public ResponseEntity<Map<String, Object>> getNews() {
		System.out.println(service.getNewsList());
		return handleSuccess("");
	}
	
	private ResponseEntity<Map<String, Object>> handleSuccess(Object data) {
		Map<String, Object> result = new HashMap<>();
		result.put("success", true);
		result.put("data", data);
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}

	private ResponseEntity<Map<String, Object>> handleFailure(String msg, HttpStatus status) {
		Map<String, Object> result = new HashMap<>();
		result.put("success", false);
		result.put("msg", msg);
		return new ResponseEntity<Map<String, Object>>(result, status);
	}
}