package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.ssafy.happyhouse.model.dto.IndicatorDto;

public interface ReportService {

	Map<String, Long> getLocalPeopleRank(String code);

	List<Map<String, Long>> getGageRank(String code, String dong);

	IndicatorDto getIndicator(String code);

}
