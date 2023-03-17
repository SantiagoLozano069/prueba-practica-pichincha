package com.pichincha.camsacreditoauto.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pichincha.camsacreditoauto.exception.HanddlerException;
import com.pichincha.camsacreditoauto.service.CreditoService;
import com.pichincha.camsacreditoauto.service.dto.CreditoDto;

@RestController
@RequestMapping("solicitudCredito")
public class CreditoController {

	@Autowired
	private CreditoService creditoService;
	
    @PostMapping("crear")
    private ResponseEntity<CreditoDto> crearCredito(@Valid @RequestBody CreditoDto creditoDto, BindingResult resultRequest){
		if (resultRequest.hasErrors()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new CreditoDto(HanddlerException.handleValidationExceptions(resultRequest)));
		}
        return ResponseEntity.status(HttpStatus.OK).body(creditoService.crearCredito(creditoDto));
    }
}
