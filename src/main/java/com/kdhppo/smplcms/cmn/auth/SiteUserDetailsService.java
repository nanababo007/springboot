package com.kdhppo.smplcms.cmn.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.kdhppo.smplcms.cst.AuthCst;
import com.kdhppo.smplcms.svc.MembSvc;
import com.kdhppo.smplcms.vo.memb.MembResVo;

@Component
public class SiteUserDetailsService implements UserDetailsService {
	private final MembSvc membSvc;

    @Autowired
	public SiteUserDetailsService(MembSvc membSvc) {
		this.membSvc = membSvc;
	}

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		Optional<MembResVo> menbInfoOpt = membSvc.getMembInfoById(userId, true);
		MembResVo membInfo = menbInfoOpt.orElseThrow(() -> new UsernameNotFoundException(userId+" is not member."));

		return User.builder()
			.username(membInfo.getMembId())
			.password(membInfo.getMembPw())
			.roles(AuthCst.ADMIN, AuthCst.USER)
			.build();
	}
}