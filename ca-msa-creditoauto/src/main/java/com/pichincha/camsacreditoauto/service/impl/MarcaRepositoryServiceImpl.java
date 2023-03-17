package com.pichincha.camsacreditoauto.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pichincha.camsacreditoauto.domain.entity.MarcaEntity;
import com.pichincha.camsacreditoauto.repository.MarcaRepository;
import com.pichincha.camsacreditoauto.service.MarcaRepositoryService;

@Service
public class MarcaRepositoryServiceImpl implements MarcaRepositoryService {

	@Autowired
	private MarcaRepository marcaRepository;
	
	@Override
	public MarcaEntity consultarMarcaPorId(Integer idMarca) {
		return marcaRepository.consultarMarcaPorId(idMarca).orElse(null);
	}

	@Override
	public List<MarcaEntity> guardarListaMarcas(List<MarcaEntity> listaMarcas) {
		return marcaRepository.saveAll(listaMarcas);
	}
}
