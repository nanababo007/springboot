package com.kdhppo.smplcms.cmn.auth;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.kdhppo.smplcms.svc.MembSvc;
import com.kdhppo.smplcms.vo.memb.MembResVo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 사이트 로그인 후에, 성공 실패 시 처리 메서드 정의.
 */
@Component
public class SiteAuthAfterProc implements AuthenticationSuccessHandler, AuthenticationFailureHandler {
    @Autowired
	private MembSvc membSvc;

	/**
	 * 로그인 성공 시 처리.
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException, ServletException {
		String userId = auth.getName();
		//String userIp = request.getRemoteAddr();
		try {
			//로그인한 회원 정보 구하기.
			Optional<MembResVo> menbInfoOpt = membSvc.getMembInfoById(userId);
			MembResVo membInfo = menbInfoOpt.orElseThrow(() -> new UsernameNotFoundException(userId+" is not member."));

			//로그인한 회원 정보 세션 저장.
			SiteAuthInfo.setLoginUserInfo(request, membInfo);

			//메인 페이지로 이동 처리.
			response.sendRedirect("/main/index.do");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 로그인 실패 시 처리.
	 */
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException auth) throws IOException, ServletException {
		//String userId = auth.getName();
		//String userIp = request.getRemoteAddr();
		//System.out.println("login fail : "+ip + "" + user_id);
		//response.sendRedirect("/");
	}

}