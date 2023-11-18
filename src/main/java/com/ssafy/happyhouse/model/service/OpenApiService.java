package com.ssafy.happyhouse.model.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.happyhouse.model.dao.ImportDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OpenApiService {
	
	private final ImportDao dao;
	public String getData(String url) throws JsonProcessingException, URISyntaxException {

		RestTemplate restTemplate = new RestTemplate();

		URI uri = new URI(url);
		ResponseEntity<HashMap> resultMap = restTemplate.getForEntity(uri, HashMap.class);
		
		List<Object> result = (List<Object>) resultMap.getBody().get("data");
		System.out.println(result);
		
		dao.registIncome(result);
		return "성공";
	}
}
