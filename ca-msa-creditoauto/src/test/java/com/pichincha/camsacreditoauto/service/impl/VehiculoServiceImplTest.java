package com.pichincha.camsacreditoauto.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.pichincha.camsacreditoauto.domain.entity.MarcaEntity;
import com.pichincha.camsacreditoauto.domain.entity.VehiculoEntity;
import com.pichincha.camsacreditoauto.domain.enums.VehiculoErrorNegocioEnum;
import com.pichincha.camsacreditoauto.exception.PichinchaException;
import com.pichincha.camsacreditoauto.repository.impl.VehiculoRepositoryImpl;
import com.pichincha.camsacreditoauto.repository.service.VehiculoRepositoryService;
import com.pichincha.camsacreditoauto.service.MarcaRepositoryService;
import com.pichincha.camsacreditoauto.service.dto.MarcaDto;
import com.pichincha.camsacreditoauto.service.dto.VehiculoDto;

@SpringBootTest
class VehiculoServiceImplTest {
	
    @Mock
    private ModelMapper modelMapper;
    
    @Mock
    private VehiculoRepositoryImpl vehiculoRepositoryImpl;
    
    @Mock
	private MarcaRepositoryServiceImpl marcaRepositoryServiceImpl;
    
    @InjectMocks
    private VehiculoServiceImpl vehiculoServiceImpl;
    

	private VehiculoDto vehiculoDto;
	private VehiculoEntity vehiculoEntity;
	private MarcaEntity marcaEntity;

	
	@BeforeEach
    void init() {
		vehiculoDto = new VehiculoDto();
		vehiculoDto.setPlaca("PBG-1234");
		vehiculoDto.setModelo("tav-4");
		vehiculoDto.setNumeroChasis("12345");
		vehiculoDto.setTipo("suv");
		vehiculoDto.setCilindraje(2.0);
		vehiculoDto.setAvaluo(BigInteger.valueOf(23000));
		vehiculoDto.setMarca(new MarcaDto(Integer.valueOf(1)));
		
		vehiculoEntity = new VehiculoEntity();
		vehiculoEntity.setPlaca("PBG-1234");
		vehiculoEntity.setModelo("tav-4");
		vehiculoEntity.setNumeroChasis("12345");
		vehiculoEntity.setTipo("suv");
		vehiculoEntity.setCilindraje(2.0);
		vehiculoEntity.setAvaluo(BigInteger.valueOf(23000));
		vehiculoEntity.setMarca(new MarcaEntity(Integer.valueOf(1)));
		
		marcaEntity =  new MarcaEntity();
		marcaEntity.setId(1);
    } 
	
	
	@Test
    void crear_vehiculo_correctamente() {		
        when(vehiculoRepositoryImpl.consultarVechiculoPorPlaca(vehiculoDto.getPlaca())).thenReturn(null);
        when(marcaRepositoryServiceImpl.consultarMarcaPorId(vehiculoDto.getMarca().getId())).thenReturn(marcaEntity);
        when(modelMapper.map(vehiculoDto, VehiculoEntity.class)).thenReturn(vehiculoEntity);
        when(vehiculoRepositoryImpl.crearVechiculo(vehiculoEntity)).thenReturn(vehiculoEntity);
        when(modelMapper.map(vehiculoEntity, VehiculoDto.class)).thenReturn(vehiculoDto);
        
        VehiculoDto vehiculoDtoReturn = vehiculoServiceImpl.crearVechiculo(vehiculoDto);  
        assertNotNull(vehiculoDtoReturn);
        assertNull(vehiculoDtoReturn.getError());
    } 
	
