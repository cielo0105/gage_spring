package com.ssafy.happyhouse.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.BoardDto;
import com.ssafy.happyhouse.model.dto.MemberDto;
import com.ssafy.happyhouse.model.service.BoardService;
import com.ssafy.happyhouse.util.PageNavigation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE} , maxAge = 6000)
@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
@Slf4j
public class BoardController {
	
	private final BoardService service;
	
	private int pgno;
	private String key;
	private String word;
	private String queryStrig;

	@GetMapping("/")
	public ResponseEntity<Map<String, Object>> test() {
		return handleSuccess("성공");
	}
	
	@PostMapping("/regist")
    public ResponseEntity<Map<String, Object>> regist(@RequestBody Map<String, String> boardData) {
    	BoardDto boardDto = new BoardDto();
    	System.out.println("boardData: "+boardData);
    	boardDto.setUserId(boardData.get("userId"));
    	boardDto.setSubject(boardData.get("subject"));
    	boardDto.setContent(boardData.get("content"));
        service.registerArticle(boardDto);
        return handleSuccess(boardDto);
    }
	
	@GetMapping("/list")
	public ResponseEntity<Map<String, Object>> list(@RequestParam int pgno, @RequestParam String key, @RequestParam String word) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("pgno", pgno+"");
		map.put("key", key);
		map.put("word", word);
		
		List<BoardDto> list = service.listArticle(map);
		PageNavigation pageNavigation = service.makePageNavigation(map);
		Map<String, Object> result = new HashMap();
		result.put("list", list);
		result.put("pageNavigation", pageNavigation);
		return handleSuccess(result);
	}
	
	@GetMapping("/view/{no}")
	public ResponseEntity<Map<String, Object>> view(@PathVariable int no) {
		return handleSuccess(service.viewArticle(no));
	}
	
	@PutMapping("/edit")
    public ResponseEntity<Map<String, Object>> modify(@RequestBody BoardDto boardDto, HttpSession session) {
    	
    	System.out.println("00000000000000000000000000000000"+boardDto.getArticleNo());
        service.modifyArticle(boardDto);
        return handleSuccess(boardDto);
    }
    
    @DeleteMapping("/{articleNo}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable int articleNo, HttpSession session) {
    	System.out.println("1111111111111111111111"+articleNo);
        service.deleteArticle(articleNo);
        return handleSuccess(articleNo);
    }
	
	private ResponseEntity<Map<String, Object>> handleSuccess(Object data) {
		Map<String, Object> result = new HashMap<>();
		result.put("success", true);
		result.put("data", data);
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}
}
