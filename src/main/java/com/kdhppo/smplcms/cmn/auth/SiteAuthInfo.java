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

}