package com.kdhppo.smplcms.ctl.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kdhppo.smplcms.util.ResUtilClass;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * 샘플 관련 API
 */
@Slf4j
@RestController
@RequestMapping("/api/smpl")
public class SmplApiCtl {

	/**
	 * 샘플 api
	 */
	@PostMapping("/smpl.do")
	public ResponseEntity<Map<String,Object>> tokenNew(HttpServletRequest request) {
		Map<String,Object> data = new HashMap<String,Object>();

		try {

			//API 응답 출력값 설정
			data.put("aaa", "kkk");

		} catch (Exception e) {
			return ResUtilClass.getCmnErrRes("not_known", log, data, e);
		}

		return ResponseEntity.status(HttpStatus.OK).body(data);
	}

}