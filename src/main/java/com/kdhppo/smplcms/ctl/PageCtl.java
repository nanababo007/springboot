package com.kdhppo.smplcms.ctl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kdhppo.smplcms.ctl.api.SmplApiCtl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/page")
public class PageCtl {

	//메인 대시보드 페이지.
	@GetMapping("/")
	public String index(Model model) {
		return "main/index";
	}

}