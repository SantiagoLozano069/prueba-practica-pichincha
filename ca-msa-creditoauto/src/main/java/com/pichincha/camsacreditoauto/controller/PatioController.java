package com.pichincha.camsacreditoauto.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pichincha.camsacreditoauto.exception.HanddlerException;
import com.pichincha.camsacreditoauto.service.PatioService;
import com.pichincha.camsacreditoauto.service.dto.PatioDto;

@RestController
@RequestMapping("patio")
public class PatioController {
	
	@Autowired
	private PatioService patioService;
	
    @PostMapping("crear")
    private ResponseEntity<PatioDto> crearPatio(@Valid @RequestBody PatioDto patioDto, BindingResult resultRequest){
		if (resultRequest.hasErrors()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new PatioDto(HanddlerException.handleValidationExceptions(resultRequest)));
		}
        return ResponseEntity.status(HttpStatus.OK).body(patioService.crearPatio(patioDto));
    }
    
    @GetMapping("buscar")
    private ResponseEntity<PatioDto> consultarPatioPorId(@RequestParam(name =  "id", required = true) Integer idPatio){
        return ResponseEntity.status(HttpStatus.OK).body(patioService.consultarPatioPorId(idPatio));
    }

    @PutMapping("actualizar")
    private ResponseEntity<PatioDto> editarPatio (@Valid @RequestBody PatioDto patioDto, BindingResult resultRequest){
		if (resultRequest.hasErrors()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new PatioDto(HanddlerException.handleValidationExceptions(resultRequest)));
		}
        return ResponseEntity.status(HttpStatus.OK).body(patioService.editarPatio(patioDto));
    }

    @DeleteMapping("eliminar")
    private ResponseEntity<PatioDto> eliminarPatio (@RequestParam(name = "id", required = true) Integer id ) {
        return ResponseEntity.status(HttpStatus.OK).body(patioService.eliminarPatio(id));
    }

}
