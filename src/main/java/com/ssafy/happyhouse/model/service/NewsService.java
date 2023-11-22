package com.ssafy.happyhouse.model.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dao.ImportDao;
import com.ssafy.happyhouse.model.dao.NewsDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NewsService {
	private final NewsDao dao;
	
	public List<String> registNews() {
		
		// [Test URL] : SBS News
//		String URL = "https://youth.seoul.go.kr/site/main/customSupp/list?targetMulti=&ageMulti=&polDivSeoulMulti=PDS_11#n";
		String URL = "https://search.naver.com/search.naver?where=news&sm=tab_jum&query=%EC%9E%90%EC%98%81%EC%97%85%EC%9E%90";

		// [Document] : Jsoup으로 가져온 HTML을 담을 객체
		Document doc = null;
		
		List<String> headlines = new ArrayList<>();
//		dao.deleteNews(); // 뉴스 초기화
		try {
			// URL에 해당하는 HTML 전체 문서 가져오기
			doc = Jsoup.connect(URL).get();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 출력 변수 선언
		String title;
		String url;

		// [Element] : Document의 HTML 요소
		// [Elements] : Element가 모인 자료형
		// 특정 값 추출 : css 선택문법을 이용, 태그를 검색하여 데이터를 가져온다.
		Elements newsHeadlines =  doc.select(".news_tit");
		
        // 추출된 정보를 리스트에 추가
        for (Element headline : newsHeadlines) {
        	title = headline.attr("title");
        	url = headline.attr("href");
        	dao.registNews(title,url);
//        	crawlPage(headline.attr("href"));
//            headlines.add(headline.text());
        }
        
        return headlines;
	}
	
public List<String> registInfo() {
		
//		String URL = "https://www.k-startup.go.kr/web/contents/startupnews.do";
		String URL = "https://www.youthcenter.go.kr/youngPlcyUnif/youngPlcyUnifList.do?pageIndex=1&frameYn=&bizId=&dtlOpenYn=&plcyTpOpenTy=&plcyCmprInfo=&srchWord=%EC%B0%BD%EC%97%85&srchPlcyTp=023010&chargerOrgCdAll=&srchAge=&trgtJynEmp=&trgtJynEmp=&srchSortOrder=1&pageUnit=12#";
		String frontURL = "https://www.youthcenter.go.kr/youngPlcyUnif/youngPlcyUnifDtl.do?pageIndex=1&frameYn=&bizId=";
		String backURL = "&dtlOpenYn=&plcyTpOpenTy=&plcyCmprInfo=&srchWord=창업&srchPlcyTp=023010&chargerOrgCdAll=&srchAge=&trgtJynEmp=&trgtJynEmp=&srchSortOrder=1&pageUnit=12";
		// [Document] : Jsoup으로 가져온 HTML을 담을 객체
		Document doc = null;
		
		List<String> headlines = new ArrayList<>();
//		dao.deleteInfo(); // 뉴스 초기화
		try {
			// URL에 해당하는 HTML 전체 문서 가져오기
			doc = Jsoup.connect(URL).get();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 출력 변수 선언
		String title;
		String id;
		String url;

		// 특정 값 추출 : css 선택문법을 이용, 태그를 검색하여 데이터를 가져온다.
		Elements newsHeadlines =  doc.select(".tit-wrap");
		
        // 추출된 정보를 리스트에 추가
        for (Element headline : newsHeadlines) {
        	id = headline.select("a").attr("id").replace("sldLink_", "").replace("dtlLink_", "");
        	title = headline.text();
        	url = frontURL + id + backURL;

        	dao.registInfo(title,url);
        }
        
        return headlines;
	}
	
public List<String> registSupport() {
	
	// 창업 길라잡이
	String URL = "https://www.k-startup.go.kr/web/contents/webFND_GUIDE.do?page=1&viewCount=16&id=160514&schBdcode=&schGroupCode=&bdExt9=&bdExt10=&bdExt11=&bdUseyn=";
	String frontURL = "https://www.k-startup.go.kr/web/contents/webFND_GUIDE.do?page=1&viewCount=16&id=";
	String backURL = "&schBdcode=&schGroupCode=&bdExt9=&bdExt10=&bdExt11=&bdUseyn=&schM=view";
	String imgURL = "https://www.k-startup.go.kr/";
	// [Document] : Jsoup으로 가져온 HTML을 담을 객체
	Document doc = null;
	
	List<String> headlines = new ArrayList<>();
//	dao.deleteInfo(); // 뉴스 초기화
	try {
		// URL에 해당하는 HTML 전체 문서 가져오기
		doc = Jsoup.connect(URL).get();
	} catch (IOException e) {
		e.printStackTrace();
	}

	// 출력 변수 선언
	String title;
	String url;
	String img;
	String id = null;
	
	Pattern pattern = Pattern.compile("\\d+");  // 정규표현식: \d+ (하나 이상의 숫자)
    Matcher matcher;  
	// 특정 값 추출 : css 선택문법을 이용, 태그를 검색하여 데이터를 가져온다.
	Elements newsHeadlines =  doc.select(".gallery_list").select("a");
    // 추출된 정보를 리스트에 추가
    for (Element headline : newsHeadlines) {
    	matcher = pattern.matcher(headline.attr("onclick"));
    	 while (matcher.find()) {
            id = matcher.group();
         }
    	 url = frontURL + id + backURL;
    	 title = headline.select(".gallery_tit").text();
    	 img = imgURL + headline.select("img").attr("src");
    	 System.out.println(url);
    	 System.out.println(title);
    	 System.out.println(img);
    	dao.registSupport(title,url, img);
    }
    
    return headlines;
}
	
	private static void crawlPage(String url) {
		try {
            // Jsoup을 사용하여 해당 페이지의 HTML 문서 가져오기
            Document page = Jsoup.connect(url).get();
            
            // 가져온 HTML 문서를 필요에 따라 처리
            String pageContent = page.select(".detail").html();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	public List<Map<String, String>> getNews() {
		System.out.println("뉴스 가져오기");
		return dao.getNews();
	}

	public List<Map<String, String>> getInfo() {
		System.out.println("청년 정책");
		return dao.getInfo();
	}

	public List<Map<String, String>> getSupport() {
		System.out.println("청년 지원");
		return dao.getSupport();
	}

}
