package com.ssafy.happyhouse.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ImportDao {
	int registIncome(List<Object> list);

	void deleteIncome();
}
