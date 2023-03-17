package com.pichincha.camsacreditoauto.domain.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cliente")
public class ClienteEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 8, nullable = false)
	private Integer id;
	
	@Column(name = "identificacion", nullable = false)
	private String identificacion;

	@Column(name = "nombres", nullable = false)
	private String nombres;

	@Column(name = "apellidos", nullable = false)
	private String apellidos;
	
	@Column(name = "telefono", nullable = false)
	private String telefono;
	
	@Column(name = "edad", nullable = false)
	private Integer edad;
	
	
	@Column(name = "fecha_nacimiento", nullable = false)
	private LocalDate fechaNacimiento;
	
	@Column(name = "estado_civil", nullable = false)
	private String estadoCivil;
	
	@Column(name = "identificacion_conyugue")
	private String identificacionConyugue;
	
	@Column(name = "nombre_conyugue")
	private String nombreConyugue;
	
	@Column(name = "sujeto_credito", nullable = false)
	private Boolean sujetoCredito;

	public ClienteEntity() {
	}

	public ClienteEntity(Integer id) {
		this.id = id;
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
