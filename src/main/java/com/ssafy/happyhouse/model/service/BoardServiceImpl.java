package com.ssafy.happyhouse.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dao.BoardDao;
import com.ssafy.happyhouse.model.dto.BoardDto;
import com.ssafy.happyhouse.util.PageNavigation;
import com.ssafy.happyhouse.util.SizeConstant;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

	private final BoardDao dao;

	@Override
	public void registerArticle(BoardDto boardDto) {
		dao.registerArticle(boardDto);

	}

	@Override
	public List<BoardDto> searchListAll() {
		return dao.searchListAll();
	}

	@Override
	public List<BoardDto> searchListBySubject(String subject) {
		return dao.searchListBySubject(subject);
	}

	@Override
	public BoardDto viewArticle(int no) {
		return dao.viewArticle(no);
	}

	@Override
	public void modifyArticle(BoardDto boardDto) {
		dao.modifyArticle(boardDto);
	}

	@Override
	public void deleteArticle(int no) {
		dao.deleteArticle(no);
	}

	@Override
	public List<BoardDto> listArticle(Map<String, String> map) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();

		String key = map.get("key");
		param.put("key", key.isEmpty() ? "" : key);
		param.put("word", map.get("word").isEmpty() ? "" : map.get("word"));
		int pgno = Integer.parseInt(map.get("pgno"));
		int start = pgno * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;
		param.put("start", start);
		param.put("listsize", SizeConstant.LIST_SIZE);

		return dao.listArticle(param);
	}

	@Override
	public PageNavigation makePageNavigation(Map<String, String> map) throws Exception {
		PageNavigation pageNavigation = new PageNavigation();

		int naviSize = SizeConstant.NAVIGATION_SIZE;
		int sizePerPage = SizeConstant.LIST_SIZE;
		int currentPage = Integer.parseInt(map.get("pgno"));
		pageNavigation.setCurrentPage(currentPage);
		pageNavigation.setNaviSize(naviSize);

		Map<String, Object> param = new HashMap<String, Object>();
		String key = map.get("key");
		param.put("key", key.isEmpty() ? "" : key);
		param.put("word", map.get("word").isEmpty() ? "" : map.get("word"));

		int totalCount = dao.getTotalArticleCount(param);
		pageNavigation.setTotalCount(totalCount);
		int totalPageCount = (totalCount - 1) / sizePerPage + 1;
		pageNavigation.setTotalPageCount(totalPageCount);
		boolean startRange = currentPage <= naviSize;
		pageNavigation.setStartRange(startRange);
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage;
		pageNavigation.setEndRange(endRange);
		int startPage = (currentPage - 1) / naviSize * naviSize + 1;
		int endPage = startPage + naviSize - 1;
		if (totalPageCount < endPage)
			endPage = totalPageCount;
		pageNavigation.setStartPage(startPage);
		pageNavigation.setEndPage(endPage);
		
		return pageNavigation;
	}

}
