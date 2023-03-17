package com.pichincha.camsacreditoauto.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.pichincha.camsacreditoauto.domain.entity.ClienteEntity;
import com.pichincha.camsacreditoauto.domain.entity.ClientePatioEntity;
import com.pichincha.camsacreditoauto.domain.entity.PatioEntity;
import com.pichincha.camsacreditoauto.domain.enums.ClientePatioErrorNegocioEnum;
import com.pichincha.camsacreditoauto.domain.enums.PatioErrorNegocioEnum;
import com.pichincha.camsacreditoauto.repository.impl.ClientePatioRepositoryServiceImpl;
import com.pichincha.camsacreditoauto.repository.impl.ClienteRepositoryServiceImpl;
import com.pichincha.camsacreditoauto.repository.impl.PatioRepositoryServiceImpl;
import com.pichincha.camsacreditoauto.service.dto.ClienteDto;
import com.pichincha.camsacreditoauto.service.dto.ClientePatioDto;
import com.pichincha.camsacreditoauto.service.dto.PatioDto;

@SpringBootTest
class ClientePatioServiceImplTest {

	
	@Mock
    private ModelMapper modelMapper;
    
    @Mock
    private ClientePatioRepositoryServiceImpl clientePatioRepositoryServiceImpl;
    
    @Mock
    private PatioRepositoryServiceImpl patioRepositoryServiceImpl;
    
    @Mock
    private ClienteRepositoryServiceImpl clienteRepositoryServiceImpl;
    
    @InjectMocks
    private ClientePatioServiceImpl clientePatioServiceImpl;
    

	private ClienteDto clienteDto;
	private PatioDto patioDto;
	private ClientePatioDto clientePatioDto;

	private ClienteEntity clienteEntity;
	private PatioEntity patioEntity;
	private ClientePatioEntity clientePatioEntity;

	
	@BeforeEach
    void init() {
		patioDto = new PatioDto();
		patioDto.setId(1);
		
		clienteDto = new ClienteDto(1);
		
		clientePatioDto = new ClientePatioDto();
		clientePatioDto.setCliente(clienteDto);
		clientePatioDto.setPatio(patioDto);
		
		patioEntity = new PatioEntity();
		patioEntity.setId(1);
		
		clienteEntity = new ClienteEntity(Integer.valueOf(1));
		
		clientePatioEntity = new ClientePatioEntity();
		clientePatioEntity.setCliente(clienteEntity);
		clientePatioEntity.setPatio(patioEntity);
    } 
	
	
	@Test
    void crear_cliente_patio_correctamente() {		
        when(patioRepositoryServiceImpl.consultarPatioPorId(clientePatioDto.getPatio().getId())).thenReturn(patioEntity);
        when(clienteRepositoryServiceImpl.consultarClientePorId(clientePatioDto.getCliente().getId())).thenReturn(clienteEntity);
        when(modelMapper.map(patioEntity, PatioDto.class)).thenReturn(patioDto);
        when(modelMapper.map(clienteEntity, ClienteDto.class)).thenReturn(clienteDto);
        when(modelMapper.map(clientePatioDto, ClientePatioEntity.class)).thenReturn(clientePatioEntity);
        when(clientePatioRepositoryServiceImpl.crearClientePatio(clientePatioEntity)).thenReturn(clientePatioEntity);
        when(modelMapper.map(clientePatioEntity, ClientePatioDto.class)).thenReturn(clientePatioDto);

        ClientePatioDto clientePatioDtoRespuesta = clientePatioServiceImpl.crearClientePatio(clientePatioDto);  
        assertNotNull(clientePatioDtoRespuesta);
        assertNull(clientePatioDtoRespuesta.getError());
    } 
	
	@Test
    void crear_cliente_patio_error_informacion_asociada() {		
        when(patioRepositoryServiceImpl.consultarPatioPorId(clientePatioDto.getPatio().getId())).thenReturn(null);
        when(clienteRepositoryServiceImpl.consultarClientePorId(clientePatioDto.getCliente().getId())).thenReturn(null);
       
        ClientePatioDto clientePatioDtoRespuesta =  clientePatioServiceImpl.crearClientePatio(clientePatioDto);
        assertNotNull(clientePatioDtoRespuesta.getError());
        assertThat(clientePatioDtoRespuesta.getError().equals(ClientePatioErrorNegocioEnum.NO_EXISTE_CLIENTE.getMensaje() + clientePatioDto.getCliente().getId()) ||
        		clientePatioDtoRespuesta.getError().equals(ClientePatioErrorNegocioEnum.NO_EXISTE_PATIO.getMensaje() + clientePatioDto.getPatio().getId()));
	} 
	
	
	@Test
    void consultar_cliente_patio_por_id_correctamente() {	
        when(clientePatioRepositoryServiceImpl.consultarClientePatioPorId(Integer.valueOf(1))).thenReturn(clientePatioEntity);
        when(modelMapper.map(clientePatioEntity, ClientePatioDto.class)).thenReturn(clientePatioDto);

        ClientePatioDto clientePatioDtoReturn = clientePatioServiceImpl.consultarClientePatioPorId(Integer.valueOf(1));
        assertNotNull(clientePatioDtoReturn);
    } 
	
