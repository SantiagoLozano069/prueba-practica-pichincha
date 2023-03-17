package com.pichincha.camsacreditoauto.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "marca")
public class MarcaEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 8, nullable = false)
	private Integer id;
	
	@Column(name = "marca")
	private String marca;

	public MarcaEntity() {}

	public MarcaEntity(Integer id) {
		super();
		this.id = id;
	}
	
	public MarcaEntity(Integer id, String marca) {
		super();
		this.id = id;
		this.marca = marca;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}

}
