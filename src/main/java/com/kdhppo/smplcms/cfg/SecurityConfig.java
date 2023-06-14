package com.kdhppo.smplcms.cfg;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.kdhppo.smplcms.cmn.auth.JwtAuthFilter;
import com.kdhppo.smplcms.cmn.auth.JwtTokenProvider;
import com.kdhppo.smplcms.cmn.auth.SiteAuthAfterProc;
import com.kdhppo.smplcms.cmn.auth.SitePasswordEncoder;
import com.kdhppo.smplcms.cst.AuthCst;

import jakarta.servlet.DispatcherType;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

	@Autowired
	SiteAuthAfterProc siteAuthAfterProc;
	@Autowired
	JwtTokenProvider jwtTokenProvider;

	/** 사이트 로그인 없이 접근 가능한 url. */
	private static final String[] SITE_WHITE_LIST = {
		"/css/**", "/js/**", "/images/**",
		"/api/memb/login/token/*"
	};

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new SitePasswordEncoder();
		//return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		//스프링 시큐리티 기본 로그인 페이지 url : /login
		//스프링 시큐리티 기본 로그아웃 페이지 url : /logout

		//스프링 시큐리티 설정.
		http
			.csrf().disable()
			.cors().disable()
			//.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.authorizeHttpRequests(request -> request
				//.shouldFilterAllDispatcherTypes(false)
				.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
				.requestMatchers(SITE_WHITE_LIST).permitAll()
				.requestMatchers("/**").hasRole(AuthCst.ADMIN)
				.anyRequest().authenticated() //.authenticated() , .anonymous()
			)
			.formLogin(login -> login
				//.loginPage("/memb/login/form.do") //이 라인 주석시, 기본 시큐리티 로그인 페이지 이동.
				//.loginProcessingUrl("/memb/login/proc.do") //로그인 폼에서 action 값을 이 값으로 변경.
				.failureUrl("/memb/login/error.do")
				.usernameParameter("userId")
				.passwordParameter("userPw")
				//.defaultSuccessUrl("/main/index.do", true) //successHandler 메서드를 정의 안하면, 이 메서드를 주석 해제.
				.successHandler(siteAuthAfterProc) //로그인 성공시 처리. 이 메서드를 정의하면, 위의 defaultSuccessUrl 가 동작 안함.
				//.failureHandler(siteAuthAfterProc) //로그인 실패시 처리. 메서드 정의 하면, 기본 로그인 실패 동작 안함, 직접 구현해 줘야됨.
				.permitAll()
			)
			.logout(withDefaults());
			//.logout() //.logout(withDefaults())
			//.logoutRequestMatcher(new AntPathRequestMatcher("/memb/login/logout.do"))
			//.logoutSuccessUrl("/");

		//세션 설정.
		//http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS);

		http.headers().frameOptions().disable();

		//JWT 필터 체크 등, 사이트 시큐리티 인증관련 필터 등록.
		http.addFilterBefore(
			new JwtAuthFilter(jwtTokenProvider),
			UsernamePasswordAuthenticationFilter.class
		);

		return http.build();
    }

}