package com.ssafy.happyhouse.controller;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.happyhouse.model.service.OpenApiService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE} , maxAge = 6000)
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class ApiController {
	private final OpenApiService service;
	
    @GetMapping("/income")
    public ResponseEntity<Map<String, Object>> income() throws JsonProcessingException, URISyntaxException {
    	String serviceKey = "z3I8BQhzcY/kk+JswXJSKGumtfSXcuo6mT0Jcl1zcF6CqZNo5wJI4mYzJtaUgXflLnQhDji1eAk2yIsfF6YWeA==";
    	String url = "https://api.odcloud.kr/api/CommercialRealEstateLeaseTrendSvc/v1/getNetOperationIncome?page=1&perPage=10&serviceKey=z3I8BQhzcY%2Fkk%2BJswXJSKGumtfSXcuo6mT0Jcl1zcF6CqZNo5wJI4mYzJtaUgXflLnQhDji1eAk2yIsfF6YWeA%3D%3D";
        System.out.println(service.getData(url));
    	return handleSuccess("");
    }
	
	private ResponseEntity<Map<String, Object>> handleSuccess(Object data) {
		Map<String, Object> result = new HashMap<>();
		result.put("success", true);
		result.put("data", data);
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}
}
