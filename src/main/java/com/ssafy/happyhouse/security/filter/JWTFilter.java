package com.ssafy.happyhouse.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ssafy.happyhouse.security.provider.TokenProvider;

import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

	public static final String AUTHORIZATION_HEADER = "Authorization";
	private final TokenProvider tokenProvider;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String jwt = resolveToken(request);
			// 만약 토큰에 이상이 있다면 오류가 발생한다.
			if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
				// tokenProvider에서 jwt를 가져가 Authentication 객체생성
				Authentication authentication = this.tokenProvider.getAuthentication(jwt);
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
			// 이상이 없다면 계속 진행
			filterChain.doFilter(request, response);
		} catch (JwtException e) {
			// 토큰에 오류가 있다면 401에러를 응답한다.
			log.error("[JWTExceptionHandlerFilter] " + e.getMessage());
			response.setStatus(401);
			response.setContentType("application/json;charset=UTF-8");
		}
	}

	private String resolveToken(HttpServletRequest request) {
		String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
		if (StringUtils.hasText(bearerToken) && StringUtils.startsWithIgnoreCase(bearerToken, "Bearer ")) {
			return bearerToken.substring(7);
		}
		return null;
	}

}