	@Test
    void crear_vehiculo_con_placa_existente() {	
        when(vehiculoRepositoryImpl.consultarVechiculoPorPlaca(vehiculoDto.getPlaca())).thenReturn(vehiculoEntity);
        VehiculoDto vehiculoDtoReturn = vehiculoServiceImpl.crearVechiculo(vehiculoDto);
        assertNotNull(vehiculoDtoReturn);
        assertEquals(vehiculoDtoReturn.getError(), VehiculoErrorNegocioEnum.PLACA_EXISTENTE.getMensaje() + vehiculoDto.getPlaca());
    } 
	
	@Test
    void crear_vehiculo_con_marca_inexistente() {	
        when(vehiculoRepositoryImpl.consultarVechiculoPorPlaca(vehiculoDto.getPlaca())).thenReturn(null);
        when(marcaRepositoryServiceImpl.consultarMarcaPorId(vehiculoDto.getMarca().getId())).thenReturn(null);
        VehiculoDto vehiculoDtoReturn = vehiculoServiceImpl.crearVechiculo(vehiculoDto);
        assertNotNull(vehiculoDtoReturn.getError());
        assertEquals(vehiculoDtoReturn.getError(), VehiculoErrorNegocioEnum.MARCA_NO_EXISTE.getMensaje() + vehiculoDto.getMarca().getId().toString());
    }
	
	@Test
    void consultar_vehiculo_por_id_correctamente() {	
        when(vehiculoRepositoryImpl.consultarVechiculoPorId(Integer.valueOf(1))).thenReturn(vehiculoEntity);
        when(modelMapper.map(vehiculoEntity, VehiculoDto.class)).thenReturn(vehiculoDto);

        VehiculoDto vehiculoDtoReturn = vehiculoServiceImpl.consultarVechiculoPorId(Integer.valueOf(1));
        assertNotNull(vehiculoDtoReturn);
    } 
	
	@Test
    void consultar_vehiculo_por_id_inexistente() {	
        when(vehiculoRepositoryImpl.consultarVechiculoPorId(Integer.valueOf(1))).thenReturn(null);
        
        VehiculoDto vehiculoDtoReturn = vehiculoServiceImpl.consultarVechiculoPorId(Integer.valueOf(1));
        assertNotNull(vehiculoDtoReturn.getError());
        assertEquals(vehiculoDtoReturn.getError(), VehiculoErrorNegocioEnum.NO_EXISTE_VEHICULO_POR_ID.getMensaje() + Integer.valueOf(1).toString());

    } 
	
	
	@Test
    void consultar_vehiculo_por_marca_exitosamente() {	
        when(vehiculoRepositoryImpl.consultarVechiculoPorMarca(Integer.valueOf(1))).thenReturn(Arrays.asList(vehiculoEntity));
        when(modelMapper.map(vehiculoEntity, VehiculoDto.class)).thenReturn(vehiculoDto);

        List<VehiculoDto> vehiculoDtoList = vehiculoServiceImpl.consultarVechiculoPorMarca(Integer.valueOf(1));
        assertNotNull(vehiculoDtoList);
    } 
	
	@Test
    void consultar_vehiculo_por_marca_inexistente() {	
        when(vehiculoRepositoryImpl.consultarVechiculoPorMarca(Integer.valueOf(1))).thenReturn(null);
        
        List<VehiculoDto> vehiculoDtoList = vehiculoServiceImpl.consultarVechiculoPorMarca(Integer.valueOf(1));
        assertNotNull(vehiculoDtoList.get(0).getError());
        assertEquals(vehiculoDtoList.get(0).getError(), VehiculoErrorNegocioEnum.NO_EXISTE_VEHICULO_POR_MARCA.getMensaje() + Integer.valueOf(1).toString());
    } 
	
	
	@Test
    void consultar_vehiculo_por_modelo_exitosamente() {	
        when(vehiculoRepositoryImpl.consultarVechiculoPorModelo("tav-4")).thenReturn(Arrays.asList(vehiculoEntity));
        when(modelMapper.map(vehiculoEntity, VehiculoDto.class)).thenReturn(vehiculoDto);

        List<VehiculoDto> vehiculoDtoList = vehiculoServiceImpl.consultarVechiculoPorModelo("tav-4");
        assertNotNull(vehiculoDtoList);
    } 
	
