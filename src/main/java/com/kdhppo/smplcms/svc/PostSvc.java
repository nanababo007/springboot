package com.kdhppo.smplcms.svc;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kdhppo.smplcms.dao.PostDao;
import com.kdhppo.smplcms.vo.sample.PostReqVo;
import com.kdhppo.smplcms.vo.sample.PostResVo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostSvc {

    private final PostDao postDao;

    /**
     * 게시글 저장
     * @param params - 게시글 정보
     * @return Generated PK
     */
    @Transactional
    public Long savePost(final PostReqVo  params) {
        postDao.save(params);
        return params.getId();
    }

    /**
     * 게시글 상세정보 조회
     * @param id - PK
     * @return 게시글 상세정보
     */
    public PostResVo findPostById(final Long id) {
        return postDao.findById(id);
    }

    /**
     * 게시글 수정
     * @param params - 게시글 정보
     * @return PK
     */
    @Transactional
    public Long updatePost(final PostReqVo params) {
        postDao.update(params);
        return params.getId();
    }

    /**
     * 게시글 삭제
     * @param id - PK
     * @return PK
     */
    public Long deletePost(final Long id) {
        postDao.deleteById(id);
        return id;
    }

    /**
     * 게시글 리스트 조회
     * @return 게시글 리스트
     */
    public List<PostResVo> findAllPost() {
        return postDao.findAll();
    }

}