package com.ssafy.happyhouse.model.service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dao.ReportDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService{
	private final ReportDao dao;
	@Override
	public  Map<String, Double> getLocalPeopleRank(String code) {
		Map<String, Double> result = new HashMap<>();
		result = dao.getLocalPeopleRank(code);
		System.out.println(result);
		result.remove("code");
		
		List<Map.Entry<String, Double>> entryList = new LinkedList<>(result.entrySet());
		
		entryList.sort(new Comparator<Map.Entry<String, Double>>() {
		    @Override
		    public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
		    	return Double.compare(o2.getValue(), o1.getValue());
		    }
		});
		
	    Map<String, Double> sortedMap = new LinkedHashMap<>();
//	    for (Map.Entry<String, Double> entry : entryList) {
//	        sortedMap.put(entry.getKey(), entry.getValue());
//	    }
	    String key = null;
	    for(int i=0; i<4; i++) {
	    	if(i==0) sortedMap.put(entryList.get(i).getKey(), entryList.get(i).getValue());
	    	else{
	    		key = change(entryList.get(i).getKey());
	    	sortedMap.put(key, entryList.get(i).getValue());
	    	}
	    }
	    System.out.println(sortedMap);
	    return sortedMap;
		
	}
	
	private String change(String word) {
		StringBuilder sb = new StringBuilder();
		char[] words = word.toCharArray();
		if(words.length == 5) {
			sb.append(words[1]).append(words[2]).append("-").append(words[3]).append(words[4]).append("세 ");
		}
		else {
			if(words[1]=='0') sb.append(words[2]).append("세 이하 ");
			else sb.append(words[1]).append(words[2]).append("세 이상 ");
		}
		if(words[0] == 'm') sb.append("남");
		else sb.append("여");
		System.out.println(sb);
		return sb.toString();
	}
	
}
