package com.pichincha.camsacreditoauto.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pichincha.camsacreditoauto.domain.entity.EjecutivoEntity;
import com.pichincha.camsacreditoauto.repository.EjecutivoRepository;
import com.pichincha.camsacreditoauto.repository.service.EjecutivoRepositoryService;

@Service
public class EjecutivoRepositoryServiceImpl implements EjecutivoRepositoryService {

	@Autowired
	private EjecutivoRepository ejecutivoRepository;
	
	@Override
	public EjecutivoEntity consultarEjecutivoPorId(Integer idEjecutivo) {
		return ejecutivoRepository.consultarEjecutivoPorId(idEjecutivo).orElse(null);
	}

	@Override
	public List<EjecutivoEntity> guardarListaEjecutivos(List<EjecutivoEntity> listaEjecutivos) {
		return ejecutivoRepository.saveAll(listaEjecutivos);
	}

}
