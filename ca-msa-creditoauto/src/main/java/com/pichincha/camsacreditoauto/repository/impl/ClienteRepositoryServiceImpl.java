package com.pichincha.camsacreditoauto.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pichincha.camsacreditoauto.domain.entity.ClienteEntity;
import com.pichincha.camsacreditoauto.repository.ClienteRespository;
import com.pichincha.camsacreditoauto.repository.service.ClienteRepositoryService;

@Service
public class ClienteRepositoryServiceImpl implements ClienteRepositoryService{

	@Autowired
	private ClienteRespository clienteRespository;
	
	
	@Override
	public ClienteEntity consultarClientePorId(Integer idCliente) {
		return clienteRespository.consultarClientePorId(idCliente).orElse(null);
	}


	@Override
	public List<ClienteEntity> guardarListaClientes(List<ClienteEntity> listaClientes) {
		return clienteRespository.saveAll(listaClientes);
	}

}
