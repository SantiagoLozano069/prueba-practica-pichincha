package com.pichincha.camsacreditoauto.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.pichincha.camsacreditoauto.domain.entity.VehiculoEntity;
import com.pichincha.camsacreditoauto.service.dto.VehiculoDto;

@Mapper
public interface VehiculoMapper {	
	VehiculoDto toVehiculoDto(VehiculoEntity vehiculoEntity);
    VehiculoEntity toVehiculoEntity(VehiculoDto vehiculoDto);

}
