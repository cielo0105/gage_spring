package com.ssafy.happyhouse.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.BoardDto;

@Mapper
public interface BoardDao {
	void registerArticle(BoardDto BoardDto);

	List<BoardDto> searchListAll();

	List<BoardDto> searchListBySubject(String subject);

	BoardDto viewArticle(int no);

	void modifyArticle(BoardDto BoardDto);

	void deleteArticle(int no);

	List<BoardDto> listArticle(Map<String, Object> param);

	int getTotalArticleCount(Map<String, Object> param);
}
