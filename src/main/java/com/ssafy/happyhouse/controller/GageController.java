package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.service.GageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE} , maxAge = 6000)
@RestController
@RequestMapping("/gage")
@RequiredArgsConstructor
@Slf4j
public class GageController {
	
	private final GageService service;
	
	
	@GetMapping("/main")
	public ResponseEntity<Map<String, Object>> getMainCategory() {
		return handleSuccess(service.getMainCategory());
	}
	
	@GetMapping("/middle")
	public ResponseEntity<Map<String, Object>> getMiddleCategory(String main) {
		return handleSuccess(service.getMiddleCategory(main));
	}

	@GetMapping("/sub")
	public ResponseEntity<Map<String, Object>> getSubCategory(String mid) {
		return handleSuccess(service.getSubCategory(mid));
	}
	
	@GetMapping("/result")
	public ResponseEntity<Map<String, Object>> getResult(String sub) {
		return handleSuccess(service.getResult(sub));
	}
	
	@GetMapping("/list")
	public ResponseEntity<Map<String, Object>> getGageList(String code) {
		return handleSuccess(service.getGageList(code));
	}
	
	@GetMapping("/range")
	public ResponseEntity<Map<String, Object>> getRangeDong( @RequestParam("bx") String bx,
            @RequestParam("by") String by,
            @RequestParam("tx") String tx,
            @RequestParam("ty") String ty) { // map 범위 안에 존재하는 동 코드 구하기
		return handleSuccess(service.getDongList(bx,by,tx,ty));
	}

	private ResponseEntity<Map<String, Object>> handleSuccess(Object data) {
		Map<String, Object> result = new HashMap<>();
		result.put("success", true);
		result.put("data", data);
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}
}
