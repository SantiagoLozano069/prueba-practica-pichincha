package com.pichincha.camsacreditoauto.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.pichincha.camsacreditoauto.domain.entity.ClienteEntity;
import com.pichincha.camsacreditoauto.domain.entity.CreditoEntity;
import com.pichincha.camsacreditoauto.domain.entity.EjecutivoEntity;
import com.pichincha.camsacreditoauto.domain.entity.PatioEntity;
import com.pichincha.camsacreditoauto.domain.entity.VehiculoEntity;
import com.pichincha.camsacreditoauto.domain.enums.ClientePatioErrorNegocioEnum;
import com.pichincha.camsacreditoauto.domain.enums.CreditoErrorNegocioEnum;
import com.pichincha.camsacreditoauto.domain.enums.EstadoSolicitudCreditoEnum;
import com.pichincha.camsacreditoauto.domain.enums.PatioErrorNegocioEnum;
import com.pichincha.camsacreditoauto.domain.enums.VehiculoErrorNegocioEnum;
import com.pichincha.camsacreditoauto.exception.PichinchaException;
import com.pichincha.camsacreditoauto.repository.service.ClienteRepositoryService;
import com.pichincha.camsacreditoauto.repository.service.CreditoRepositoryService;
import com.pichincha.camsacreditoauto.repository.service.EjecutivoRepositoryService;
import com.pichincha.camsacreditoauto.repository.service.PatioRepositoryService;
import com.pichincha.camsacreditoauto.repository.service.VehiculoRepositoryService;
import com.pichincha.camsacreditoauto.service.ClientePatioService;
import com.pichincha.camsacreditoauto.service.CreditoService;
import com.pichincha.camsacreditoauto.service.dto.ClientePatioDto;
import com.pichincha.camsacreditoauto.service.dto.CreditoDto;
import com.pichincha.camsacreditoauto.service.dto.EjecutivoDto;
import com.pichincha.camsacreditoauto.service.dto.PatioDto;
import com.pichincha.camsacreditoauto.service.dto.VehiculoDto;

