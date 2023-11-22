package com.ssafy.happyhouse.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NewsDao {

	int registNews(String title, String url);

	int deleteNews();

	int registInfo(String title, String url);
	
	int registSupport(String title, String url, String img);
	
	List<Map<String, String>> getNews();

	List<Map<String, String>> getInfo();

	List<Map<String, String>> getSupport();


}
