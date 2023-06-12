package com.kdhppo.smplcms.vo.memb;

import lombok.Getter;
import lombok.Setter;

/**
 * 로그인 관련 요청 vo
 */
@Getter
@Setter
public class MembLoginReqVo {
	private String userId;
	private String userPw;
	private String token;
}