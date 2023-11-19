package com.ssafy.happyhouse.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ImportDao {
	int registIncome(List<Object> list);
	int registLease(List<Object> list);
	int registIncomeRate(List<Object> list);
	int registGage(List<Object> list);
	void deleteIncome();
	void deleteGage();
	void deleteLease();
	void deleteIncomeRate();
}
