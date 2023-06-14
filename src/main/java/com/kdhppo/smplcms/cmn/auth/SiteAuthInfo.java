package com.kdhppo.smplcms.cmn.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.kdhppo.smplcms.svc.MembSvc;
import com.kdhppo.smplcms.util.UtilClass;
import com.kdhppo.smplcms.vo.memb.MembResVo;

import jakarta.servlet.http.HttpServletRequest;

/**
 * 인증된 사용자 정보.
 */
@Component
public class SiteAuthInfo {
	/** 로그인한 회원 아이디 저장, 키 이름 */
	private static final String cstLoginIdKeyName = "loginId";
	/** 로그인한 회원 정보 저장, 키 이름 */
	private static final String cstLoginInfoKeyName = "loginInfo";
	/** 로그인한 회원 토큰, 키 이름 */
	private static final String cstLoginTokenKeyName = "loginToken";

	@Autowired
	private MembSvc membSvc;

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

	/**
	 * 시큐리티 사용자 정보 반환
	 */
	public static UserDetails getSecurityUserDetails() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = (UserDetails)principal;
		//userDetails.getUsername();
		//userDetails.getPassword();
		return userDetails;
	}

	/**
	 * 시큐리티 사용자 이름 반환
	 */
	public static String getSecurityUsername() {
		UserDetails userDetails = getSecurityUserDetails();
		return userDetails.getUsername();
	}

	/**
	 * 시큐리티 사용자 아이디로 디비 조회한 전체 사용자 정보 반환
	 *
	 *@Autowired SiteAuthInfo siteAuthInfo;
	 * MembResVo membResVo = siteAuthInfo.getSecurityUserInfoFromDB();
	 * System.out.println("security userNm ::: "+membResVo.getMembNm());
	 */
	public MembResVo getSecurityUserInfoFromDB() {
		//시큐리티에 저장된 회원 아이디 정보.
		String userId = getSecurityUsername();

		//로그인한 회원 정보 구하기.
		Optional<MembResVo> menbInfoOpt = membSvc.getMembInfoById(userId);
		MembResVo membInfo = menbInfoOpt.orElseThrow(() -> new UsernameNotFoundException(userId+" is not member."));

		return membInfo;
	}

}