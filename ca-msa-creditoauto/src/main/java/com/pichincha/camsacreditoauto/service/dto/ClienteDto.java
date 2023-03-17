package com.pichincha.camsacreditoauto.service.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ClienteDto {

	private Integer id;
	
	private String identificacion;

	private String nombres;

	private String apellidos;
	
	private String telefono;
	
	private Integer edad;
	
	private LocalDate fechaNacimiento;
	
	private String estadoCivil;
	
	private String identificacionConyugue;
	
	private String nombreConyugue;
	
	private Boolean sujetoCredito;
	

	public ClienteDto(Integer id) {
		super();
		this.id = id;
	}
	
	public ClienteDto() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getIdentificacionConyugue() {
		return identificacionConyugue;
	}

	public void setIdentificacionConyugue(String identificacionConyugue) {
		this.identificacionConyugue = identificacionConyugue;
	}

	public String getNombreConyugue() {
		return nombreConyugue;
	}

	public void setNombreConyugue(String nombreConyugue) {
		this.nombreConyugue = nombreConyugue;
	}

	public Boolean getSujetoCredito() {
		return sujetoCredito;
	}

	public void setSujetoCredito(Boolean sujetoCredito) {
		this.sujetoCredito = sujetoCredito;
	}
	
}
