package com.kdhppo.smplcms.cmn.sch;

import lombok.Getter;
import lombok.Setter;

/**
 * 페이징 및 검색 관련 공통 VO
 */
@Getter
@Setter
public class SearchVo {
	//페이징 관련.
	int pageSize;
	int pageNo;
	int startNum;
	int endNum;

	//검색 관련.
	String schKind = "";
	String schVal = "";
}