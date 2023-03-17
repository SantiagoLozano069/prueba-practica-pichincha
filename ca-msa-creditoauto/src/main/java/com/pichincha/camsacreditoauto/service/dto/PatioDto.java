package com.pichincha.camsacreditoauto.service.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class PatioDto extends ErrorDto {
	
	private Integer id;

	@NotEmpty(message = "El nombre es obligatorio")
	@NotNull(message = "El nombre es obligatorio")
	private String nombre;
	
	@NotEmpty(message = "La direccion es obligatoria")
	@NotNull(message = "La direccion es obligatoria")
	private String direccion;
	
	@NotEmpty(message = "El telefono es obligatorio")
	@NotNull(message = "El telefono es obligatorio")
	private String telefono;
	
	@NotNull(message = "El numeroPuntoVenta es obligatorio")
	private Long numeroPuntoVenta;
	
	
	public PatioDto() {
		super();
	}
	
	public PatioDto(String error) {
		super(error);
	}

	public PatioDto(List<String> errorRequest) {
		super(errorRequest);
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public Long getNumeroPuntoVenta() {
		return numeroPuntoVenta;
	}
	public void setNumeroPuntoVenta(Long numeroPuntoVenta) {
		this.numeroPuntoVenta = numeroPuntoVenta;
	}
	
}
