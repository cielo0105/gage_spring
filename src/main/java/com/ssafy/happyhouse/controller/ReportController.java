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
import com.ssafy.happyhouse.model.service.ReportService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE} , maxAge = 6000)
@RestController
@RequestMapping("/report")
@RequiredArgsConstructor
@Slf4j
public class ReportController {
	private final ReportService service;
	
	@GetMapping("/rank")
	public ResponseEntity<Map<String, Object>> getLocalPeopleRank(String code){
		 return handleSuccess(service.getLocalPeopleRank(code));
	}
	
	@GetMapping("/gagerank")
	public ResponseEntity<Map<String, Object>> getGageRank(@RequestParam("code") String code,
            @RequestParam("dong") String dong){
		System.out.println(code+" "+dong);
		System.out.println(service.getGageRank(code, dong));
		 return handleSuccess(service.getGageRank(code, dong));
	}
	
	@GetMapping("/indicator")
	public ResponseEntity<Map<String, Object>> getIndicator(@RequestParam("code") String code){ // 동코드
		 return handleSuccess(service.getIndicator(code));
	}
	
	private ResponseEntity<Map<String, Object>> handleSuccess(Object data) {
		Map<String, Object> result = new HashMap<>();
		result.put("success", true);
		result.put("data", data);
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}
}
