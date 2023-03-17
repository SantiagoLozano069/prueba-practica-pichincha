package com.pichincha.camsacreditoauto.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pichincha.camsacreditoauto.domain.entity.CreditoEntity;
import com.pichincha.camsacreditoauto.domain.entity.EjecutivoEntity;
import com.pichincha.camsacreditoauto.repository.CreditoRepository;
import com.pichincha.camsacreditoauto.repository.service.CreditoRepositoryService;

@Service
public class CreditoRepositoryServiceImpl implements CreditoRepositoryService{

	@Autowired
	private CreditoRepository creditoRepository;
	
	@Override
	public CreditoEntity crearCredito(CreditoEntity creditoEntity) {
		return creditoRepository.save(creditoEntity);
	}

	@Override
	public List<CreditoEntity> consultarCreditoPorCliente(Integer idCLiente) {
		return creditoRepository.consultarCreditoPorCliente(idCLiente);
	}

	@Override
	public CreditoEntity consultarCreditoPorVehiculo(Integer idVehiculo) {
		return creditoRepository.consultarCreditoPorVehiculo(idVehiculo).orElse(null);
	}

	@Override
	public List<CreditoEntity> consultareEjecutivosPorPatio(Integer idPatio) {
		return creditoRepository.consultarInformacionAsociadaPatio(idPatio);
	}

}
