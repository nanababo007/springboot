package com.kdhppo.smplcms.ctl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/memb")
public class MembCtl {

	//회원 목록 페이지.
	@GetMapping("/list.do")
	public String membList(Model model) {
		return "memb/list";
	}

	//회원 등록 페이지.
	@GetMapping("/reg.do")
	public String membRegForm(Model model) {
		return "memb/reg";
	}

	//회원 등록 처리 페이지.
	@PostMapping("/reg.do")
	public String membRegProc(Model model) {
		return "redirect:/list.do";
	}

}