	@Test
    void consultar_cliente_patio_por_id_inexistente() {	
        when(clientePatioRepositoryServiceImpl.consultarClientePatioPorId(Integer.valueOf(1))).thenReturn(null);
        
        ClientePatioDto clientePatioDtoReturn = clientePatioServiceImpl.consultarClientePatioPorId(Integer.valueOf(1));
        assertNotNull(clientePatioDtoReturn.getError());
        assertEquals(clientePatioDtoReturn.getError(), ClientePatioErrorNegocioEnum.NO_EXISTE_CLIENTE_PATIO.getMensaje() + Integer.valueOf(1).toString());
    } 
	
	
	@Test
    void editar_cliente_patio_exitosamente() {	
		clientePatioDto.setId(1);
        when(clientePatioRepositoryServiceImpl.consultarClientePatioPorId(clientePatioDto.getId())).thenReturn(clientePatioEntity);
        when(patioRepositoryServiceImpl.consultarPatioPorId(clientePatioDto.getPatio().getId())).thenReturn(patioEntity);
        when(clienteRepositoryServiceImpl.consultarClientePorId(clientePatioDto.getCliente().getId())).thenReturn(clienteEntity);
        when(modelMapper.map(patioEntity, PatioDto.class)).thenReturn(patioDto);
        when(modelMapper.map(clienteEntity, ClienteDto.class)).thenReturn(clienteDto);
        when(modelMapper.map(clientePatioDto, ClientePatioEntity.class)).thenReturn(clientePatioEntity);
        when(clientePatioRepositoryServiceImpl.crearClientePatio(clientePatioEntity)).thenReturn(clientePatioEntity);
        when(modelMapper.map(clientePatioEntity, ClientePatioDto.class)).thenReturn(clientePatioDto);

        ClientePatioDto clientePatioDtoReturn = clientePatioServiceImpl.editarClientePatio(clientePatioDto);
        assertNotNull(clientePatioDtoReturn);
    } 
	
	@Test
    void editar_patio_error_id_enexistente() {	
		clientePatioDto.setId(1);
        when(clientePatioRepositoryServiceImpl.consultarClientePatioPorId(clientePatioDto.getId())).thenReturn(null);
        ClientePatioDto clientePatioDtoReturn = clientePatioServiceImpl.editarClientePatio(clientePatioDto);
        assertNotNull(clientePatioDtoReturn.getError());
        assertEquals(clientePatioDtoReturn.getError(), ClientePatioErrorNegocioEnum.NO_EXISTE_CLIENTE_PATIO.getMensaje()
				+ patioDto.getId().toString());
    } 
	
	@Test
    void editar_patio_error_id_null() {	
        ClientePatioDto clientePatioDtoReturn = clientePatioServiceImpl.editarClientePatio(clientePatioDto);
        assertNotNull(clientePatioDtoReturn.getError());
        assertEquals(clientePatioDtoReturn.getError(), PatioErrorNegocioEnum.ID_NULL.getMensaje());
    } 

	
	@Test
    void eliminar_patio_correctamente() {	
        when(clientePatioRepositoryServiceImpl.consultarClientePatioPorId(Integer.valueOf(1))).thenReturn(clientePatioEntity);
        doNothing().when(clientePatioRepositoryServiceImpl).eliminarPatio(Integer.valueOf(1));
        when(modelMapper.map(clientePatioEntity, ClientePatioDto.class)).thenReturn(clientePatioDto);
        ClientePatioDto clientePatioDtoReturn = clientePatioServiceImpl.eliminarClientePatio(Integer.valueOf(1));
        assertNotNull(clientePatioDtoReturn);
    } 
	
	@Test
    void eliminar_patio_error_id_inexistente() {	
        when(clientePatioRepositoryServiceImpl.consultarClientePatioPorId(Integer.valueOf(1))).thenReturn(null);
        ClientePatioDto clientePatioDtoReturn = clientePatioServiceImpl.eliminarClientePatio(Integer.valueOf(1));
        assertNotNull(clientePatioDtoReturn.getError());
        assertEquals(clientePatioDtoReturn.getError(), ClientePatioErrorNegocioEnum.NO_EXISTE_CLIENTE_PATIO.getMensaje() + Integer.valueOf(1).toString());
    }


}
