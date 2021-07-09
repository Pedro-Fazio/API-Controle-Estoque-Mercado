package com.finalproject.supermarket.service;

import com.finalproject.supermarket.controller.dto.TokenDto;
import com.finalproject.supermarket.controller.form.LoginForm;

public interface AuthenticationService {
	public TokenDto authenticate(LoginForm loginForm);
}
