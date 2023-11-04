package com.ssafy.happyhouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
	
	@GetMapping("/notice")
	public String notice() {
		return "notice/notice";
	}
	
	@GetMapping("/regist")
	public String regist() {
		return "member/register";
	}
	
	@GetMapping("/about")
	public String about() {
		return "about";
	}
	
	@GetMapping("/analysis")
	public String analysis() {
		return "analysis";
	}
	
	@GetMapping("/findpw1")
	public String findpw1() {
		return "member/findpw1";
	}
	
	@GetMapping("/info")
	public String info() {
		return "member/info";
	}
}
