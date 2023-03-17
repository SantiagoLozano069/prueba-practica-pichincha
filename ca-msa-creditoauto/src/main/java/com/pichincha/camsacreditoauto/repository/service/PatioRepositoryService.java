package com.pichincha.camsacreditoauto.repository.service;

import com.pichincha.camsacreditoauto.domain.entity.PatioEntity;

public interface PatioRepositoryService {

	PatioEntity crearPatio(PatioEntity patioEntity);
	
	PatioEntity consultarPatioPorId(Integer idPatio);
	
	PatioEntity editarPatio(PatioEntity patioEntity);
	
	Boolean existeInformacionAsociadaParaPatio(Integer idPatio);

	void eliminarPatio(Integer idPatio);
}
