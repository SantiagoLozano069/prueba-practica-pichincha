package com.pichincha.camsacreditoauto.repository.service;

import java.util.List;

import com.pichincha.camsacreditoauto.domain.entity.CreditoEntity;

public interface CreditoRepositoryService {

	CreditoEntity crearCredito(CreditoEntity creditoEntity);
	
	List<CreditoEntity> consultarCreditoPorCliente(Integer idCLiente);
	
	CreditoEntity consultarCreditoPorVehiculo(Integer idVehiculo);
	
	List<CreditoEntity> consultareEjecutivosPorPatio(Integer idPatio);

}
