package com.kdhppo.smplcms.ctl.api;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.kdhppo.smplcms.cmn.auth.JwtTokenProvider;
import com.kdhppo.smplcms.expt.cmn.CmnNeedParamException;
import com.kdhppo.smplcms.expt.memb.MembLoginPwdNotMatchException;
import com.kdhppo.smplcms.expt.memb.MembLoginTokenInvalidException;
import com.kdhppo.smplcms.svc.MembSvc;
import com.kdhppo.smplcms.util.ResUtilClass;
import com.kdhppo.smplcms.util.UtilClass;
import com.kdhppo.smplcms.vo.memb.MembLoginReqVo;
import com.kdhppo.smplcms.vo.memb.MembResVo;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * 로그인 토큰 관련 API
 */
@Slf4j
@RestController
@RequestMapping("/api/memb/login/token")
public class MembLoginTokenApiCtl {
	@Autowired
	private MembSvc membSvc;
	@Autowired
	private JwtTokenProvider tokenProvider;

	/**
	 * 토큰 신규 발행
	 * 아이디 비밀번호를 받아서, 토큰을 신규 발행함
	 * @return 신규 발행 토큰 정보
	 */
	@PostMapping("/new.do")
	public ResponseEntity<Map<String,Object>> tokenNew(HttpServletRequest request, MembLoginReqVo membLoginReqVo) {
		Map<String,Object> data = new HashMap<String,Object>();

		//파라미터 정보 설정
		String userId = membLoginReqVo.getUserId();
		String userPw = membLoginReqVo.getUserPw();

		try {
			//유효성 체크
			if(UtilClass.isEmptiesOneMore(new String[]{userId,userPw})) {
				throw new CmnNeedParamException("need_param","userId,userPw");
			}

			//로그인한 회원 정보 구하기.
			Optional<MembResVo> menbInfoOpt = membSvc.getMembInfoById(userId,true);
			MembResVo membInfo = menbInfoOpt.orElseThrow(() -> new UsernameNotFoundException(userId+" is not member."));

			//비밀번호 확인.
			if(!userPw.equals(membInfo.getMembPw())) {
				throw new MembLoginPwdNotMatchException(userId+"'s password is not matched");
			}

			//토큰 발급 및 저장.
			String token = tokenProvider.createToken(userId);
			log.info("login user token created : "+token);

			//API 응답 출력값 설정
			data.put("userId", userId);
			data.put("token", token);

		} catch (CmnNeedParamException e) {
			return ResUtilClass.getCmnErrRes("need_param", log, data, e);
		} catch (UsernameNotFoundException e) {
			return ResUtilClass.getCmnErrRes("not_member", log, data, e);
		} catch (MembLoginPwdNotMatchException e) {
			return ResUtilClass.getCmnErrRes("password_not_match", log, data, e);
		}

		return ResponseEntity.status(HttpStatus.OK).body(data);
	}

	/**
	 * 토큰 재발행
	 * 기존에 발급 받았던 유효한 토큰을 받아서, 신규로 발행한 토큰으로 다시 재발행 해서 보내줌
	 * 기존에 발급 받았던 토큰이, 만료 시간이 가까워 지면, 다시 재발행 받아서, 만료 시간을 연장하는 요청
	 * @return 재발행 토큰 정보
	 */
	@PostMapping("/renew.do")
	public ResponseEntity<Map<String,Object>> tokenRenew(HttpServletRequest request, MembLoginReqVo membLoginReqVo) {
		Map<String,Object> data = new HashMap<String,Object>();

		try {
			//기존 발행 토큰.
			String token = membLoginReqVo.getToken();

			//파라미터 체크
			if(UtilClass.isEmptiesOneMore(new String[]{token})) {
				throw new CmnNeedParamException("need_param","token");
			}

			//기존 발행 토큰.
			data.put("oldToken",token);

			//토큰 유효성 체크
			if(tokenProvider.checkValidToken(token)==null) {
				throw new MembLoginTokenInvalidException("current user's token is not valid");
			}

			//기존 토큰에 저장된 회원 아이디 추출.
			String userId = tokenProvider.getSubjectFromToken(token);

			//토큰 재발급.
			String newToken = tokenProvider.createToken(userId);
			log.info("login user token created : "+newToken);

			//기존 발행 토큰.
			data.put("newToken",newToken);

		} catch (CmnNeedParamException e) {
			return ResUtilClass.getCmnErrRes("need_param", log, data, e);
		} catch (MembLoginTokenInvalidException e) {
			return ResUtilClass.getCmnErrRes("token_invalid", log, data, e);
		}

		return ResponseEntity.status(HttpStatus.OK).body(data);
	}

	/**
	 * 토큰 유효성 체크
	 * 기존에 발급 받았던 토큰을 받아서, 유효 여부와, 만료까지 남은 시간 및 만료 시간 등의 정보 반환
	 * @return 토큰 유효성 정보
	 */
	@PostMapping("/valid.do")
	public ResponseEntity<Map<String,Object>> tokenValid(HttpServletRequest request, MembLoginReqVo membLoginReqVo) {
		Map<String,Object> data = new HashMap<String,Object>();

		try {
			//기존 발행 토큰.
			String token = membLoginReqVo.getToken();

			//유효성 체크
			if(UtilClass.isEmptiesOneMore(new String[]{token})) {
				throw new CmnNeedParamException("need_param","token");
			}

			//기존 발행 토큰 출력 설정.
			data.put("token",token);

			//토큰 유효성 체크
			if(tokenProvider.checkValidToken(token)==null) {
				throw new MembLoginTokenInvalidException("current user's token is not valid");
			}

			//기존 토큰에 저장된 회원 아이디 추출.
			DecodedJWT djwt = tokenProvider.getDecodedToken(token);
			//만료 일시
			Date expDt = djwt.getExpiresAt();
			//만료 일시 문자열
			String expDtStr = UtilClass.getForamtDate("yyyy-MM-dd HH:mm:ss", expDt);
			//만료 남은 시간 (초)
			long expRemainSec = UtilClass.getDateDiffSec(expDt, new Date());

			//기존 발행 토큰.
			data.put("expiresAt",expDtStr);
			data.put("expRemainSec",expRemainSec);

		} catch (CmnNeedParamException e) {
			return ResUtilClass.getCmnErrRes("need_param", log, data, e);
		} catch (MembLoginTokenInvalidException e) {
			return ResUtilClass.getCmnErrRes("token_invalid", log, data, e);
		}

		return ResponseEntity.status(HttpStatus.OK).body(data);
	}

}