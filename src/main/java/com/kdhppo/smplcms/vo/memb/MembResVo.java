package com.kdhppo.smplcms.vo.memb;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MembResVo {
	private String membId;
	private String membNm;
	private String membPw;
	private String adminYn;
	private String delYn;
	private String useYn;
	private String joinDt;
	private String regdateStr;
	private String reguser;
	private String moddateStr;
	private String moduser;
}