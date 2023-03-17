package com.pichincha.camsacreditoauto.repository.service;

import java.util.List;

import com.pichincha.camsacreditoauto.domain.entity.VehiculoEntity;

public interface VehiculoRepositoryService {
	
	VehiculoEntity crearVechiculo(VehiculoEntity vehiculoEntity);
	
	VehiculoEntity consultarVechiculoPorId(Integer idVehiculo);
	
	Boolean existeInformacionAsociadaParaVehiculo(Integer idVehiculo);
	
	List<VehiculoEntity> consultarVechiculoPorMarca(Integer marca);

	List<VehiculoEntity> consultarVechiculoPorModelo(String modelo);
	
	VehiculoEntity consultarVechiculoPorPlaca(String placa);

	VehiculoEntity editarVechiculo(VehiculoEntity vehiculoEntity);

	void eliminarVechiculo(Integer idVehiculo);

}
