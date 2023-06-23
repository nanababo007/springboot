package com.kdhppo.smplcms.ctl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MainCtl {

	//메인 페이지.
	@GetMapping({"/", "/main/index.do"})
	public String index(Model model) {
		return "redirect:/main/dash.do";
	}

	//메인 대시보드 페이지.
	@GetMapping("/main/dash.do")
	public String mainDash(Model model) {
		return "main/index";
	}

}