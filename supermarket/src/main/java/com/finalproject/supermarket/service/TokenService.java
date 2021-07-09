package com.finalproject.supermarket.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.finalproject.supermarket.model.Admin;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	@Value("${supermarket.jwt.expiration}")
	private String expiration;
	
	@Value("${supermarket.jwt.secret}")
	private String secret;

	public String generateToken(Authentication authentication) {
		Admin admin = (Admin) authentication.getPrincipal();
		Date today = new Date();
		Date expirationDay = new Date(today.getTime() + Long.parseLong(expiration));

		return Jwts.builder()
				.setIssuer("Pedro's Supermarket API")
				.setSubject(admin.getId().toString())
				.setIssuedAt(today)
				.setExpiration(expirationDay)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

	public boolean isValidToken(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Long getIdAdmin(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}
}
