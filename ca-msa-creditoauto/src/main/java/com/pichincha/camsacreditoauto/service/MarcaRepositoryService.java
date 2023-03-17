package com.pichincha.camsacreditoauto.service;

import java.util.List;

import com.pichincha.camsacreditoauto.domain.entity.MarcaEntity;

public interface MarcaRepositoryService {

	MarcaEntity consultarMarcaPorId(Integer idMarca);
	
	List<MarcaEntity> guardarListaMarcas(List<MarcaEntity> listaMarcas);

	
}
