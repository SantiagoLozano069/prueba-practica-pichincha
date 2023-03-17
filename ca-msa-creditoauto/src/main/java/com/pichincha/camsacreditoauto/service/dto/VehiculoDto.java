package com.pichincha.camsacreditoauto.service.dto;

import java.math.BigInteger;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class VehiculoDto extends ErrorDto{

	private Integer id;

	@NotEmpty(message = "La placa del vehiculo es obligatoria.")
	@NotNull(message = "La placa del vehiculo es obligatoria.")
	private String placa;
	
	@NotEmpty(message = "El modelo del vehiculo es obligatorio.")
	@NotNull(message = "El modelo del vehiculo es obligatorio.")
	private String modelo;
	
	@NotEmpty(message = "El numero del chachis del vehiculo es obligatorio.")
	@NotNull(message = "El numero del chachis del vehiculo es obligatorio.")
	private String numeroChasis;

	private String tipo;
	
	@NotNull(message = "El cilindraje del vehiculo es obligatorio.")
	private Double cilindraje;	
	
	@NotNull(message = "El avaluo del vehiculo es obligatorio.")
	private BigInteger avaluo;
	
	@NotNull(message = "La marca del vehiculo es obligatoria.")
	private MarcaDto marca;



	//constructores
	public VehiculoDto() {
		super();
	}
	
	public VehiculoDto(String error) {
		super(error);
	}

	public VehiculoDto(List<String> errorRequest) {
		super(errorRequest);
	}


	//get and set
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getNumeroChasis() {
		return numeroChasis;
	}

	public void setNumeroChasis(String numeroChasis) {
		this.numeroChasis = numeroChasis;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Double getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(Double cilindraje) {
		this.cilindraje = cilindraje;
	}

	public BigInteger getAvaluo() {
		return avaluo;
	}

	public void setAvaluo(BigInteger avaluo) {
		this.avaluo = avaluo;
	}

	public MarcaDto getMarca() {
		return marca;
	}

	public void setMarca(MarcaDto marca) {
		this.marca = marca;
	}
}
