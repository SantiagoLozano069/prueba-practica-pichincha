package com.pichincha.camsacreditoauto.service;

import com.pichincha.camsacreditoauto.service.dto.ClientePatioDto;

public interface ClientePatioService {

	ClientePatioDto crearClientePatio(ClientePatioDto clientePatioDto);
	
	ClientePatioDto consultarClientePatioPorId(Integer idClientePatio);
	
	ClientePatioDto editarClientePatio(ClientePatioDto clientePatioDto);

	ClientePatioDto eliminarClientePatio(Integer idClientePatio);
}
