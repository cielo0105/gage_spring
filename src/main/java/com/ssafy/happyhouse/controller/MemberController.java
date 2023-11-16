package com.ssafy.happyhouse.controller;

import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.ssafy.happyhouse.model.dto.MemberDto;
import com.ssafy.happyhouse.model.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = { "*" }, methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.POST }, maxAge = 6000)
@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
public class MemberController {
	private final MemberService service;

	@PostMapping("/regist")
	public ResponseEntity<Map<String, Object>> regist(@RequestBody Map<String, String> userData) {
		MemberDto memberDto = new MemberDto();
		System.out.println(userData);
		memberDto.setUserId(userData.get("userId"));
		memberDto.setUserPass(userData.get("userPass"));
		memberDto.setUserName(userData.get("userName"));
		try {
			service.registerMember(memberDto);
			return handleSuccess(memberDto);
		} catch (DuplicateKeyException e) {
			return handleFailure("이미 존재하는 회원입니다.", HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return handleFailure("잠시 후 다시 시도해주세요.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> userData, HttpSession session) {
		String userId = userData.get("userId");
		String userPass = userData.get("userPass");
		MemberDto result = service.login(userId, userPass);
		System.out.println(userData);
		if (result != null) {
			session.setAttribute("user", result);
			return handleSuccess(result);
		} else
			return handleFailure("회원 정보가 일치하지 않습니다.", HttpStatus.UNAUTHORIZED);
	}

	@PostMapping("/logout")
	public ResponseEntity<Map<String, Object>> logout(HttpSession session) {
		session.invalidate();
		return handleSuccess("로그아웃 성공");
	}

	@PostMapping("/findpw1")
	public ResponseEntity<Map<String, Object>> findpw1(@RequestParam String userId, HttpSession session) {
		MemberDto result = service.getInfo(userId);
		session.setAttribute("findid", result);
		return handleSuccess(result);
	}

	@PostMapping("/findpw3")
	public ResponseEntity<Map<String, Object>> findpw3(@RequestParam String userPass, HttpSession session) {
		MemberDto user = (MemberDto) session.getAttribute("findid");
		user.setUserPass(userPass);
		service.modifyMember(user);
		return handleSuccess("비밀번호 변경 성공");
	}

	@GetMapping("/info/{userId}")
	public ResponseEntity<Map<String, Object>> info(@PathVariable String userId) {
		return handleSuccess(service.getInfo(userId));
	}

	@PutMapping("/edit")
	public ResponseEntity<Map<String, Object>> modify(@RequestBody MemberDto memberDto, HttpSession session) {

		System.out.println("00000000000000000000000000000000" + memberDto.getUserName());
		service.modifyMember(memberDto);
		session.setAttribute("user", memberDto);
		return handleSuccess(memberDto);
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<Map<String, Object>> delete(@PathVariable String userId, HttpSession session) {
		service.deleteMember(userId);
		session.invalidate();
		return handleSuccess(userId);
	}

	private ResponseEntity<Map<String, Object>> handleSuccess(Object data) {
		Map<String, Object> result = new HashMap<>();
		result.put("success", true);
		result.put("data", data);
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}

	private ResponseEntity<Map<String, Object>> handleFailure(String msg, HttpStatus status) {
		Map<String, Object> result = new HashMap<>();
		result.put("success", false);
		result.put("msg", msg);
		return new ResponseEntity<Map<String, Object>>(result, status);
	}
}