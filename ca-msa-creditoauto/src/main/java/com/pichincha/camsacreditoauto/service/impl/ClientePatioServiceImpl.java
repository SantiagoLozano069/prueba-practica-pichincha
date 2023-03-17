package com.pichincha.camsacreditoauto.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.pichincha.camsacreditoauto.domain.entity.ClienteEntity;
import com.pichincha.camsacreditoauto.domain.entity.ClientePatioEntity;
import com.pichincha.camsacreditoauto.domain.entity.PatioEntity;
import com.pichincha.camsacreditoauto.domain.enums.ClientePatioErrorNegocioEnum;
import com.pichincha.camsacreditoauto.domain.enums.PatioErrorNegocioEnum;
import com.pichincha.camsacreditoauto.exception.PichinchaException;
import com.pichincha.camsacreditoauto.repository.service.ClientePatioRepositoryService;
import com.pichincha.camsacreditoauto.repository.service.ClienteRepositoryService;
import com.pichincha.camsacreditoauto.repository.service.PatioRepositoryService;
import com.pichincha.camsacreditoauto.service.ClientePatioService;
import com.pichincha.camsacreditoauto.service.dto.ClienteDto;
import com.pichincha.camsacreditoauto.service.dto.ClientePatioDto;
import com.pichincha.camsacreditoauto.service.dto.PatioDto;

@Service
public class ClientePatioServiceImpl implements ClientePatioService {

	@Autowired
	private ClientePatioRepositoryService clientePatioRepositoryService;
	
	@Autowired
	private PatioRepositoryService patioRepositoryService;
	
	@Autowired
	private ClienteRepositoryService clienteRepositoryService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public ClientePatioDto crearClientePatio(ClientePatioDto clientePatioDto) {		
		try {
			this.existeErrorDataAsociada(clientePatioDto);
			ClientePatioEntity clientePatioEntity = clientePatioRepositoryService
					.crearClientePatio(modelMapper.map(clientePatioDto, ClientePatioEntity.class));
			return modelMapper.map(clientePatioEntity, ClientePatioDto.class);
		}catch (Exception e) {
			return new ClientePatioDto(e.getMessage());
		}

	}
	
	private void existeErrorDataAsociada(ClientePatioDto clientePatioDto) throws PichinchaException {
		PatioEntity patioEntity = patioRepositoryService.consultarPatioPorId(clientePatioDto.getPatio().getId());
		ClienteEntity clienteEntity = clienteRepositoryService.consultarClientePorId(clientePatioDto.getCliente().getId());
		if(ObjectUtils.isEmpty(clienteEntity)) {
			throw new PichinchaException(ClientePatioErrorNegocioEnum.NO_EXISTE_CLIENTE.getMensaje() + clientePatioDto.getCliente().getId());
		}
		if(ObjectUtils.isEmpty(patioEntity)) {
			throw new PichinchaException(ClientePatioErrorNegocioEnum.NO_EXISTE_PATIO.getMensaje() + clientePatioDto.getPatio().getId());
		}
		clientePatioDto.setCliente(modelMapper.map(clienteEntity, ClienteDto.class));
		clientePatioDto.setPatio(modelMapper.map(patioEntity, PatioDto.class));
	}

	@Override
	public ClientePatioDto consultarClientePatioPorId(Integer idClientePatio) {
		ClientePatioEntity clientePatioEntity = clientePatioRepositoryService.consultarClientePatioPorId(idClientePatio);
		if(ObjectUtils.isEmpty(clientePatioEntity)) {
			return new ClientePatioDto(ClientePatioErrorNegocioEnum.NO_EXISTE_CLIENTE_PATIO.getMensaje() + idClientePatio.toString());
		}else {
			return modelMapper.map(clientePatioEntity, ClientePatioDto.class);
		}
	}

	@Override
	public ClientePatioDto editarClientePatio(ClientePatioDto clientePatioDto) {
		if (ObjectUtils.isEmpty(clientePatioDto.getId())) {
			return new ClientePatioDto(PatioErrorNegocioEnum.ID_NULL.getMensaje());
		} else {
			if (ObjectUtils.isEmpty(clientePatioRepositoryService.consultarClientePatioPorId(clientePatioDto.getId()))) {
				return new ClientePatioDto(ClientePatioErrorNegocioEnum.NO_EXISTE_CLIENTE_PATIO.getMensaje()
						+ clientePatioDto.getId().toString());
			} else {
				return this.crearClientePatio(clientePatioDto);
			}
		}
	}

	@Override
	public ClientePatioDto eliminarClientePatio(Integer idClientePatio) {
		ClientePatioEntity clientePatioEntity = clientePatioRepositoryService.consultarClientePatioPorId(idClientePatio);
		if(ObjectUtils.isEmpty(clientePatioEntity)) {
			return new ClientePatioDto(ClientePatioErrorNegocioEnum.NO_EXISTE_CLIENTE_PATIO.getMensaje() + idClientePatio.toString());
		}else {
			if (clientePatioRepositoryService.existeInformacionAsociadaParaClientePatio(
					clientePatioEntity.getCliente().getId(), clientePatioEntity.getPatio().getId())) {
				return new ClientePatioDto(ClientePatioErrorNegocioEnum.INFORMACION_ASOCIADA.getMensaje());
			}
			clientePatioRepositoryService.eliminarPatio(idClientePatio);
			return modelMapper.map(clientePatioEntity, ClientePatioDto.class);
		}
	}

}
