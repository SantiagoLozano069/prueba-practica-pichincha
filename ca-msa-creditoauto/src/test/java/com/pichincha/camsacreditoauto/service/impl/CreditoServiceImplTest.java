package com.pichincha.camsacreditoauto.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

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
import com.pichincha.camsacreditoauto.repository.impl.ClienteRepositoryServiceImpl;
import com.pichincha.camsacreditoauto.repository.impl.CreditoRepositoryServiceImpl;
import com.pichincha.camsacreditoauto.repository.impl.EjecutivoRepositoryServiceImpl;
import com.pichincha.camsacreditoauto.repository.impl.PatioRepositoryServiceImpl;
import com.pichincha.camsacreditoauto.repository.impl.VehiculoRepositoryImpl;
import com.pichincha.camsacreditoauto.service.dto.ClienteDto;
import com.pichincha.camsacreditoauto.service.dto.ClientePatioDto;
import com.pichincha.camsacreditoauto.service.dto.CreditoDto;
import com.pichincha.camsacreditoauto.service.dto.EjecutivoDto;
import com.pichincha.camsacreditoauto.service.dto.PatioDto;
import com.pichincha.camsacreditoauto.service.dto.VehiculoDto;

@SpringBootTest
class CreditoServiceImplTest {

	@Mock
	private ModelMapper modelMapper;

	@Mock
	private EjecutivoRepositoryServiceImpl ejecutivoRepositoryServiceImpl;

	@Mock
	private CreditoRepositoryServiceImpl creditoRepositoryServiceImpl;

	@Mock
	private ClienteRepositoryServiceImpl clienteRepositoryServiceImpl;

	@Mock
	private PatioRepositoryServiceImpl patioRepositoryServiceImpl;

	@Mock
	private VehiculoRepositoryImpl vehiculoRepositoryImpl;
	
	@Mock
	private ClientePatioServiceImpl clientePatioServiceImpl;
	
	@InjectMocks
	private CreditoServiceImpl creditoServiceImpl;

	private CreditoDto creditoDto;
	private CreditoEntity creditoEntity;
	private ClienteEntity clienteEntity; 
	private PatioEntity patioEntity;
	private EjecutivoEntity ejecutivoEntity;
	private VehiculoEntity vehiculoEntity;
	private ClientePatioDto clientePatioDto;
	private EjecutivoDto ejecutivoDto;
	
	@BeforeEach
	void setUp() throws Exception {

		ClienteDto clienteDto = new ClienteDto();
		clienteDto.setId(1);

		PatioDto patioDto = new PatioDto();
		patioDto.setId(1);

		ejecutivoDto = new EjecutivoDto();
		ejecutivoDto.setId(1000);

		VehiculoDto vehiculoDto = new VehiculoDto();
		vehiculoDto.setId(1);

		creditoDto = new CreditoDto();
		creditoDto.setCliente(clienteDto);
		creditoDto.setPatio(patioDto);
		creditoDto.setEjecutivo(ejecutivoDto);
		creditoDto.setVehiculo(vehiculoDto);
		creditoDto.setFechaElaboracion(LocalDate.now());
		creditoDto.setMesesPlazo(12);
		creditoDto.setCuotas(12);
		creditoDto.setEntrada(BigInteger.valueOf(50000));
		creditoDto.setObservacion("obs");
		creditoDto.setEstadoSolicitudCredito(EstadoSolicitudCreditoEnum.REGISTRADA.getCodigo());
		
		clienteEntity = new ClienteEntity();
		clienteEntity.setId(1);

		patioEntity = new PatioEntity();
		patioEntity.setId(1);

		ejecutivoEntity = new EjecutivoEntity();
		ejecutivoEntity.setId(1000);

		vehiculoEntity = new VehiculoEntity();
		vehiculoEntity.setId(1);

		creditoEntity = new CreditoEntity();
		creditoEntity.setCliente(clienteEntity);
		creditoEntity.setPatio(patioEntity);
		creditoEntity.setEjecutivo(ejecutivoEntity);
		creditoEntity.setVehiculo(vehiculoEntity);
		creditoEntity.setFechaElaboracion(LocalDate.now());
		creditoEntity.setMesesPlazo(12);
		creditoEntity.setCuotas(12);
		creditoEntity.setEntrada(BigInteger.valueOf(50000));
		creditoEntity.setObservacion("obs");
		creditoEntity.setEstadoSolicitudCredito(EstadoSolicitudCreditoEnum.REGISTRADA);
				
		clientePatioDto = new ClientePatioDto(creditoDto.getCliente(), creditoDto.getPatio(), creditoDto.getFechaElaboracion());
	}

