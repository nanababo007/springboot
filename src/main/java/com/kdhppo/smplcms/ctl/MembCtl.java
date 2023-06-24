package com.kdhppo.smplcms.ctl;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kdhppo.smplcms.svc.MembSvc;
import com.kdhppo.smplcms.vo.memb.MembReqVo;
import com.kdhppo.smplcms.vo.memb.MembResVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/memb")
public class MembCtl {

	private final MembSvc membSvc;

	//회원 목록 페이지.
	@GetMapping("/list.do")
	public String membList(Model model, MembReqVo membReqVo) {
		List<MembResVo> list = membSvc.getMembList(membReqVo);
		model.addAttribute("list", list);
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