package com.pichincha.camsacreditoauto.service;

import com.pichincha.camsacreditoauto.service.dto.PatioDto;

public interface PatioService {

	PatioDto crearPatio(PatioDto patiotDto);
	
	PatioDto consultarPatioPorId(Integer idPatio);
	
	PatioDto editarPatio(PatioDto patiotDto);

	PatioDto eliminarPatio(Integer idPatio);
}
