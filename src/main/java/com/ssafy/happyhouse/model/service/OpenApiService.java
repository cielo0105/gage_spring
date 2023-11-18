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
	private String serviceKey = "z3I8BQhzcY%2Fkk%2BJswXJSKGumtfSXcuo6mT0Jcl1zcF6CqZNo5wJI4mYzJtaUgXflLnQhDji1eAk2yIsfF6YWeA%3D%3D";

	public String addIncome(String url) throws JsonProcessingException, URISyntaxException {
		StringBuilder sb = new StringBuilder();
//		기존 데이터 삭제
		dao.deleteIncome();
		
//		데이터 하나만 가져와서 matchCount 확인
		String checkCountUrl = sb.append(url).append("?page=1&perPage=1&serviceKey=").append(serviceKey).toString();
		int matchCount = (int) getData(checkCountUrl).get("matchCount");
		
		System.out.println("matchCount="+ matchCount);
		
		sb.setLength(0);
		
//		totalCount만큼 가져오기
		int currentCount = 0;
		
		System.out.println("데이터 insert 시작");
		for(int i=1; currentCount<matchCount; i++) {
			currentCount+= 2000;
			sb.setLength(0);
			String resultUrl = sb.append(url).append("?page=").append(i).append("&perPage=").append(currentCount).append("&cond%5BRESEARCH_DATE%3A%3AGTE%5D=201811&serviceKey=").append(serviceKey).toString();
			HashMap<String,Object> result = getData(resultUrl);
			List<Object> list = (List<Object>) result.get("data");
			
//		새로 받아온 데이터 삽입
			dao.registIncome(list);
			System.out.println(currentCount+"개 insert 완료");
		}
		System.out.println("데이터 insert 종료");
		
		return "success";
	}

	public HashMap<String, Object> getData(String url) throws JsonProcessingException, URISyntaxException {
		System.out.println("호출된 url=" + url);

		RestTemplate restTemplate = new RestTemplate();

		URI uri = new URI(url);
		ResponseEntity<HashMap> resultMap = restTemplate.getForEntity(uri, HashMap.class);

		HashMap<String, Object> result = resultMap.getBody();
		return result;
	}
}
