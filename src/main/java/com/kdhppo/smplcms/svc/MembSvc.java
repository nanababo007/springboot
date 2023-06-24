package com.kdhppo.smplcms.svc;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

	/**
	 * 회원 목록 조회
	 * @return 회원 목록
	 */
	public List<MembResVo> getMembList(MembReqVo membReqVo){
		return membDao.getMembList(membReqVo);
	}

	/**
	 * 회원 상세정보 조회
	 * @return 회원 상세정보
	 */
	public MembResVo getMembInfo(MembReqVo membReqVo) {
		Optional<MembResVo> menbInfoOpt = membDao.getMembInfo(membReqVo);
		MembResVo membInfo = menbInfoOpt.orElseThrow(() -> new UsernameNotFoundException(membReqVo.getMembId()+" is not member."));
		return membInfo;
	}

	/**
	 * 회원 정보 확인
	 * @return 회원 정보 확인 결과 건수
	 */
	public int checkMembInfo(MembReqVo membReqVo) {
		return membDao.checkMembInfo(membReqVo);
	}

	/**
	 * 회원 정보 등록
	 * @return 회원 정보 등록 결과 건수
	 */
	public int insertMembInfo(MembReqVo membReqVo) {
		return membDao.insertMembInfo(membReqVo);
	}

	/**
	 * 회원 정보 변경
	 * @return 회원 정보 변경 결과 건수
	 */
	public int updateMembInfo(MembReqVo membReqVo) {
		return membDao.updateMembInfo(membReqVo);
	}

	/**
	 * 회원 정보 삭제
	 * @return 회원 정보 삭제 결과 건수
	 */
	public int deleteMembInfo(MembReqVo membReqVo) {
		return membDao.deleteMembInfo(membReqVo);
	}

}