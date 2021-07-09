package com.finalproject.supermarket.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finalproject.supermarket.controller.dto.TokenDto;
import com.finalproject.supermarket.controller.form.LoginForm;
import com.finalproject.supermarket.service.AuthenticationService;
import com.finalproject.supermarket.service.TokenService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	
//	@Autowired
//	AuthenticationService authenticationService;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<TokenDto> authenticate(@RequestBody @Valid LoginForm loginForm) {
		
//		if(authenticationService.authenticate(loginForm) != null) {
//			return ResponseEntity.ok().body(authenticationService.authenticate(loginForm));	
//		} else {
//			return ResponseEntity.badRequest().build();
//		}
		
		UsernamePasswordAuthenticationToken loginData = loginForm.convert();
		
		

		try {
			Authentication authentication = authManager.authenticate(loginData);
			
			String token = tokenService.generateToken(authentication);

			return ResponseEntity.ok().body(new TokenDto(token, "Bearer"));			
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
