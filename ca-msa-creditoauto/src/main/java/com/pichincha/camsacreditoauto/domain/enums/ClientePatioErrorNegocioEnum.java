package com.pichincha.camsacreditoauto.domain.enums;

public enum ClientePatioErrorNegocioEnum {

	NO_EXISTE_CLIENTE_PATIO("Error: no existe cliente con este id "),
	NO_EXISTE_CLIENTE("Error: no existe el cliente  ingresado "),
	NO_EXISTE_PATIO("Error: no existe el patio ingresado "),
	INFORMACION_ASOCIADA("Error: no puede eliminar este registro porque existe informacion asociada."),
	ID_NULL("Error: el id es obligatorio ");



	private String mensaje;
	
	ClientePatioErrorNegocioEnum(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getMensaje() {
		return mensaje;
	}
}
