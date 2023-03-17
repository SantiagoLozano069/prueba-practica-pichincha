package com.pichincha.camsacreditoauto.repository.service;

import java.util.List;

import com.pichincha.camsacreditoauto.domain.entity.ClienteEntity;

public interface ClienteRepositoryService {

	ClienteEntity consultarClientePorId(Integer idCliente);
	
	List<ClienteEntity> guardarListaClientes(List<ClienteEntity> listaClientes);
}
