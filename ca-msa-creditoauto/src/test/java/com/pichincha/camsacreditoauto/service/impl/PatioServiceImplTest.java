package com.pichincha.camsacreditoauto.service.impl;

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

import com.pichincha.camsacreditoauto.domain.entity.PatioEntity;
import com.pichincha.camsacreditoauto.domain.enums.PatioErrorNegocioEnum;
import com.pichincha.camsacreditoauto.repository.impl.PatioRepositoryServiceImpl;
import com.pichincha.camsacreditoauto.service.dto.PatioDto;

@SpringBootTest
public class PatioServiceImplTest {
	
	@Mock
    private ModelMapper modelMapper;
    
    @Mock
    private PatioRepositoryServiceImpl patioRepositoryServiceImpl;
    
    @InjectMocks
    private PatioServiceImpl patioServiceImpl;
    

	private PatioDto patioDto;
	private PatioEntity patioEntity;

	
	@BeforeEach
    void init() {
		patioDto = new PatioDto();
		patioDto.setNombre("patio tuerca2");
		patioDto.setTelefono("1233232");
		patioDto.setNombre("Av amazonas y esquina");
		patioDto.setNumeroPuntoVenta(Long.valueOf(12333));

		
		patioEntity = new PatioEntity();
		patioEntity.setNombre("patio tuerca2");
		patioEntity.setTelefono("1233232");
		patioEntity.setNombre("Av amazonas y esquina");
		patioEntity.setNumeroPuntoVenta(Long.valueOf(12333));
    } 
	
	
	@Test
    void crear_patio_correctamente() {		
        when(modelMapper.map(patioDto, PatioEntity.class)).thenReturn(patioEntity);
        when(patioRepositoryServiceImpl.crearPatio(patioEntity)).thenReturn(patioEntity);
        when(modelMapper.map(patioEntity, PatioDto.class)).thenReturn(patioDto);
        
        PatioDto patioDtoRespuesta = patioServiceImpl.crearPatio(patioDto);  
        assertNotNull(patioDtoRespuesta);
        assertNull(patioDtoRespuesta.getError());
    } 
	
	
	@Test
    void consultar_patio_por_id_correctamente() {	
        when(patioRepositoryServiceImpl.consultarPatioPorId(Integer.valueOf(1))).thenReturn(patioEntity);
        when(modelMapper.map(patioEntity, PatioDto.class)).thenReturn(patioDto);

        PatioDto patioDto = patioServiceImpl.consultarPatioPorId(Integer.valueOf(1));
        assertNotNull(patioDto);
    } 
	
	@Test
    void consultar_patio_por_id_inexistente() {	
        when(patioRepositoryServiceImpl.consultarPatioPorId(Integer.valueOf(1))).thenReturn(null);
        
        PatioDto patioDto = patioServiceImpl.consultarPatioPorId(Integer.valueOf(1));
        assertNotNull(patioDto.getError());
        assertEquals(patioDto.getError(), PatioErrorNegocioEnum.NO_EXISTE_PATIO.getMensaje() + Integer.valueOf(1).toString());
    } 
	
	
	@Test
    void editar_patio_exitosamente() {	
		patioDto.setId(1);
        when(patioRepositoryServiceImpl.consultarPatioPorId(patioDto.getId())).thenReturn(patioEntity);
        when(modelMapper.map(patioDto, PatioEntity.class)).thenReturn(patioEntity);
        when(patioRepositoryServiceImpl.crearPatio(patioEntity)).thenReturn(patioEntity);
        when(modelMapper.map(patioEntity, PatioDto.class)).thenReturn(patioDto);

        PatioDto patioDtoResponse = patioServiceImpl.editarPatio(patioDto);
        assertNotNull(patioDtoResponse);
    } 
	
	@Test
    void editar_patio_error_id_enexistente() {	
		patioDto.setId(1);
        when(patioRepositoryServiceImpl.consultarPatioPorId(patioDto.getId())).thenReturn(null);
        PatioDto patioDtoResponse = patioServiceImpl.editarPatio(patioDto);
        assertNotNull(patioDtoResponse.getError());
        assertEquals(patioDtoResponse.getError(), PatioErrorNegocioEnum.NO_EXISTE_PATIO.getMensaje()
				+ patioDto.getId().toString());
    } 
	
	@Test
    void editar_patio_error_id_null() {	
        PatioDto patioDtoResponse = patioServiceImpl.editarPatio(patioDto);
        assertNotNull(patioDtoResponse.getError());
        assertEquals(patioDtoResponse.getError(), PatioErrorNegocioEnum.ID_NULL.getMensaje());
    } 

	
	@Test
    void eliminar_patio_correctamente() {	
        when(patioRepositoryServiceImpl.consultarPatioPorId(Integer.valueOf(1))).thenReturn(patioEntity);
        doNothing().when(patioRepositoryServiceImpl).eliminarPatio(Integer.valueOf(1));
        when(modelMapper.map(patioEntity, PatioDto.class)).thenReturn(patioDto);
        PatioDto patioDtoRespuesta = patioServiceImpl.eliminarPatio(Integer.valueOf(1));
        assertNotNull(patioDtoRespuesta);
    } 
	
	@Test
    void eliminar_patio_error_id_inexistente() {	
        when(patioRepositoryServiceImpl.consultarPatioPorId(Integer.valueOf(1))).thenReturn(null);
        PatioDto patioDtoRespuesta = patioServiceImpl.eliminarPatio(Integer.valueOf(1));
        assertNotNull(patioDtoRespuesta.getError());
        assertEquals(patioDtoRespuesta.getError(), PatioErrorNegocioEnum.NO_EXISTE_PATIO.getMensaje() + Integer.valueOf(1).toString());
    }

}
