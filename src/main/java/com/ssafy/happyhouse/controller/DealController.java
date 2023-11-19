package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.DealDto;
import com.ssafy.happyhouse.model.service.DealService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE} , maxAge = 6000)
@RestController
@RequestMapping("/deal")
@RequiredArgsConstructor
@Slf4j
public class DealController {
	
	private final DealService service;
	
    @PostMapping("/regist")
    public ResponseEntity<Map<String, Object>> regist(@RequestBody Map<String, String> data){
    	DealDto dealDto = new DealDto();
    	dealDto.setType(data.get("type"));
    	dealDto.setAddress(data.get("address"));
    	dealDto.setDetailAddress(data.get("detailAddress"));
    	dealDto.setArea(Double.parseDouble(data.get("area")));
    	dealDto.setRecommend(data.get("recommend"));
    	dealDto.setFloor(Integer.parseInt(data.get("floor")));
    	dealDto.setDesc(data.get("desc"));
    	
    	return handleSuccess(service.regist(dealDto));
    }
    
	
	private ResponseEntity<Map<String, Object>> handleSuccess(Object data) {
		Map<String, Object> result = new HashMap<>();
		result.put("success", true);
		result.put("data", data);
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}
}
