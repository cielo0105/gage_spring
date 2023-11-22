package com.ssafy.happyhouse.security.provider;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.ssafy.happyhouse.model.dto.MemberDto;
import com.ssafy.happyhouse.security.dto.TokenDto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class TokenProvider implements InitializingBean {

	private String secretKey = "secretkeysecretkeysecretkeysecretkeysecretkeysecretkeysecretkeysecretkeyeysecretkeysecretkeysecretkeyseysecretkeysecretkeysecretkeys";
	// 토큰 만료 시간
	private long tokenValidityInSeconds = 86400000;

	private Key key;

	@Override
	public void afterPropertiesSet() {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		this.key = Keys.hmacShaKeyFor(keyBytes);
	}

	public TokenDto createToken(Authentication authentication) {

		long now = (new Date()).getTime();
		Date validity = new Date(now + tokenValidityInSeconds);

		Map<String, Object> headerMap = new HashMap<>();
		headerMap.put("typ", "JWT");

		return new TokenDto(
				Jwts.builder().signWith(key, SignatureAlgorithm.HS512).setHeader(headerMap).setSubject("gage")
						.claim("id", authentication.getName()).setExpiration(validity).compact(),
				validity.toInstant().toEpochMilli());
	}

	public Authentication getAuthentication(String token) {
		Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
		
//		MemberDto principal = new MemberDto (claims.get("id").toString(), "");
		MemberDto principal = MemberDto.builder().userId(claims.get("id").toString()).build();

		return new UsernamePasswordAuthenticationToken(principal, token);
	}

	public boolean validateToken(String token) {
		Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
		return true;
	}
}
