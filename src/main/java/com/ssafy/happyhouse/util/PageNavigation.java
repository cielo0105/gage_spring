package com.ssafy.happyhouse.util;

import java.sql.Date;

import com.ssafy.happyhouse.model.dto.BoardDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageNavigation {

	private int totalCount; // 총 게시글 갯수
	private int currentPage; // 현재 페이지 번호
	private int totalPageCount; // 총 페이지 갯수
	
	private int startPage;
	private int endPage;

	private int naviSize; // 네비게이션 사이즈
	private int countPerPage; // 페이지당 글 갯수
	private boolean startRange; // 현재 페이지가 이전이 눌려지지 않는 범위의 페이지 체크
	private boolean endRange; // 현재 페이지가 다음이 눌려지지 않는 범위의 페이지 체크

	private String navigator;
}
