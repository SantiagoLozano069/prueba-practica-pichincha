package com.pichincha.camsacreditoauto.domain.enums;

public enum VehiculoErrorNegocioEnum {
	
	PLACA_EXISTENTE("Error: ya existe un vehiculo con la placa "),
	MARCA_NO_EXISTE("Error: no existe una marca con id "),
	NO_EXISTE_VEHICULO_POR_ID("Error: no existe vehiculo con el id "),
	NO_EXISTE_VEHICULO_POR_MARCA("Error: no existe vehiculo con la marca "),
	NO_EXISTE_VEHICULO_POR_MODELO("Error: no existe vehiculo con el modelo "),
	ID_VEHICULO_NULL("Error: el id del vehiculo es obligatorio"),
	INFORMACION_ASOCIADA("Error: existe una solicitud de crédito con el vehiculo que desea eliminar");



	
	private String mensaje;
	
	VehiculoErrorNegocioEnum(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getMensaje() {
		return mensaje;
	}
}
