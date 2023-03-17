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
import com.pichincha.camsacreditoauto.service.ClientePatioService;
import com.pichincha.camsacreditoauto.service.dto.ClientePatioDto;

@RestController
@RequestMapping("clientePatio")
public class ClientePatioController {

	@Autowired
	private ClientePatioService clientePatioService;
	
    @PostMapping("crear")
    private ResponseEntity<ClientePatioDto> crearClientePatio(@Valid @RequestBody ClientePatioDto clientePatioDto, BindingResult resultRequest){
		if (resultRequest.hasErrors()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ClientePatioDto(HanddlerException.handleValidationExceptions(resultRequest)));
		}
        return ResponseEntity.status(HttpStatus.OK).body(clientePatioService.crearClientePatio(clientePatioDto));
    }
    
    @GetMapping("buscar")
    private ResponseEntity<ClientePatioDto> consultarClientePatioPorId(@RequestParam(name =  "id", required = true) Integer idClientePAtio){
        return ResponseEntity.status(HttpStatus.OK).body(clientePatioService.consultarClientePatioPorId(idClientePAtio));
    }

    @PutMapping("actualizar")
    private ResponseEntity<ClientePatioDto> editarClientePatio (@Valid @RequestBody ClientePatioDto clientePatioDto, BindingResult resultRequest){
		if (resultRequest.hasErrors()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ClientePatioDto(HanddlerException.handleValidationExceptions(resultRequest)));
		}
        return ResponseEntity.status(HttpStatus.OK).body(clientePatioService.editarClientePatio(clientePatioDto));
    }

    @DeleteMapping("eliminar")
    private ResponseEntity<ClientePatioDto> eliminarClientePatio(@RequestParam(name = "id", required = true) Integer id ) {
        return ResponseEntity.status(HttpStatus.OK).body(clientePatioService.eliminarClientePatio(id));
    }
    
}
