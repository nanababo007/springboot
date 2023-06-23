package com.kdhppo.smplcms.ctl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/page")
public class PageCtl {

	//페이지 목록.
	@GetMapping("/list.do")
	public String list(Model model) {
		return "page/list";
	}

	//페이지 등록.
	@GetMapping("/reg.do")
	public String reg(Model model) {
		return "page/reg";
	}

}