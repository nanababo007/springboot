package com.kdhppo.smplcms.svc;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kdhppo.smplcms.dao.MembDao;
import com.kdhppo.smplcms.vo.memb.MembReqVo;
import com.kdhppo.smplcms.vo.memb.MembResVo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MembSvc {

	private final MembDao membDao;

	/**
	 * 회원 상세정보 조회
	 * @param userId - 사용자 아이디
	 * @return 회원 상세정보
	 */
	public Optional<MembResVo> getMembInfoById(final String userId) {
		return getMembInfoById(userId, false);
	}

	/**
	 * 회원 상세정보 조회
	 * @param userId - 사용자 아이디
	 * @param isGetPw - 비밀번호를 가져올지 여부 true/false
	 * @return 회원 상세정보
	 */
	public Optional<MembResVo> getMembInfoById(final String userId, final boolean isGetPw) {
		MembReqVo membReqInfo = new MembReqVo();
		//비밀번호 조회 여부. 가져오게.
		if(isGetPw) {membReqInfo.setUserPw("1");}
		membReqInfo.setMembId(userId);
		return membDao.getMembInfo(membReqInfo);
	}

}