package com.pichincha.camsacreditoauto.domain.enums;

public enum CreditoErrorNegocioEnum {

	NO_EXISTE_EJECUTIVO("Error: no existe ejecutivo con este id "),
	SOLICITUD_EXISTENTE_EN_FECHA_ENVIADA("Error: ya existe una solicitud del cliente en la fecha enviada "),
	VEHICULO_RESERVADO("Error: el vehiculo enviado para la solicitud del crédito ya está reservado para otro. Id Vehiculo: ");

	private String mensaje;
	
	CreditoErrorNegocioEnum(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getMensaje() {
		return mensaje;
	}
}
