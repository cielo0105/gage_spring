package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.service.HouseService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.POST} , maxAge = 6000)
@RestController
@RequestMapping("/house")
@RequiredArgsConstructor
@Slf4j
public class HouseController {
	
	private final HouseService service;
	
	@GetMapping("/code")
	public ResponseEntity<Map<String, Object>> getCode(String dong) {
//		return handleSuccess("성공");
		return handleSuccess(service.getDongCode(dong));
	}
	
	@GetMapping("/aptinfo")
	public ResponseEntity<Map<String, Object>> aptInfo(Long code) {
		return handleSuccess(service.searchByDongCode(code));
	}
	
	@GetMapping("/dealinfo")
	public ResponseEntity<Map<String, Object>> dealInfo(Long aptNo) {
		System.out.println("code=="+aptNo);
		return handleSuccess(service.searchByAptNo(aptNo));
	}

	private ResponseEntity<Map<String, Object>> handleSuccess(Object data) {
		Map<String, Object> result = new HashMap<>();
		result.put("success", true);
		result.put("data", data);
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}
}
