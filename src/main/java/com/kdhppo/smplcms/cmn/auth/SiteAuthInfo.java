package com.kdhppo.smplcms.cmn.auth;

import com.kdhppo.smplcms.util.UtilClass;
import com.kdhppo.smplcms.vo.memb.MembResVo;

import jakarta.servlet.http.HttpServletRequest;

/**
 * 인증된 사용자 정보.
 */
public class SiteAuthInfo {
	/** 로그인한 회원 아이디 저장, 키 이름 */
	private static final String cstLoginIdKeyName = "loginId";
	/** 로그인한 회원 정보 저장, 키 이름 */
	private static final String cstLoginInfoKeyName = "loginInfo";
	/** 로그인한 회원 토큰, 키 이름 */
	private static final String cstLoginTokenKeyName = "loginToken";

	/**
	 * 현재 로그인 여부 반환.
	 */
	public static boolean isLogin(HttpServletRequest request) {
		if(UtilClass.isEmpty(getLoginUserId(request))){
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 현재 로그인한 회원 아이디를 구함.
	 */
	public static String getLoginUserId(HttpServletRequest request) {
		String loginId = "";

		loginId = UtilClass.nvl((String)request.getSession(true).getAttribute(cstLoginIdKeyName));

		return loginId;
	}

	/**
	 * 현재 로그인한 회원 정보를 구함.
	 */
	public static MembResVo getLoginUserInfo(HttpServletRequest request) {
		return (MembResVo)request.getSession(true).getAttribute(cstLoginInfoKeyName);
	}

	/**
	 * 현재 로그인한 회원 정보를 설정.
	 */
	public static void setLoginUserInfo(HttpServletRequest request, MembResVo membInfo) {
		request.getSession(true).setAttribute(cstLoginInfoKeyName, membInfo);
	}

	/**
	 * 현재 로그인한 회원 토큰을 구함.
	 */
	public static String getLoginUserToken(HttpServletRequest request) {
		return (String)request.getSession(true).getAttribute(cstLoginTokenKeyName);
	}

	/**
	 * 현재 로그인한 회원 토큰을 설정.
	 */
	public static void setLoginUserToken(HttpServletRequest request, String token) {
		request.getSession(true).setAttribute(cstLoginTokenKeyName, token);
	}

}