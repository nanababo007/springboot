package com.kdhppo.smplcms.cmn.auth;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.kdhppo.smplcms.cst.AuthCst;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtTokenProvider {

	@Value(value = "${jwt.secrect_key}")
	private String jwtSecrectKey;
	@Value(value = "${jwt.expired_min}")
	private String jwtExpiredMin;
	@Value(value = "${jwt.issuer}")
	private String jwtIssuer;

	/**
	 * 토큰 생성하기
	 */
	public String createToken(String subject) {
		LocalDateTime exprDttm = LocalDateTime.now().plusMinutes(Long.parseLong(jwtExpiredMin));
		Date exprDt = Timestamp.valueOf(exprDttm);

		String jwtToken = "";

		try {
			jwtToken = JWT.create()
				.withIssuer(jwtIssuer)
				.withSubject(subject)
				.withExpiresAt(exprDt) // 만료일
				.sign(Algorithm.HMAC256(jwtSecrectKey));
		} catch (JWTCreationException ex) {
			log.info("jwt create error");
			ex.printStackTrace();
		}

		return jwtToken;
	}

	/**
	 * 토큰에서 저장값 구하기
	 */
	public String getSubjectFromToken(String token) {
		DecodedJWT djwt = getDecodedToken(token);
		if(djwt!=null) {
			return djwt.getSubject();
		} else {
			return "";
		}
	}

	/**
	 * 디코드된 토큰 반환
	 */
	public DecodedJWT getDecodedToken(String token) {
		DecodedJWT djwt = null;

		try {
			djwt = JWT.decode(token);
		} catch (JWTDecodeException e) {
			log.info("jwt token decode error");
		}

		return djwt;
	}

	/**
	 * 토큰이 유효한지, 유효하지 않으면 널값 반환.
	 */
	public DecodedJWT checkValidToken(String token) {
		DecodedJWT djwt = null;

		try {
			JWTVerifier verifier = JWT.require(Algorithm.HMAC256(jwtSecrectKey))
				.withIssuer(jwtIssuer)
				.acceptExpiresAt(Long.parseLong(jwtExpiredMin) * 60)
				.build()
			;

			djwt = verifier.verify(token);
		} catch (JWTVerificationException ex) {
			log.info("jwt token not valid");
			//ex.printStackTrace();
		}

		return djwt;
	}

	/**
	 * JWT 토큰을 복호화하여 토큰에 들어있는 정보를 꺼내는 메서드
	 */
	public Authentication getAuthentication(String token) {
		Authentication result = null;

		// 토큰 복호화
		DecodedJWT djwt = checkValidToken(token);

		//정상적으로 복호화 되었으면
		if(djwt!=null) {
			// security 권한 목록
			Collection<GrantedAuthority> authorities =
				Arrays.stream(new String[] {AuthCst.ADMIN, AuthCst.USER}).
				map(SimpleGrantedAuthority::new).collect(Collectors.toList());

			// UserDetails 객체를 만들어서 Authentication 리턴
			UserDetails principal = new User(djwt.getSubject(), "", authorities);
			result = new UsernamePasswordAuthenticationToken(principal, "", authorities);
		}

		return result;
	}

}