package com.finalproject.supermarket.config.validation;

public class ErrorFormDto {
	
	private String variable;
	private String errorMessage;
	
	public ErrorFormDto(String variable, String errorMessage) {
		super();
		this.variable = variable;
		this.errorMessage = errorMessage;
	}

	public String getVariable() {
		return variable;
	}

	public String getError() {
		return errorMessage;
	}
}
