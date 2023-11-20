package com.ssafy.happyhouse.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.happyhouse.model.dto.DealDto;
import com.ssafy.happyhouse.model.service.DealService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE} , maxAge = 6000)
@RestController
@RequestMapping("/deal")
@RequiredArgsConstructor
@Slf4j
public class DealController {
	
	private final DealService service;
	
    @PostMapping("/regist")
    public ResponseEntity<Map<String, Object>> regist(@RequestBody Map<String, String> data){
    	DealDto dealDto = new DealDto();
    	dealDto.setType(data.get("type"));
    	dealDto.setAddress(data.get("address"));
    	dealDto.setAddressDetail(data.get("addressDetail"));
    	dealDto.setArea(Double.parseDouble(data.get("area")));
    	dealDto.setRecommend(data.get("recommend"));
    	dealDto.setFloor(Integer.parseInt(data.get("floor")));
    	dealDto.setFloorAll(Integer.parseInt(data.get("floorAll")));
    	dealDto.setDesc(data.get("desc"));
    	dealDto.setImg(data.get("img"));
    	dealDto.setAmount1(Integer.parseInt(data.get("amount1")));
    	if(data.get("amount2")!=null) {
    		dealDto.setAmount2(Integer.parseInt(data.get("amount2")));
    	}
    	dealDto.setLat(data.get("lat"));
    	dealDto.setLon(data.get("lon"));
    	System.out.println(dealDto);
    	return handleSuccess(service.regist(dealDto));
    }
    
    @PostMapping(value = "/img", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Map<String, Object>> uploadImg(@RequestParam("gage-img") MultipartFile img) throws IllegalStateException, IOException{
    	System.out.println(img.getOriginalFilename());
    	String uploadRootPath = "C:/ssafygageimg/";
    	
    	String originalFilename = img.getOriginalFilename();
    	
        String uploadFileName = UUID.randomUUID() + "_" + originalFilename;
        
        File uploadFile = new File(uploadRootPath + File.separator + uploadFileName);
        //1-b. 파일을 해당 경로에 업로드
        img.transferTo(uploadFile);

        String savePath= uploadFile.getPath();
        System.out.println(savePath);
    	return handleSuccess(savePath);
    }
    
    
	
	private ResponseEntity<Map<String, Object>> handleSuccess(Object data) {
		Map<String, Object> result = new HashMap<>();
		result.put("success", true);
		result.put("data", data);
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}
}
