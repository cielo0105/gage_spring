package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.dto.BoardDto;
import com.ssafy.happyhouse.util.PageNavigation;

public interface BoardService {
	void registerArticle(BoardDto boardDto);

	List<BoardDto> searchListAll();

	List<BoardDto> searchListBySubject(String subject);

	BoardDto viewArticle(int no);

	void modifyArticle(BoardDto boardDto);

	void deleteArticle(int no);

	List<BoardDto> listArticle(Map<String, String> map) throws Exception;

	PageNavigation makePageNavigation(Map<String, String> map) throws Exception;
}
