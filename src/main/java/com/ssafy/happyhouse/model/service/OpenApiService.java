package com.ssafy.happyhouse.model.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.happyhouse.model.dao.ImportDao;
import com.ssafy.happyhouse.model.dto.IncomeDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OpenApiService {
	
	private final ImportDao dao;
	public String getData(String url) throws JsonProcessingException, URISyntaxException {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		String jsonInString = "";

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders header = new HttpHeaders();
		HttpEntity<?> entity = new HttpEntity<>(header);

		URI uri = new URI(url);
		ResponseEntity<IncomeDto> resultMap = restTemplate.getForEntity(uri, IncomeDto.class);
		
		System.out.println("resultMap===" + resultMap.getBody());
		result.put("statusCode", resultMap.getStatusCodeValue());
		result.put("header", resultMap.getHeaders());
		result.put("body", resultMap.getBody());

		// 데이터를 제대로 전달 받았는지 확인 string형태로 파싱해줌
		ObjectMapper mapper = new ObjectMapper();
		jsonInString = mapper.writeValueAsString(resultMap.getBody());
		
		System.out.println(jsonInString);
		

		return jsonInString;
	}
}
