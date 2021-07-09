package com.finalproject.supermarket.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.finalproject.supermarket.model.Admin;
import com.finalproject.supermarket.repository.AdminRepository;
import com.finalproject.supermarket.service.TokenService;

public class AuthenticationViaTokenFilter extends OncePerRequestFilter{
	
	private TokenService tokenService;
	private AdminRepository adminRepository;
	
	public AuthenticationViaTokenFilter(TokenService tokenService, AdminRepository adminRepository) {
		this.tokenService = tokenService;
		this.adminRepository = adminRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	throws ServletException, IOException {
		
		String token = restoreToken(request);
		
		boolean validToken = tokenService.isValidToken(token);
		
		if(validToken) {
			authenticateAdmin(token);
		}
				
		filterChain.doFilter(request, response);
		
	}

	private void authenticateAdmin(String token) {
		Long idAdmin = tokenService.getIdAdmin(token);
		Admin admin = adminRepository.findById(idAdmin).get();
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(admin, null, admin.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private String restoreToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		
		return token.substring(7, token.length());
	}
	
}