	@Test
	void crear_credito_error_ejecutivo_inexistente() {
		when(ejecutivoRepositoryServiceImpl.consultarEjecutivoPorId(creditoDto.getEjecutivo().getId())).thenReturn(null);
		CreditoDto creditoDtoRespuesta = creditoServiceImpl.crearCredito(creditoDto);
		assertNotNull(creditoDtoRespuesta.getError());
		assertEquals(creditoDtoRespuesta.getError(), CreditoErrorNegocioEnum.NO_EXISTE_EJECUTIVO.getMensaje() + creditoDto.getEjecutivo().getId().toString());
	}
	
	@Test
	void crear_credito_error_cliente_inexistente() {
		when(ejecutivoRepositoryServiceImpl.consultarEjecutivoPorId(creditoDto.getEjecutivo().getId())).thenReturn(ejecutivoEntity);
		when(clienteRepositoryServiceImpl.consultarClientePorId(creditoDto.getCliente().getId())).thenReturn(null);
		CreditoDto creditoDtoRespuesta = creditoServiceImpl.crearCredito(creditoDto);
		assertNotNull(creditoDtoRespuesta.getError());
		assertEquals(creditoDtoRespuesta.getError(), ClientePatioErrorNegocioEnum.NO_EXISTE_CLIENTE.getMensaje() + creditoDto.getCliente().getId().toString());
	}
	
	
	@Test
	void crear_credito_error_patio_inexistente() {
		when(ejecutivoRepositoryServiceImpl.consultarEjecutivoPorId(creditoDto.getEjecutivo().getId())).thenReturn(ejecutivoEntity);
		when(clienteRepositoryServiceImpl.consultarClientePorId(creditoDto.getCliente().getId())).thenReturn(clienteEntity);
		when(patioRepositoryServiceImpl.consultarPatioPorId(creditoDto.getPatio().getId())).thenReturn(null);
		CreditoDto creditoDtoRespuesta = creditoServiceImpl.crearCredito(creditoDto);
		assertNotNull(creditoDtoRespuesta.getError());
		assertEquals(creditoDtoRespuesta.getError(), PatioErrorNegocioEnum.NO_EXISTE_PATIO.getMensaje() +creditoDto.getPatio().getId().toString());
	}
	
	@Test
	void crear_credito_error_vehiculo_inexistente() {
		when(ejecutivoRepositoryServiceImpl.consultarEjecutivoPorId(creditoDto.getEjecutivo().getId())).thenReturn(ejecutivoEntity);
		when(clienteRepositoryServiceImpl.consultarClientePorId(creditoDto.getCliente().getId())).thenReturn(clienteEntity);
		when(patioRepositoryServiceImpl.consultarPatioPorId(creditoDto.getPatio().getId())).thenReturn(patioEntity);
		when(vehiculoRepositoryImpl.consultarVechiculoPorId(creditoDto.getVehiculo().getId())).thenReturn(null);
		CreditoDto creditoDtoRespuesta = creditoServiceImpl.crearCredito(creditoDto);
		assertNotNull(creditoDtoRespuesta.getError());
		assertEquals(creditoDtoRespuesta.getError(), VehiculoErrorNegocioEnum.NO_EXISTE_VEHICULO_POR_ID.getMensaje() +creditoDto.getVehiculo().getId().toString());
	}
	