@Service
public class CreditoServiceImpl implements CreditoService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private EjecutivoRepositoryService ejecutivoRepositoryService;

	@Autowired
	private CreditoRepositoryService creditoRepositoryService;

	@Autowired
	private ClienteRepositoryService clienteRepositoryService;

	@Autowired
	private PatioRepositoryService patioRepositoryService;

	@Autowired
	private VehiculoRepositoryService vehiculoRepositoryService;

	@Autowired
	private ClientePatioService clientePatioService;

	@Override
	public CreditoDto crearCredito(CreditoDto creditoDto) {
		try {
			EjecutivoEntity ejecutivoEntity = this.validarExisteciaEjectivo(creditoDto);
			ClienteEntity clienteEntity = this.validarExistenciaCliente(creditoDto);
			PatioEntity patioEntity = this.validarExistenciaPatio(creditoDto);
			VehiculoEntity vehiculoEntity = this.validarExistenciaVehiculo(creditoDto);
			this.validarSolicitudExistente(creditoDto);
			this.validarVehiculoReservado(creditoDto);
			CreditoEntity creditoEntitySaved = creditoRepositoryService.crearCredito(this.mapCreditoDtoToCreditoEntity(
					creditoDto, ejecutivoEntity, clienteEntity, patioEntity, vehiculoEntity));
			CreditoDto creditoReturn = modelMapper.map(creditoEntitySaved, CreditoDto.class);
			creditoReturn.setEjecutivos(this.consultarEjecutivosAsociadosAlPatio(creditoDto.getPatio().getId()));
			clientePatioService.crearClientePatio(new ClientePatioDto(creditoDto.getCliente(), creditoDto.getPatio(),
					creditoDto.getFechaElaboracion()));
			return creditoReturn;
		} catch (Exception e) {
			return new CreditoDto(e.getMessage());
		}

	}

	private EjecutivoEntity validarExisteciaEjectivo(CreditoDto creditoDto) throws PichinchaException {
		EjecutivoEntity ejecutivoEntity = ejecutivoRepositoryService
				.consultarEjecutivoPorId(creditoDto.getEjecutivo().getId());
		if (ObjectUtils.isEmpty(ejecutivoEntity)) {
			throw new PichinchaException(CreditoErrorNegocioEnum.NO_EXISTE_EJECUTIVO.getMensaje()
					+ creditoDto.getEjecutivo().getId().toString());
		}
		return ejecutivoEntity;
	}

	private ClienteEntity validarExistenciaCliente(CreditoDto creditoDto) throws PichinchaException {
		ClienteEntity clienteEntity = clienteRepositoryService.consultarClientePorId(creditoDto.getCliente().getId());
		if (ObjectUtils.isEmpty(clienteEntity)) {
			throw new PichinchaException(ClientePatioErrorNegocioEnum.NO_EXISTE_CLIENTE.getMensaje()
					+ creditoDto.getCliente().getId().toString());
		}
		return clienteEntity;
	}

	private PatioEntity validarExistenciaPatio(CreditoDto creditoDto) throws PichinchaException {
		PatioEntity patioEntity = patioRepositoryService.consultarPatioPorId(creditoDto.getPatio().getId());
		if (ObjectUtils.isEmpty(patioEntity)) {
			throw new PichinchaException(
					PatioErrorNegocioEnum.NO_EXISTE_PATIO.getMensaje() + creditoDto.getPatio().getId().toString());
		}
		return patioEntity;
	}

	private VehiculoEntity validarExistenciaVehiculo(CreditoDto creditoDto) throws PichinchaException {
		VehiculoEntity vehiculoEntity = vehiculoRepositoryService
				.consultarVechiculoPorId(creditoDto.getVehiculo().getId());
		if (ObjectUtils.isEmpty(vehiculoEntity)) {
			throw new PichinchaException(VehiculoErrorNegocioEnum.NO_EXISTE_VEHICULO_POR_ID.getMensaje()
					+ creditoDto.getVehiculo().getId().toString());
		}
		return vehiculoEntity;
	}

	private void validarSolicitudExistente(CreditoDto creditoDto) throws PichinchaException {
		List<CreditoEntity> creditoPorClienteEntityList = creditoRepositoryService
				.consultarCreditoPorCliente(creditoDto.getCliente().getId());
		if (!ObjectUtils.isEmpty(creditoPorClienteEntityList)) {
			creditoPorClienteEntityList.stream().forEach(creditoPorClienteEntity -> {
				if (creditoPorClienteEntity.getFechaElaboracion().equals(creditoDto.getFechaElaboracion())
						&& creditoPorClienteEntity.getEstadoSolicitudCredito()
								.equals(EstadoSolicitudCreditoEnum.REGISTRADA)) {
					throw new PichinchaException(
							CreditoErrorNegocioEnum.SOLICITUD_EXISTENTE_EN_FECHA_ENVIADA.getMensaje()
									+ creditoDto.getFechaElaboracion().toString());
				}
			});
		}
	}

	private void validarVehiculoReservado(CreditoDto creditoDto) throws PichinchaException {
		CreditoEntity creditoPorVehiculoEntity = creditoRepositoryService
				.consultarCreditoPorVehiculo(creditoDto.getVehiculo().getId());
		if (!ObjectUtils.isEmpty(creditoPorVehiculoEntity)) {
			throw new PichinchaException(CreditoErrorNegocioEnum.VEHICULO_RESERVADO.getMensaje()
					+ creditoDto.getVehiculo().getId().toString());
		}
	}

	private CreditoEntity mapCreditoDtoToCreditoEntity(CreditoDto creditoDto, EjecutivoEntity ejecutivoEntity,
			ClienteEntity clienteEntity, PatioEntity patioEntity, VehiculoEntity VehiculoEntity) {
		CreditoEntity creditoEntity = modelMapper.map(creditoDto, CreditoEntity.class);
		creditoEntity.setEjecutivo(ejecutivoEntity);
		creditoEntity.setCliente(clienteEntity);
		creditoEntity.setPatio(patioEntity);
		creditoEntity.setVehiculo(VehiculoEntity);
		creditoEntity.setEstadoSolicitudCredito(
				EstadoSolicitudCreditoEnum.obtenerEstadoPorCodigo(creditoDto.getEstadoSolicitudCredito()));
		return creditoEntity;
	}

	private List<EjecutivoDto> consultarEjecutivosAsociadosAlPatio(Integer idPatio) {
		return creditoRepositoryService.consultareEjecutivosPorPatio(idPatio).stream()
				.map(credito -> credito.getEjecutivo())
				.distinct()
				.map(ejecutivoEntity -> modelMapper.map(ejecutivoEntity, EjecutivoDto.class))
				.collect(Collectors.toList());
	}

}
