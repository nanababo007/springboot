package com.kdhppo.smplcms.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReqUtilClass {

	/**
	 * 파라미터를 포함한 url 을 반환
	 * @param req : 요청 객체
	 * @param url : url 문자열
	 * @param paramNames : 파라미터 이름들(request 에서 읽어서 url 에 붙임, 콤마구분 문자열)
	 * @return 파라미터를 포함한 url
	 */
	public static String getReqUrl(HttpServletRequest req, String url, String paramNames){
		return getReqUrl(req, url, paramNames, "utf8");
	}

	/**
	 * 파라미터를 포함한 url 을 반환
	 * @param req : 요청 객체
	 * @param url : url 문자열
	 * @param paramNames : 파라미터 이름들(request 에서 읽어서 url 에 붙임, 콤마구분 문자열)
	 * @param charset : utf8, euckr
	 * @return 파라미터를 포함한 url
	 */
	public static String getReqUrl(HttpServletRequest req, String url, String paramNames, String charset){
		StringBuffer result = new StringBuffer();
		String divStr = "";

		//url 값 기본처리.
		url = UtilClass.nvl(url).trim();
		//url 값 결과값에 셋팅.
		result.append(url);
		//url 파라미터 구분 문자열
		divStr = url.contains("?") ? "&" : "?";
		//인코딩 캐릭터셋
		charset = UtilClass.nvl(charset,"utf8").trim();

		//파라미터 이름 배열 루프, url 에 파라미터 반영.
		if(UtilClass.isNotEmpty(result) && UtilClass.isNotEmpty(paramNames)) {
			String[] paramNamesArr = paramNames.split(",");
			for (String paramName : paramNamesArr) {
				if(UtilClass.isNotEmpty(paramName)) {
					paramName = paramName.trim();

					String paramValue = UtilClass.nvl(req.getParameter(paramName));

					//파라미터 값 url 인코딩 처리
					try {
						paramValue = URLEncoder.encode(paramValue,charset);
					} catch (UnsupportedEncodingException e) {
						//e.printStackTrace();
						log.info("getReqUrl method, "+paramName+" parameter, url encode error.");
						paramValue = "";
					}

					//url 뒤에다가 파라미터 문자열 붙이기
					if(UtilClass.isNotEmpty(paramValue)) {
						result.append(divStr);
						result.append(paramValue);

						//구분 문자열이 ? 이면, 다음부터는 & 로 변경.
						if("?".equals(divStr)) {
							divStr = "&";
						}
					}
				}
			}
		}

		return result.toString();
	}

	/**
	 * 파라미터를 포함한 url 을 반환
	 * @param req : 요청 객체
	 * @param url : url 문자열
	 * @param paramNames : 파라미터 이름들(request 에서 읽어서 url 에 붙임, 콤마구분 문자열)
	 * @return 파라미터를 포함한 url
	 */
	public static String getReqUrl(Map<String,String> map, String url, String paramNames){
		return getReqUrl(map, url, paramNames, "utf8");
	}

	/**
	 * 파라미터를 포함한 url 을 반환
	 * @param req : 요청 객체
	 * @param url : url 문자열
	 * @param paramNames : 파라미터 이름들(request 에서 읽어서 url 에 붙임, 콤마구분 문자열)
	 * @param charset : utf8, euckr
	 * @return 파라미터를 포함한 url
	 */
	public static String getReqUrl(Map<String,String> map, String url, String paramNames, String charset){
		StringBuffer result = new StringBuffer();
		String divStr = "";

		//url 값 기본처리.
		url = UtilClass.nvl(url).trim();
		//url 값 결과값에 셋팅.
		result.append(url);
		//url 파라미터 구분 문자열
		divStr = url.contains("?") ? "&" : "?";
		//인코딩 캐릭터셋
		charset = UtilClass.nvl(charset,"utf8").trim();

		//파라미터 이름 배열 루프, url 에 파라미터 반영.
		if(UtilClass.isNotEmpty(result) && UtilClass.isNotEmpty(paramNames)) {
			String[] paramNamesArr = paramNames.split(",");
			for (String paramName : paramNamesArr) {
				if(UtilClass.isNotEmpty(paramName)) {
					paramName = paramName.trim();

					String paramValue = UtilClass.nvl(map.get(paramName));

					//파라미터 값 url 인코딩 처리
					try {
						paramValue = URLEncoder.encode(paramValue,charset);
					} catch (UnsupportedEncodingException e) {
						//e.printStackTrace();
						log.info("getReqUrl method, "+paramName+" parameter, url encode error.");
						paramValue = "";
					}

					//url 뒤에다가 파라미터 문자열 붙이기
					if(UtilClass.isNotEmpty(paramValue)) {
						result.append(divStr);
						result.append(paramValue);

						//구분 문자열이 ? 이면, 다음부터는 & 로 변경.
						if("?".equals(divStr)) {
							divStr = "&";
						}
					}
				}
			}
		}

		return result.toString();
	}

}