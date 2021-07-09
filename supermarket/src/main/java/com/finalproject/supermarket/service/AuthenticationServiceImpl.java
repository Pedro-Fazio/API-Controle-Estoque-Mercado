package com.finalproject.supermarket.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.finalproject.supermarket.controller.dto.TokenDto;
import com.finalproject.supermarket.controller.form.LoginForm;
import com.finalproject.supermarket.model.Admin;
import com.finalproject.supermarket.repository.AdminRepository;

@Service
public class AuthenticationServiceImpl implements UserDetailsService {
	
	@Autowired
	private AdminRepository adminRepository;
	
//	@Autowired
//	private AuthenticationManager authManager;
//	
//	@Autowired
//	private TokenService tokenService;
	
//	@Override
//	public TokenDto authenticate(LoginForm loginForm) {
//		UsernamePasswordAuthenticationToken loginData = loginForm.convert();
//
//		try {
//			Authentication authentication = authManager.authenticate(loginData);
//			
//			String token = tokenService.generateToken(authentication);
//			
//			return (new TokenDto(token, "Bearer"));
//		} catch (AuthenticationException e) {
//			return null;
//		}
//	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Admin> admin = adminRepository.findByEmail(username);
		
		if(admin.isPresent()) {
			return admin.get();
		} else {
			throw new UsernameNotFoundException("Invalid data!");
		}
	}

}
