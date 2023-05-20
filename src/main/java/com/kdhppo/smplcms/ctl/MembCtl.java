package com.kdhppo.smplcms.ctl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kdhppo.smplcms.svc.MembSvc;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/memb")
public class MembCtl {
	private final Logger logger = LoggerFactory.getLogger(MembCtl.class);

	private final MembSvc membSvc;

	//로그인 페이지.
	@GetMapping("/login/login.do")
	public String membLoginLogin(Model model) {
		return "core/memb/login/login";
	}

}