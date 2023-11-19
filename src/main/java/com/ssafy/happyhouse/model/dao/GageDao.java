package com.ssafy.happyhouse.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GageDao {
	List<String> getMainCategory(); // 대분류

	List<String> getMiddleCategory(String main); // 중분류
	
	List<String> getSubCategory(String main); // 중분류

	List<String> getResult(String sub); // 결과
}
