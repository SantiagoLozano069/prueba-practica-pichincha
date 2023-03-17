package com.pichincha.camsacreditoauto.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pichincha.camsacreditoauto.exception.HanddlerException;
import com.pichincha.camsacreditoauto.service.VehiculoService;
import com.pichincha.camsacreditoauto.service.dto.VehiculoDto;

@RestController
@RequestMapping("vehiculo")
public class VehiculoController {
	
	@Autowired
	private VehiculoService vehiculoService;
	
    @PostMapping("crear")
    private ResponseEntity<VehiculoDto> crearVehiculo(@Valid @RequestBody VehiculoDto vehiculoDto, BindingResult resultRequest){
		if (resultRequest.hasErrors()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new VehiculoDto(HanddlerException.handleValidationExceptions(resultRequest)));
		}
        return ResponseEntity.status(HttpStatus.OK).body(vehiculoService.crearVechiculo(vehiculoDto));
    }
    
    @GetMapping("buscar")
    private ResponseEntity<VehiculoDto> consultarVehiculoPorId(@RequestParam(name =  "id", required = true) Integer idVehiculo){
        return ResponseEntity.status(HttpStatus.OK).body(vehiculoService.consultarVechiculoPorId(idVehiculo));
    }
    
    @GetMapping("buscarPorMarca")
    private ResponseEntity<List<VehiculoDto>> consultarVehiculoPorMarca(@RequestParam(name =  "marca", required = true) Integer marca){
        return ResponseEntity.status(HttpStatus.OK).body(vehiculoService.consultarVechiculoPorMarca(marca));
    }
    
    @GetMapping("consultarPorModelo")
    private ResponseEntity<List<VehiculoDto>> consultarVehiculoPorModelo(@RequestParam(name =  "modelo", required = true) String modelo){
        return ResponseEntity.status(HttpStatus.OK).body(vehiculoService.consultarVechiculoPorModelo(modelo));
    }

    @PutMapping("actualizar")
    private ResponseEntity<VehiculoDto> editarVehiculo (@Valid @RequestBody VehiculoDto vehiculoDto, BindingResult resultRequest){
		if (resultRequest.hasErrors()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new VehiculoDto(HanddlerException.handleValidationExceptions(resultRequest)));
		}
        return ResponseEntity.status(HttpStatus.OK).body(vehiculoService.editarVechiculo(vehiculoDto));
    }

    @DeleteMapping("eliminar")
    private ResponseEntity<VehiculoDto> eliminarVehiculo(@RequestParam(name = "id", required = true) Integer id ) {
        return ResponseEntity.status(HttpStatus.OK).body(vehiculoService.eliminarVechiculo(id));
    }
	
}
