package com.kdhppo.smplcms.util;

import java.util.Map;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResUtilClass {

	/**
	 * 일반 오류 응답 반환
	 */
	public static ResponseEntity<Map<String,Object>> getCmnErrRes(String errMsg, Logger log, Map<String,Object> data){
		return getCmnErrRes(errMsg, log, data, null);
	}

	/**
	 * 일반 오류 응답 반환
	 */
	public static ResponseEntity<Map<String,Object>> getCmnErrRes(String errMsg, Logger log, Map<String,Object> data, Exception e){
		String errDtlMsg = e!=null ? e.getMessage() : "";

		if(log!=null) {
			log.info(errDtlMsg);
		}

		data.put("error_message", errMsg);
		data.put("error_message_detail", errDtlMsg);

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(data);
	}

}