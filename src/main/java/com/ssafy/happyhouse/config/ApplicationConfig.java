package com.ssafy.happyhouse.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

import com.ssafy.happyhouse.model.dao.BoardDao;
import com.ssafy.happyhouse.model.dao.MemberDao;

@Configuration
@MapperScan(basePackageClasses = {BoardDao.class, MemberDao.class})
public class ApplicationConfig {

}
