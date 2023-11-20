package com.ssafy.happyhouse.model.service;

import java.util.List;



public interface GageService {
	List<String> getMainCategory(); // 대분류

	List<String> getMiddleCategory(String main);

	List<String> getSubCategory(String mid);

	List<String> getResult(String sub);

	List<String> getGageList(String code);
	
	List<String> getDongList(String bx, String by, String tx, String ty);

	String[] getLocation(String code);

	int getGageCount(String dong);

	int getGageCountByCode(String code, String dong);
}
