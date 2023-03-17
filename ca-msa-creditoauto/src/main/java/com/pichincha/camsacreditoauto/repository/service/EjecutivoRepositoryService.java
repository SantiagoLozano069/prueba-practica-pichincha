package com.pichincha.camsacreditoauto.repository.service;

import java.util.List;

import com.pichincha.camsacreditoauto.domain.entity.EjecutivoEntity;

public interface EjecutivoRepositoryService {

	EjecutivoEntity consultarEjecutivoPorId(Integer idEjecutivo);
	
	List<EjecutivoEntity> guardarListaEjecutivos(List<EjecutivoEntity> listaEjecutivos);
}
