package com.kdhppo.smplcms.ctl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kdhppo.smplcms.cst.AuthCst;
import com.kdhppo.smplcms.svc.MembSvc;
import com.kdhppo.smplcms.vo.memb.MembResVo;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/memb")
public class MembCtl {
	private final Logger logger = LoggerFactory.getLogger(MembCtl.class);

	private final MembSvc membSvc;

	//로그인 페이지.
	@GetMapping("/login/form.do")
	public String membLoginLogin(Model model) {
		return "memb/login/form";
	}

	/**
	 * 로그인 처리 페이지.
	 */
	@PostMapping("/login/proc.do")
	public String membLoginProc(User user) {
		String userId = user.getUsername();
		String userPw = user.getPassword();

		Optional<MembResVo> menbInfoOpt = membSvc.getMembInfoById(userId, true);
		MembResVo membInfo = menbInfoOpt.orElseThrow(() -> new UsernameNotFoundException(userId+" is not member."));

		logger.info("kkkkkkkkkkkkkkkkkkkbbbccc");

		User.builder()
			.username(membInfo.getMembId())
			.password(membInfo.getMembPw())
			.roles(AuthCst.ADMIN, AuthCst.USER)
			.build();

		return "redirect:/main/dash1.do";
	}

}