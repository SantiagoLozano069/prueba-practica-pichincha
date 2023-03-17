package com.pichincha.camsacreditoauto.service.dto;


public class MarcaDto {
	
	
	private Integer id;
	
	private String marca;
	
	public MarcaDto() {
		super();
	}

	public MarcaDto(Integer id) {
		super();
		this.id = id;
	}
	
	public MarcaDto(Integer id, String marca) {
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
