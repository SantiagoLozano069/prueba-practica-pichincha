package com.pichincha.camsacreditoauto.domain.enums;

public enum PatioErrorNegocioEnum {

	NO_EXISTE_PATIO("Error: no existe patio con este id "),
	INFORMACION_ASOCIADA("Error: no se puede eliminar este patio dado que tiene informacion asociada"),
	ID_NULL("Error: el id es obligatorio ");



	private String mensaje;
	
	PatioErrorNegocioEnum(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getMensaje() {
		return mensaje;
	}
}
