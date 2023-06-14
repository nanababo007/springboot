package com.kdhppo.smplcms.cmn;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.kdhppo.smplcms.cmn.auth.SiteAuthInfo;
import com.kdhppo.smplcms.vo.memb.MembResVo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * 페이지 공통 인터셉터
 * 페이지 실행 시, 공통으로 실행되어야 할 내용.
 * 스프링 빈 자동 주입 가능함. (인터셉터 등록 시, 컴포넌트를 자동 주입해서 생성해서 가능함)
 */
@Slf4j
@Component
public class PageItc implements HandlerInterceptor {

	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//log.info("interceptor preHandle started ...");

		//사용자 로그인 정보 설정
		MembResVo membResVo = SiteAuthInfo.getLoginUserInfo(request);
		if(membResVo!=null) {
			request.setAttribute("membId", membResVo.getMembId());
			request.setAttribute("membNm", membResVo.getMembNm());
		}

		//로그인 토큰 값 설정
		String token = SiteAuthInfo.getLoginUserToken(request);
		request.setAttribute("loginToken", token);

		//log.info("interceptor preHandle ended ...");

		return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    	HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    	//log.info("interceptor postHandle started ...");
    	//log.info("interceptor postHandle ended ...");
    }

}