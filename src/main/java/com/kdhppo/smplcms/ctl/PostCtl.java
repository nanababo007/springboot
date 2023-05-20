package com.kdhppo.smplcms.ctl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kdhppo.smplcms.svc.PostSvc;
import com.kdhppo.smplcms.vo.sample.PostReqVo;
import com.kdhppo.smplcms.vo.sample.PostResVo;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PostCtl {
	private final Logger logger = LoggerFactory.getLogger(PostCtl.class);

    private final PostSvc postSvc;

    // 게시글 리스트 페이지
    @GetMapping("/sample/post/list.do")
    public String openPostList(Model model) {
        List<PostResVo> posts = postSvc.findAllPost();
        model.addAttribute("posts", posts);
        return "sample/post/list";
    }

    // 게시글 작성 페이지
    @GetMapping("/sample/post/write.do")
    public String openPostWrite(@RequestParam(value = "id", required = false) final Long id, Model model) {
        if (id != null) {
            PostResVo post = postSvc.findPostById(id);
            model.addAttribute("post", post);
        }
        return "sample/post/write";
    }

    // 신규 게시글 생성
    @PostMapping("/sample/post/save.do")
    public String savePost(final PostReqVo params) {
        postSvc.savePost(params);
        return "redirect:/sample/post/list.do";
    }

}