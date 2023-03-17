package com.pichincha.camsacreditoauto.repository.service;

import com.pichincha.camsacreditoauto.domain.entity.ClientePatioEntity;

public interface ClientePatioRepositoryService {

	ClientePatioEntity crearClientePatio(ClientePatioEntity clientePatioEntity);
	
	ClientePatioEntity consultarClientePatioPorId(Integer idClientePatio);
	
	ClientePatioEntity editarClientePatio(ClientePatioEntity clientePatioEntity);

	Boolean existeInformacionAsociadaParaClientePatio(Integer idCliente, Integer idPatio);

	void eliminarPatio(Integer idClientePatio);
}
