package com.pichincha.camsacreditoauto.domain.entity;

import java.math.BigInteger;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.pichincha.camsacreditoauto.domain.enums.EstadoSolicitudCreditoEnum;
import com.pichincha.camsacreditoauto.domain.enums.converters.EstadoSolicitudCreditoEnumConverter;

@Entity
@Table(name = "credito")
public class CreditoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 8, nullable = false)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cliente", nullable = false)
	private ClienteEntity cliente;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_patio", nullable = false)
	private PatioEntity patio;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_ejecutivo", nullable = false)
	private EjecutivoEntity ejecutivo;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_vehiculo", nullable = false)
	private VehiculoEntity vehiculo;
	
	@Column(name = "fecha_elaboracion", nullable = false)
	private LocalDate fechaElaboracion;
	
	@Column(name = "mess_plazo", nullable = false)
	private Integer mesesPlazo;
	
	@Column(name = "cuotas", nullable = false)
	private Integer cuotas;
	
	@Column(name = "entrada", nullable = false)
	private BigInteger entrada;
	
	@Column(name = "observacion", nullable = true)
	private String observacion;
	
	//@Enumerated(value = EnumType.STRING)
	@Convert(converter = EstadoSolicitudCreditoEnumConverter.class)
	@Column(name = "estado", nullable = true)
	private EstadoSolicitudCreditoEnum estadoSolicitudCredito;
    
    
	public CreditoEntity() {}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public ClienteEntity getCliente() {
		return cliente;
	}


	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}


	public PatioEntity getPatio() {
		return patio;
	}


	public void setPatio(PatioEntity patio) {
		this.patio = patio;
	}


	public EjecutivoEntity getEjecutivo() {
		return ejecutivo;
	}


	public void setEjecutivo(EjecutivoEntity ejecutivo) {
		this.ejecutivo = ejecutivo;
	}


	public VehiculoEntity getVehiculo() {
		return vehiculo;
	}


	public void setVehiculo(VehiculoEntity vehiculo) {
		this.vehiculo = vehiculo;
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


	public EstadoSolicitudCreditoEnum getEstadoSolicitudCredito() {
		return estadoSolicitudCredito;
	}


	public void setEstadoSolicitudCredito(EstadoSolicitudCreditoEnum estadoSolicitudCredito) {
		this.estadoSolicitudCredito = estadoSolicitudCredito;
	}
}
