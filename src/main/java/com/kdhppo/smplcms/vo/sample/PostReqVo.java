package com.kdhppo.smplcms.vo.sample;

import com.kdhppo.smplcms.cmn.sch.SearchVo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostReqVo extends SearchVo {
    private Long id;             // PK
    private String title;        // 제목
    private String content;      // 내용
    private String writer;       // 작성자
    private Boolean noticeYn;    // 공지글 여부
}