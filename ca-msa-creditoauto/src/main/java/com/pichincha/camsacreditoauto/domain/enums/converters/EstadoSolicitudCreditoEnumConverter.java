package com.pichincha.camsacreditoauto.domain.enums.converters;

import javax.persistence.AttributeConverter;

import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.databind.util.StdConverter;
import com.pichincha.camsacreditoauto.domain.enums.EstadoSolicitudCreditoEnum;

public class EstadoSolicitudCreditoEnumConverter extends StdConverter<String, EstadoSolicitudCreditoEnum> implements AttributeConverter<EstadoSolicitudCreditoEnum, String> {

	@Override
	public String convertToDatabaseColumn(EstadoSolicitudCreditoEnum attribute) {
        if(!ObjectUtils.isEmpty(attribute)) {
            return attribute.getCodigo();
        }
        return null;
	}

	@Override
	public EstadoSolicitudCreditoEnum convertToEntityAttribute(String dbData) {
        if(!ObjectUtils.isEmpty(dbData)) {
            return EstadoSolicitudCreditoEnum.obtenerEstadoPorCodigo(dbData);
        }
        return null;
	}

	@Override
	public EstadoSolicitudCreditoEnum convert(String value) {
        return EstadoSolicitudCreditoEnum.obtenerEstadoPorCodigo(value);
	}

}
