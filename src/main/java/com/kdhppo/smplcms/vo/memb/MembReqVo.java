package com.kdhppo.smplcms.vo.memb;

import com.kdhppo.smplcms.cmn.sch.SearchVo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MembReqVo extends SearchVo {

	//********** 로그인 관련 **********
	private String userId;
	private String userPw;

	//********** 회원정보 관련 **********
	private String membId;
	private String membNm;
	private String membPw;
	private String adminYn;
	private String delYn;
	private String useYn;
	private String joinDt;
	private String outDttm;
	private String regdateStr;
	private String reguser;
	private String moddateStr;
	private String moduser;

}