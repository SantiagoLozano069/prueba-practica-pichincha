package com.pichincha.camsacreditoauto.domain.enums;

public enum EstadoSolicitudCreditoEnum {

	REGISTRADA("R"),
	DESPACHADA("D"),
	CANCELADA("C");
	

	private String codigo;
	
	EstadoSolicitudCreditoEnum(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}
	
    public static EstadoSolicitudCreditoEnum obtenerEstadoPorCodigo(String codigo){
        for(EstadoSolicitudCreditoEnum codigoEnum : EstadoSolicitudCreditoEnum.values()){
            if(codigoEnum.getCodigo().equalsIgnoreCase(codigo)){
                return codigoEnum;
            }
        }
        throw new IllegalArgumentException("No se encontró ningún estado con código ["+ codigo + "].");
    }
}
