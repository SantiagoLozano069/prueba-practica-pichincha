package com.pichincha.camsacreditoauto.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pichincha.camsacreditoauto.domain.entity.VehiculoEntity;
import com.pichincha.camsacreditoauto.repository.CreditoRepository;
import com.pichincha.camsacreditoauto.repository.VehiculoRepository;
import com.pichincha.camsacreditoauto.repository.service.VehiculoRepositoryService;
import com.pichincha.camsacreditoauto.service.dto.VehiculoDto;

@Service
public class VehiculoRepositoryImpl implements VehiculoRepositoryService{

	@Autowired
	private VehiculoRepository vehiculoRepository;
	
	@Autowired
	private CreditoRepository creditoRepository;
	
	@Override
	public VehiculoEntity crearVechiculo(VehiculoEntity vehiculoEntity) {
		return vehiculoRepository.save(vehiculoEntity);
	}

	@Override
	public VehiculoEntity consultarVechiculoPorId(Integer idVehiculo) {
		return vehiculoRepository.consultarVehiculoPorId(idVehiculo).orElse(null);
	}

	@Override
	public List<VehiculoEntity> consultarVechiculoPorMarca(Integer marca) {
		return vehiculoRepository.consultarVehiculoPorMarca(marca);
	}

	@Override
	public List<VehiculoEntity> consultarVechiculoPorModelo(String modelo) {
		return vehiculoRepository.consultarVehiculoPorModelo(modelo);
	}

	@Override
	public VehiculoEntity editarVechiculo(VehiculoEntity vehiculoEntity) {
		return vehiculoRepository.save(vehiculoEntity);
	}

	@Override
	public void eliminarVechiculo(Integer idVehiculo) {
		vehiculoRepository.deleteById(idVehiculo);
	}

	@Override
	public VehiculoEntity consultarVechiculoPorPlaca(String placa) {
		return vehiculoRepository.consultarVehiculoPorPlaca(placa).orElse(null);
	}

	@Override
	public Boolean existeInformacionAsociadaParaVehiculo(Integer idVehiculo) {
		return creditoRepository.consultarInformacionAsociadaVehiculo(idVehiculo).isPresent() ?  true : false;
	}
}
