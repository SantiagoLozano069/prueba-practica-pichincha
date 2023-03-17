package com.pichincha.camsacreditoauto.domain.entity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vehiculo")
public class VehiculoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 8, nullable = false)
	private Integer id;

	@Column(name = "placa", nullable = false)
	private String placa;

	@Column(name = "modelo", nullable = false)
	private String modelo;
	
	@Column(name = "numeroChasis", nullable = false)
	private String numeroChasis;

	@Column(name = "tipo")
	private String tipo;

	@Column(name = "cilindraje", nullable = false)
	private Double cilindraje;	
	
	@Column(name = "avaluo", nullable = false)
	private BigInteger avaluo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_marca", nullable = false)
	private MarcaEntity marca;

	
	
	public VehiculoEntity() {}
	
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

	public MarcaEntity getMarca() {
		return marca;
	}

	public void setMarca(MarcaEntity marca) {
		this.marca = marca;
	}
	
	
}
