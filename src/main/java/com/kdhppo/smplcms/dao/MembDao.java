package com.kdhppo.smplcms.dao;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.kdhppo.smplcms.vo.memb.MembReqVo;
import com.kdhppo.smplcms.vo.memb.MembResVo;

@Mapper
public interface MembDao {

	/**
	 * 회원 상세정보 조회
	 * @param userId - 사용자 아이디
	 * @return 회원 상세정보
	 */
	Optional<MembResVo> getMembInfo(MembReqVo membReqInfo);

}