package com.pichincha.camsacreditoauto.service.dto;

import java.util.List;

public class ErrorDto {

	private String error;

	private List<String> errorRequest;
	 
	
	public ErrorDto() {
	}
	
	public ErrorDto(String error) {
		super();
		this.error = error;
	}
	
	public ErrorDto(List<String> errorRequest) {
		super();
		this.errorRequest = errorRequest;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
	public List<String> getErrorRequest() {
		return errorRequest;
	}

	public void setErrorRequest(List<String> errorRequest) {
		this.errorRequest = errorRequest;
	}
}
