package com.pichincha.camsacreditoauto.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.validation.BindingResult;

public class HanddlerException {
	
	public static List<String> handleValidationExceptions(
            BindingResult resultRequest) {
        return resultRequest.getAllErrors().stream().map(error ->
                error.getDefaultMessage()
        ).collect(Collectors.toList());
    }
}
