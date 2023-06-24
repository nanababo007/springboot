package com.kdhppo.smplcms.dao;

import java.util.List;
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
	Optional<MembResVo> getMembInfo(MembReqVo membReqVo);

	/**
	 * 회원 목록 조회
	 * @return 회원 목록
	 */
	List<MembResVo> getMembList(MembReqVo membReqVo);

	/**
	 * 회원 정보 확인
	 * @return 회원 정보 확인 결과 건수
	 */
	int checkMembInfo(MembReqVo membReqVo);

	/**
	 * 회원 정보 등록
	 * @return 회원 정보 등록 결과 건수
	 */
	int insertMembInfo(MembReqVo membReqVo);

	/**
	 * 회원 정보 변경
	 * @return 회원 정보 변경 결과 건수
	 */
	int updateMembInfo(MembReqVo membReqVo);

	/**
	 * 회원 정보 삭제
	 * @return 회원 정보 삭제 결과 건수
	 */
	int deleteMembInfo(MembReqVo membReqVo);

}