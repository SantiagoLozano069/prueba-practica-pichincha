package com.pichincha.camsacreditoauto.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.pichincha.camsacreditoauto.domain.entity.MarcaEntity;
import com.pichincha.camsacreditoauto.domain.entity.VehiculoEntity;
import com.pichincha.camsacreditoauto.domain.enums.VehiculoErrorNegocioEnum;
import com.pichincha.camsacreditoauto.exception.PichinchaException;
import com.pichincha.camsacreditoauto.repository.service.VehiculoRepositoryService;
import com.pichincha.camsacreditoauto.service.MarcaRepositoryService;
import com.pichincha.camsacreditoauto.service.VehiculoService;
import com.pichincha.camsacreditoauto.service.dto.VehiculoDto;

@Service
public class VehiculoServiceImpl implements VehiculoService{
	
	@Autowired
	private VehiculoRepositoryService vehiculoRepositoryService;
	
	@Autowired
	private MarcaRepositoryService marcaRepositoryService;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public VehiculoDto crearVechiculo(VehiculoDto vehiculotDto) {
		try {
			if (ObjectUtils.isEmpty(vehiculoRepositoryService.consultarVechiculoPorPlaca(vehiculotDto.getPlaca()))) {
				this.validarExistenciaMarca(vehiculotDto);
				VehiculoEntity vehiculoEntity = vehiculoRepositoryService
						.crearVechiculo(modelMapper.map(vehiculotDto, VehiculoEntity.class));
				return modelMapper.map(vehiculoEntity, VehiculoDto.class);
			} else {
				return new VehiculoDto(VehiculoErrorNegocioEnum.PLACA_EXISTENTE.getMensaje() + vehiculotDto.getPlaca());
			}
		} catch (PichinchaException e) {
			return new VehiculoDto(e.getMessage());
		}
	}

	private void validarExistenciaMarca(VehiculoDto vehiculotDto) throws PichinchaException {
		MarcaEntity marcaEntity = marcaRepositoryService.consultarMarcaPorId(vehiculotDto.getMarca().getId());
		if(ObjectUtils.isEmpty(marcaEntity)) {
			throw new PichinchaException(VehiculoErrorNegocioEnum.MARCA_NO_EXISTE.getMensaje() + vehiculotDto.getMarca().getId().toString());
		}
		vehiculotDto.getMarca().setMarca(marcaEntity.getMarca());
	}
	
	
	@Override
	public VehiculoDto consultarVechiculoPorId(Integer idVehiculo) {
		VehiculoEntity vehiculoEntity = vehiculoRepositoryService.consultarVechiculoPorId(idVehiculo);
		if(ObjectUtils.isEmpty(vehiculoEntity)) {
			return new VehiculoDto(VehiculoErrorNegocioEnum.NO_EXISTE_VEHICULO_POR_ID.getMensaje() + idVehiculo.toString());
		}else {
			return modelMapper.map(vehiculoEntity, VehiculoDto.class);
		}
	}

	@Override
	public List<VehiculoDto> consultarVechiculoPorMarca(Integer marca) {
		List<VehiculoEntity> listaVehiculoEntity = vehiculoRepositoryService.consultarVechiculoPorMarca(marca);
		if(ObjectUtils.isEmpty(listaVehiculoEntity)) {
			return Arrays.asList(new VehiculoDto(VehiculoErrorNegocioEnum.NO_EXISTE_VEHICULO_POR_MARCA.getMensaje() + marca.toString()));
		}else {
			return this.mapVehiculoEntityToVehiculoDtoList(listaVehiculoEntity);
		}
	}

	@Override
	public List<VehiculoDto> consultarVechiculoPorModelo(String modelo) {
		List<VehiculoEntity> listaVehiculoEntity = vehiculoRepositoryService.consultarVechiculoPorModelo(modelo);
		if(ObjectUtils.isEmpty(listaVehiculoEntity)) {
			return Arrays.asList(new VehiculoDto(VehiculoErrorNegocioEnum.NO_EXISTE_VEHICULO_POR_MODELO.getMensaje() + modelo));
		}else {
			return this.mapVehiculoEntityToVehiculoDtoList(listaVehiculoEntity);
		}
	}
	
	private List<VehiculoDto> mapVehiculoEntityToVehiculoDtoList(List<VehiculoEntity> listaVehiculoEntity){
		return listaVehiculoEntity.stream().map(entity -> modelMapper.map(entity, VehiculoDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public VehiculoDto editarVechiculo(VehiculoDto vehiculotDto) {
		if (ObjectUtils.isEmpty(vehiculotDto.getId())) {
			return new VehiculoDto(VehiculoErrorNegocioEnum.ID_VEHICULO_NULL.getMensaje());
		} else {
			if (ObjectUtils.isEmpty(vehiculoRepositoryService.consultarVechiculoPorId(vehiculotDto.getId()))) {
				return new VehiculoDto(VehiculoErrorNegocioEnum.NO_EXISTE_VEHICULO_POR_ID.getMensaje()
						+ vehiculotDto.getId().toString());
			} else {
				VehiculoEntity vehiculoEntity = vehiculoRepositoryService
						.editarVechiculo(modelMapper.map(vehiculotDto, VehiculoEntity.class));
				return modelMapper.map(vehiculoEntity, VehiculoDto.class);
			}
		}
	}

	@Override
	public VehiculoDto eliminarVechiculo(Integer idVehiculo) {
		VehiculoEntity vehiculoEntity = vehiculoRepositoryService.consultarVechiculoPorId(idVehiculo);
		if(ObjectUtils.isEmpty(vehiculoEntity)) {
			return new VehiculoDto(VehiculoErrorNegocioEnum.NO_EXISTE_VEHICULO_POR_ID.getMensaje() + idVehiculo.toString());
		}else {
			if(vehiculoRepositoryService.existeInformacionAsociadaParaVehiculo(idVehiculo)) {
				return new VehiculoDto(VehiculoErrorNegocioEnum.INFORMACION_ASOCIADA.getMensaje());
			}
			vehiculoRepositoryService.eliminarVechiculo(idVehiculo);
			return modelMapper.map(vehiculoEntity, VehiculoDto.class);
		}
	}
	
}
