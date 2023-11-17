package com.ssafy.happyhouse.model.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class NewsService {

	public List<String> getNewsList() {
		// [Test URL] : SBS News
		String URL = "https://youth.seoul.go.kr/site/main/customSupp/list?targetMulti=&ageMulti=&polDivSeoulMulti=PDS_11#n";

		// [Document] : Jsoup으로 가져온 HTML을 담을 객체
		Document doc = null;
		
		List<String> headlines = new ArrayList<>();

		try {
			// URL에 해당하는 HTML 전체 문서 가져오기
			doc = Jsoup.connect(URL).get();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 출력 변수 선언
		String title;
		String content;
		String date;
		String writer;

		// [Element] : Document의 HTML 요소
		// [Elements] : Element가 모인 자료형
		// 특정 값 추출 : css 선택문법을 이용, 태그를 검색하여 데이터를 가져온다.
		Elements newsHeadlines =  doc.select(".nclicks*");
		
        // 추출된 정보를 리스트에 추가
        for (Element headline : newsHeadlines) {
            headlines.add(headline.text());
        }
        
        return headlines;
	}
}
