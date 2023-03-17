package com.pichincha.camsacreditoauto.service;

import java.util.List;

import com.pichincha.camsacreditoauto.service.dto.VehiculoDto;

public interface VehiculoService {
	
	VehiculoDto crearVechiculo(VehiculoDto vehiculotDto);
	
	VehiculoDto consultarVechiculoPorId(Integer idVehiculo);
	
	List<VehiculoDto> consultarVechiculoPorMarca(Integer marca);

	List<VehiculoDto> consultarVechiculoPorModelo(String modelo);
	
	VehiculoDto editarVechiculo(VehiculoDto vehiculotDto);

	VehiculoDto eliminarVechiculo(Integer idVehiculo);
}
