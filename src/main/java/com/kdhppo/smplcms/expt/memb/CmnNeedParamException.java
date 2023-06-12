package com.kdhppo.smplcms.expt.memb;

import com.kdhppo.smplcms.util.UtilClass;

public class CmnNeedParamException extends Exception {

	private static final long serialVersionUID = 1L;

	public CmnNeedParamException(String msg) {
		super(msg);
	}

	/**
	 * @param msg 오류 메시지
	 * @param params 파라미터 이름 목록 콤마구분 문자열 ("param1,param2,...")
	 */
	public CmnNeedParamException(String msg, String params) {
		super(getExtMsg(msg,params));
	}

	/**
	 * 조합 오류 메시지 문자열 반환
	 * @param msg 오류 메시지
	 * @param params 파라미터 이름 목록 콤마구분 문자열 ("param1,param2,...")
	 */
	private static String getExtMsg(String msg, String params) {
		StringBuffer msgBuf = new StringBuffer();

		if(UtilClass.isNotEmpty(params)) {
			msgBuf.append(msg);
			msgBuf.append("\n");
			msgBuf.append("\n필요 파라미터 : ["+params+"]");
		}

		return msgBuf.toString();
	}

}