	@Test
	void crear_credito_error_solicitud_existente() {
		when(ejecutivoRepositoryServiceImpl.consultarEjecutivoPorId(creditoDto.getEjecutivo().getId())).thenReturn(ejecutivoEntity);
		when(clienteRepositoryServiceImpl.consultarClientePorId(creditoDto.getCliente().getId())).thenReturn(clienteEntity);
		when(patioRepositoryServiceImpl.consultarPatioPorId(creditoDto.getPatio().getId())).thenReturn(patioEntity);
		when(vehiculoRepositoryImpl.consultarVechiculoPorId(creditoDto.getVehiculo().getId())).thenReturn(vehiculoEntity);
		when(creditoRepositoryServiceImpl.consultarCreditoPorCliente(creditoDto.getCliente().getId())).thenReturn(Arrays.asList(creditoEntity));

		CreditoDto creditoDtoRespuesta = creditoServiceImpl.crearCredito(creditoDto);
		assertNotNull(creditoDtoRespuesta.getError());
		assertEquals(creditoDtoRespuesta.getError(), CreditoErrorNegocioEnum.SOLICITUD_EXISTENTE_EN_FECHA_ENVIADA.getMensaje() + creditoDto.getFechaElaboracion().toString());
	}
	
	@Test
	void crear_credito_error_vehiculo_reservado() {
		creditoEntity.setFechaElaboracion(LocalDate.of(2023, 01, 15));
		when(ejecutivoRepositoryServiceImpl.consultarEjecutivoPorId(creditoDto.getEjecutivo().getId())).thenReturn(ejecutivoEntity);
		when(clienteRepositoryServiceImpl.consultarClientePorId(creditoDto.getCliente().getId())).thenReturn(clienteEntity);
		when(patioRepositoryServiceImpl.consultarPatioPorId(creditoDto.getPatio().getId())).thenReturn(patioEntity);
		when(vehiculoRepositoryImpl.consultarVechiculoPorId(creditoDto.getVehiculo().getId())).thenReturn(vehiculoEntity);
		when(creditoRepositoryServiceImpl.consultarCreditoPorCliente(creditoDto.getCliente().getId())).thenReturn(Arrays.asList(creditoEntity));
		when(creditoRepositoryServiceImpl.consultarCreditoPorVehiculo(creditoDto.getCliente().getId())).thenReturn(creditoEntity);

		CreditoDto creditoDtoRespuesta = creditoServiceImpl.crearCredito(creditoDto);
		assertNotNull(creditoDtoRespuesta.getError());
		assertEquals(creditoDtoRespuesta.getError(), CreditoErrorNegocioEnum.VEHICULO_RESERVADO.getMensaje() + creditoDto.getVehiculo().getId().toString());
	}
	
	@Test
	void crear_credito_exitosamente() {
		creditoEntity.setFechaElaboracion(LocalDate.of(2023, 01, 15));
		when(ejecutivoRepositoryServiceImpl.consultarEjecutivoPorId(creditoDto.getEjecutivo().getId())).thenReturn(ejecutivoEntity);
		when(clienteRepositoryServiceImpl.consultarClientePorId(creditoDto.getCliente().getId())).thenReturn(clienteEntity);
		when(patioRepositoryServiceImpl.consultarPatioPorId(creditoDto.getPatio().getId())).thenReturn(patioEntity);
		when(vehiculoRepositoryImpl.consultarVechiculoPorId(creditoDto.getVehiculo().getId())).thenReturn(vehiculoEntity);
		when(creditoRepositoryServiceImpl.consultarCreditoPorCliente(creditoDto.getCliente().getId())).thenReturn(Arrays.asList(creditoEntity));
		when(creditoRepositoryServiceImpl.consultarCreditoPorVehiculo(creditoDto.getCliente().getId())).thenReturn(null);
		when(modelMapper.map(creditoDto, CreditoEntity.class)).thenReturn(creditoEntity);
		when(creditoRepositoryServiceImpl.crearCredito(creditoEntity)).thenReturn(creditoEntity);
		when(modelMapper.map(creditoEntity, CreditoDto.class)).thenReturn(creditoDto);
		when(creditoRepositoryServiceImpl.consultareEjecutivosPorPatio(clientePatioDto.getPatio().getId())).thenReturn(Arrays.asList(creditoEntity));
		when(modelMapper.map(ejecutivoEntity, EjecutivoDto.class)).thenReturn(ejecutivoDto);
		when(clientePatioServiceImpl.crearClientePatio(clientePatioDto)).thenReturn(clientePatioDto);
		
		CreditoDto creditoDtoRespuesta = creditoServiceImpl.crearCredito(creditoDto);
		assertNotNull(creditoDtoRespuesta);
		assertNull(creditoDtoRespuesta.getError());
	}

}
