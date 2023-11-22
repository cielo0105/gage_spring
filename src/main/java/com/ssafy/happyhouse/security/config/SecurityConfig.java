package com.ssafy.happyhouse.security.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.ssafy.happyhouse.security.filter.JWTFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig {

	private final AuthenticationProvider authenticationProvider;
	private final JWTFilter jwtFilter;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {

		security.csrf().disable().headers().frameOptions().disable();

		security.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		security.authorizeHttpRequests().requestMatchers(new AntPathRequestMatcher("/chat/**"), new AntPathRequestMatcher("/deal/regist/**")).authenticated()
				.anyRequest().permitAll();

		security.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

		security.formLogin().disable();
		security.httpBasic().disable();

		return security.build();
	}

	@Bean
	public AuthenticationManager authenticationManagerBean() {
		List<AuthenticationProvider> authenticationProviderList = new ArrayList<>();
		authenticationProviderList.add(authenticationProvider);
		ProviderManager authenticationManager = new ProviderManager(authenticationProviderList);
		authenticationManager.setAuthenticationEventPublisher(defaultAuthenticationEventPublisher());
		return authenticationManager;
	}

	@Bean
	DefaultAuthenticationEventPublisher defaultAuthenticationEventPublisher() {
		return new DefaultAuthenticationEventPublisher();
	}
}