	@Test
    void consultar_vehiculo_por_modelo_inexistente() {	
        when(vehiculoRepositoryImpl.consultarVechiculoPorModelo("tav-4")).thenReturn(null);
        
        List<VehiculoDto> vehiculoDtsoList = vehiculoServiceImpl.consultarVechiculoPorModelo("tav-4");
        assertNotNull(vehiculoDtsoList.get(0).getError());
        assertEquals(vehiculoDtsoList.get(0).getError(), VehiculoErrorNegocioEnum.NO_EXISTE_VEHICULO_POR_MODELO.getMensaje() + "tav-4");
    } 
	
	
	@Test
    void editar_vehiculo_exitosamente() {	
        when(vehiculoRepositoryImpl.consultarVechiculoPorId(Integer.valueOf(1))).thenReturn(vehiculoEntity);
        when(modelMapper.map(vehiculoDto, VehiculoEntity.class)).thenReturn(vehiculoEntity);
        when(vehiculoRepositoryImpl.editarVechiculo(vehiculoEntity)).thenReturn(vehiculoEntity);
        when(modelMapper.map(vehiculoEntity, VehiculoDto.class)).thenReturn(vehiculoDto);

        VehiculoDto vehiculoDtoReturn = vehiculoServiceImpl.editarVechiculo(vehiculoDto);
        assertNotNull(vehiculoDtoReturn);
    } 
	
	@Test
    void editar_vehiculo_error_id_enexistente() {	
		vehiculoDto.setId(1);
        when(vehiculoRepositoryImpl.consultarVechiculoPorId(vehiculoDto.getId())).thenReturn(null);
        VehiculoDto vehiculoDtoReturn = vehiculoServiceImpl.editarVechiculo(vehiculoDto);
        assertNotNull(vehiculoDtoReturn.getError());
        assertEquals(vehiculoDtoReturn.getError(), VehiculoErrorNegocioEnum.NO_EXISTE_VEHICULO_POR_ID.getMensaje()
				+ vehiculoDto.getId().toString());
    } 
	
	@Test
    void editar_vehiculo_error_id_null() {	
        VehiculoDto vehiculoDtoReturn = vehiculoServiceImpl.editarVechiculo(vehiculoDto);
        assertNotNull(vehiculoDtoReturn.getError());
        assertEquals(vehiculoDtoReturn.getError(), VehiculoErrorNegocioEnum.ID_VEHICULO_NULL.getMensaje());
    } 

	
	@Test
    void eliminar_vehiculo_correctamente() {	
        when(vehiculoRepositoryImpl.consultarVechiculoPorId(Integer.valueOf(1))).thenReturn(vehiculoEntity);
        doNothing().when(vehiculoRepositoryImpl).eliminarVechiculo(Integer.valueOf(1));
        when(modelMapper.map(vehiculoEntity, VehiculoDto.class)).thenReturn(vehiculoDto);
        VehiculoDto vehiculoDtoReturn = vehiculoServiceImpl.eliminarVechiculo(Integer.valueOf(1));
        assertNotNull(vehiculoDtoReturn);
    } 
	
	@Test
    void eliminar_vehiculo_error_id_inexistente() {	
        when(vehiculoRepositoryImpl.consultarVechiculoPorId(Integer.valueOf(1))).thenReturn(null);
        VehiculoDto vehiculoDtoReturn = vehiculoServiceImpl.eliminarVechiculo(Integer.valueOf(1));
        assertNotNull(vehiculoDtoReturn.getError());
        assertEquals(vehiculoDtoReturn.getError(), VehiculoErrorNegocioEnum.NO_EXISTE_VEHICULO_POR_ID.getMensaje() + Integer.valueOf(1).toString());
    }
}
