package com.kdhppo.smplcms.ctl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/bord")
public class BordCtl {

	//공지사항 목록 페이지.
	@GetMapping("/noti.do")
	public String noti(Model model) {
		return "bord/noti";
	}

	//FAQ 목록 페이지.
	@GetMapping("/faq.do")
	public String faq(Model model) {
		return "bord/faq";
	}

	//QnA 목록 페이지.
	@GetMapping("/qna.do")
	public String qna(Model model) {
		return "bord/qna";
	}

	//이미지 게시판 목록 페이지.
	@GetMapping("/img.do")
	public String img(Model model) {
		return "bord/img";
	}

	//등록 페이지.
	@GetMapping("/reg.do")
	public String reg(Model model) {
		return "bord/reg";
	}

}