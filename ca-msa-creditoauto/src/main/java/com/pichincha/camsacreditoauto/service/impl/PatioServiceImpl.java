package com.pichincha.camsacreditoauto.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.pichincha.camsacreditoauto.domain.entity.PatioEntity;
import com.pichincha.camsacreditoauto.domain.enums.PatioErrorNegocioEnum;
import com.pichincha.camsacreditoauto.repository.service.PatioRepositoryService;
import com.pichincha.camsacreditoauto.service.PatioService;
import com.pichincha.camsacreditoauto.service.dto.PatioDto;

@Service
public class PatioServiceImpl implements PatioService {
	
	@Autowired
	private PatioRepositoryService patioRepositoryService;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PatioDto crearPatio(PatioDto patiotDto) {
		PatioEntity patioEntity = patioRepositoryService
				.crearPatio(modelMapper.map(patiotDto, PatioEntity.class));
		return modelMapper.map(patioEntity, PatioDto.class);

	}

	@Override
	public PatioDto consultarPatioPorId(Integer idPatio) {
		PatioEntity patioEntity = patioRepositoryService.consultarPatioPorId(idPatio);
		if(ObjectUtils.isEmpty(patioEntity)) {
			return new PatioDto(PatioErrorNegocioEnum.NO_EXISTE_PATIO.getMensaje() + idPatio.toString());
		}else {
			return modelMapper.map(patioEntity, PatioDto.class);
		}
	}

	@Override
	public PatioDto editarPatio(PatioDto patiotDto) {
		if (ObjectUtils.isEmpty(patiotDto.getId())) {
			return new PatioDto(PatioErrorNegocioEnum.ID_NULL.getMensaje());
		} else {
			if (ObjectUtils.isEmpty(patioRepositoryService.consultarPatioPorId(patiotDto.getId()))) {
				return new PatioDto(PatioErrorNegocioEnum.NO_EXISTE_PATIO.getMensaje()
						+ patiotDto.getId().toString());
			} else {
				return this.crearPatio(patiotDto);
			}
		}
	}

	@Override
	public PatioDto eliminarPatio(Integer idPatio) {
		PatioEntity patioEntity = patioRepositoryService.consultarPatioPorId(idPatio);
		if(ObjectUtils.isEmpty(patioEntity)) {
			return new PatioDto(PatioErrorNegocioEnum.NO_EXISTE_PATIO.getMensaje() + idPatio.toString());
		}else {
			if(patioRepositoryService.existeInformacionAsociadaParaPatio(idPatio)) {
				return new PatioDto(PatioErrorNegocioEnum.INFORMACION_ASOCIADA.getMensaje());
			}
			patioRepositoryService.eliminarPatio(idPatio);
			return modelMapper.map(patioEntity, PatioDto.class);
		}
	}

}
