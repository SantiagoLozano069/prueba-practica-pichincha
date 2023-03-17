package com.pichincha.camsacreditoauto.service.dto;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class CreditoDto extends ErrorDto{
	
	private Integer id;
	
	@NotNull(message =  "Error, el cliente es obligatorio")
	private ClienteDto cliente;
	
	@NotNull(message =  "Error, el patio es obligatorio")
	private PatioDto patio;
	
	@NotNull(message =  "Error, el vehiculo es obligatorio")
	private VehiculoDto vehiculo;
	
	@NotNull(message =  "Error, el ejecutivo es obligatorio")
	private EjecutivoDto ejecutivo;
	
	@NotNull(message = "La fecha de elaboracion es obligatoria")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaElaboracion;
	
	@NotNull(message =  "Error, los meses de plazo son obligatorios")
	private Integer mesesPlazo;
	
	@NotNull(message =  "Error, el numero de cuotas es obligatorio")
	private Integer cuotas;
	
	@NotNull(message =  "Error, la entrada es obligatoria")
	private BigInteger entrada;
	
	private String observacion;
	
	//@Enumerated(value = EnumType.STRING)
	@NotNull(message =  "Error, el estado de la solicutd es obligatoria")
	private String estadoSolicitudCredito;
	
	private List<EjecutivoDto> ejecutivos;
	
	//Constructores
	public CreditoDto() {
		super();
	}
	
	public CreditoDto(String error) {
		super(error);
	}

	public CreditoDto(List<String> errorRequest) {
		super(errorRequest);
	}
	
	
	
	//Getteres y Setters
	
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

	public VehiculoDto getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(VehiculoDto vehiculo) {
		this.vehiculo = vehiculo;
	}

	public EjecutivoDto getEjecutivo() {
		return ejecutivo;
	}

	public void setEjecutivo(EjecutivoDto ejecutivo) {
		this.ejecutivo = ejecutivo;
	}

	public LocalDate getFechaElaboracion() {
		return fechaElaboracion;
	}

	public void setFechaElaboracion(LocalDate fechaElaboracion) {
		this.fechaElaboracion = fechaElaboracion;
	}

	public Integer getMesesPlazo() {
		return mesesPlazo;
	}

	public void setMesesPlazo(Integer mesesPlazo) {
		this.mesesPlazo = mesesPlazo;
	}

	public Integer getCuotas() {
		return cuotas;
	}

	public void setCuotas(Integer cuotas) {
		this.cuotas = cuotas;
	}

	public BigInteger getEntrada() {
		return entrada;
	}

	public void setEntrada(BigInteger entrada) {
		this.entrada = entrada;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getEstadoSolicitudCredito() {
		return estadoSolicitudCredito;
	}

	public void setEstadoSolicitudCredito(String estadoSolicitudCredito) {
		this.estadoSolicitudCredito = estadoSolicitudCredito;
	}

	public List<EjecutivoDto> getEjecutivos() {
		return ejecutivos;
	}

	public void setEjecutivos(List<EjecutivoDto> ejecutivos) {
		this.ejecutivos = ejecutivos;
	}

}
