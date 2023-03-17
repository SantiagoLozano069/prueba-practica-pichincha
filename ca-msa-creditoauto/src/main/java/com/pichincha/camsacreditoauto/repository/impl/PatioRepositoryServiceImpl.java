package com.pichincha.camsacreditoauto.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pichincha.camsacreditoauto.domain.entity.PatioEntity;
import com.pichincha.camsacreditoauto.repository.CreditoRepository;
import com.pichincha.camsacreditoauto.repository.PatioRepository;
import com.pichincha.camsacreditoauto.repository.service.PatioRepositoryService;

@Service
public class PatioRepositoryServiceImpl implements PatioRepositoryService {
	
	@Autowired
	private PatioRepository patioRepository;
	
	@Autowired
	private CreditoRepository creditoRepository;

	@Override
	public PatioEntity crearPatio(PatioEntity patioEntity) {
		return patioRepository.save(patioEntity);
	}

	@Override
	public PatioEntity consultarPatioPorId(Integer idPatio) {
		return patioRepository.consultarPatioPorId(idPatio).orElse(null);
	}

	@Override
	public PatioEntity editarPatio(PatioEntity patioEntity) {
		return patioRepository.save(patioEntity);
	}

	@Override
	public void eliminarPatio(Integer idPatio) {
		patioRepository.deleteById(idPatio);
	}

	@Override
	public Boolean existeInformacionAsociadaParaPatio(Integer idPatio) {
		return creditoRepository.consultarInformacionAsociadaPatio(idPatio).isEmpty() ? false : true;
	}

}
