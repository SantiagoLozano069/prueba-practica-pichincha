package com.pichincha.camsacreditoauto.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pichincha.camsacreditoauto.domain.entity.ClientePatioEntity;
import com.pichincha.camsacreditoauto.repository.ClientePatioRepository;
import com.pichincha.camsacreditoauto.repository.CreditoRepository;
import com.pichincha.camsacreditoauto.repository.service.ClientePatioRepositoryService;

@Service
public class ClientePatioRepositoryServiceImpl implements ClientePatioRepositoryService{

	@Autowired
	private ClientePatioRepository clientePatioRepository;
	
	@Autowired
	private CreditoRepository creditoRepository;
	
	@Override
	public ClientePatioEntity crearClientePatio(ClientePatioEntity clientePatioEntity) {
		return clientePatioRepository.save(clientePatioEntity);
	}

	@Override
	public ClientePatioEntity consultarClientePatioPorId(Integer idClientePatio) {
		return clientePatioRepository.consultarClientePartioPorId(idClientePatio).orElse(null);
	}

	@Override
	public ClientePatioEntity editarClientePatio(ClientePatioEntity clientePatioEntity) {
		return clientePatioRepository.save(clientePatioEntity);
	}

	@Override
	public void eliminarPatio(Integer idClientePatio) {
		clientePatioRepository.deleteById(idClientePatio);
	}

	@Override
	public Boolean existeInformacionAsociadaParaClientePatio(Integer idCliente, Integer idPatio) {
		return creditoRepository.consultarInformacionAsociadaClientePatio(idCliente, idPatio).isPresent() ? true : false;
	}

}
