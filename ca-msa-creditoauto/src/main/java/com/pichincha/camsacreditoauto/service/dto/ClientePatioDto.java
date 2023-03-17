package com.pichincha.camsacreditoauto.service.dto;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ClientePatioDto extends ErrorDto {

	private Integer id;
	
	@NotNull(message = "El cliente es obligatorio")
	private ClienteDto cliente;

	@NotNull(message = "El patio es obligatorio")
	private PatioDto patio;

	@NotNull(message = "La fecha es obligatoria")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaAsignacion;
	
	public ClientePatioDto() {
		super();
	}
	
	
	public ClientePatioDto(ClienteDto cliente,PatioDto patio,LocalDate fechaAsignacion) {
		super();
		this.cliente = cliente;
		this.patio = patio;
		this.fechaAsignacion = fechaAsignacion;
	}


	public ClientePatioDto(String error) {
		super(error);
	}

	public ClientePatioDto(List<String> errorRequest) {
		super(errorRequest);
	}
	
	
	//Getters and setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ClienteDto getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDto cliente) {
		this.cliente = cliente;
	}

	public PatioDto getPatio() {
		return patio;
	}

	public void setPatio(PatioDto patio) {
		this.patio = patio;
	}

	public LocalDate getFechaAsignacion() {
		return fechaAsignacion;
	}

	public void setFechaAsignacion(LocalDate fechaAsignacion) {
		this.fechaAsignacion = fechaAsignacion;
	}
}
