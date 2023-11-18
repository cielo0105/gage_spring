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
	
//	한국부동산원_상업용부동산 임대동향 조사 통계 조회 서비스-순영업 소득 조회
//	https://www.data.go.kr/tcs/dss/selectApiDataDetailView.do?publicDataPk=15099345
    @GetMapping("/income")
    public ResponseEntity<Map<String, Object>> income() throws JsonProcessingException, URISyntaxException {
    	String url = "https://api.odcloud.kr/api/CommercialRealEstateLeaseTrendSvc/v1/getNetOperationIncome";
    	return handleSuccess(service.addIncome(url));
    }
    
    
	
	private ResponseEntity<Map<String, Object>> handleSuccess(Object data) {
		Map<String, Object> result = new HashMap<>();
		result.put("success", true);
		result.put("data", data);
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}
}
