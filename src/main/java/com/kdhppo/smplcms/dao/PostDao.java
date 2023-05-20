package com.kdhppo.smplcms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kdhppo.smplcms.vo.sample.PostReqVo;
import com.kdhppo.smplcms.vo.sample.PostResVo;

@Mapper
public interface PostDao {

	/**
	 * 게시글 저장
	 * @param params - 게시글 정보
	 */
	void save(PostReqVo params);

	/**
	 * 게시글 상세정보 조회
	 * @param id - PK
	 * @return 게시글 상세정보
	 */
	PostResVo findById(Long id);

	/**
	 * 게시글 수정
	 * @param params - 게시글 정보
	 */
	void update(PostReqVo params);

	/**
	 * 게시글 삭제
	 * @param id - PK
	 */
	void deleteById(Long id);

	/**
	 * 게시글 리스트 조회
	 * @return 게시글 리스트
	 */
	List<PostResVo> findAll();

	/**
	 * 게시글 수 카운팅
	 * @return 게시글 수
	 */
	int count();

}