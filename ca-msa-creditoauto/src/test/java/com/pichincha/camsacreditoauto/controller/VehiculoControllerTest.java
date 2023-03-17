package com.pichincha.camsacreditoauto.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigInteger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pichincha.camsacreditoauto.service.VehiculoService;
import com.pichincha.camsacreditoauto.service.dto.MarcaDto;
import com.pichincha.camsacreditoauto.service.dto.VehiculoDto;

@RunWith(SpringRunner.class)
@WebMvcTest(VehiculoController.class)
public class VehiculoControllerTest {
	
	 	@Autowired
	    private MockMvc mockMvc;
	 	
	 	@Autowired
	    private ObjectMapper objectMapper;

	    @MockBean
	    private VehiculoService vehiculoService;

	    
	    private VehiculoDto vehiculoDto;

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
	    }

	   @Test
	    void crear_vehiculo_retorna_200() throws Exception {
	        mockMvc.perform(MockMvcRequestBuilders.post("/vehiculo/crear")
	                        .contentType("application/json")
	                        .content(objectMapper.writeValueAsString(vehiculoDto))
	                )
	                .andExpect(status().isOk());

	        //se verifica la llamada a la logica de negocio
	        ArgumentCaptor<VehiculoDto> vehiculoCaptor = ArgumentCaptor.forClass(VehiculoDto.class);
	        Mockito.verify(vehiculoService, Mockito.times(1)).crearVechiculo(vehiculoCaptor.capture());
	        assertEquals(vehiculoCaptor.getValue().getPlaca(), vehiculoDto.getPlaca());
	        assertEquals(vehiculoCaptor.getValue().getModelo(), vehiculoDto.getModelo());
	    } 
	   
	   @Test
	    void crear_vehiculo_retorna_400() throws Exception {
		   vehiculoDto.setPlaca(null);
	        mockMvc.perform(MockMvcRequestBuilders.post("/vehiculo/crear")
	                        .contentType("application/json")
	                        .content(objectMapper.writeValueAsString(vehiculoDto))
	                )
	                .andExpect(status().isBadRequest());
	    } 
	   
	   @Test
	    void consultar_vehiculo_por_id_retorna_200() throws Exception {
	        mockMvc.perform(MockMvcRequestBuilders.get("/vehiculo/buscar?id=1")
	                        .contentType("application/json")
	                )
	                .andExpect(status().isOk());

	        //se verifica la llamada a la logica de negocio
	        Mockito.verify(vehiculoService, Mockito.times(1)).consultarVechiculoPorId(Integer.valueOf(1));
	    } 
	   
	   @Test
	    void consultar_vehiculo_por_id_retorna_400() throws Exception {
	        mockMvc.perform(MockMvcRequestBuilders.get("/vehiculo/buscar?id=")
	                        .contentType("application/json")
	                )
	                .andExpect(status().isBadRequest());
	    } 
	   
	   @Test
	    void consultar_vehiculo_por_marca_retorna_200() throws Exception {
	        mockMvc.perform(MockMvcRequestBuilders.get("/vehiculo/buscarPorMarca?marca=1")
	                        .contentType("application/json")
	                )
	                .andExpect(status().isOk());

	        //se verifica la llamada a la logica de negocio
	        Mockito.verify(vehiculoService, Mockito.times(1)).consultarVechiculoPorMarca(Integer.valueOf(1));
	    } 
	   
	   @Test
	    void consultar_vehiculo_por_marca_retorna_400() throws Exception {
		   vehiculoDto.setPlaca(null);
	        mockMvc.perform(MockMvcRequestBuilders.get("/vehiculo/buscarPorMarca?marca=")
	                        .contentType("application/json")
	                )
	                .andExpect(status().isBadRequest());
	    } 
	   
	   @Test
	    void consultar_vehiculo_por_modelo_retorna_200() throws Exception {
	        mockMvc.perform(MockMvcRequestBuilders.get("/vehiculo/consultarPorModelo?modelo=1")
	                        .contentType("application/json")
	                )
	                .andExpect(status().isOk());

	        //se verifica la llamada a la logica de negocio
	        Mockito.verify(vehiculoService, Mockito.times(1)).consultarVechiculoPorModelo("1");
	    } 
	   
	   
	   @Test
	    void actualizar_vehiculo_retorna_200() throws Exception {
		   	vehiculoDto.setId(1);
	        mockMvc.perform(MockMvcRequestBuilders.put("/vehiculo/actualizar")
	                        .contentType("application/json")
	                        .content(objectMapper.writeValueAsString(vehiculoDto))
	                )
	                .andExpect(status().isOk());

	        //se verifica la llamada a la logica de negocio
	        ArgumentCaptor<VehiculoDto> vehiculoCaptor = ArgumentCaptor.forClass(VehiculoDto.class);
	        Mockito.verify(vehiculoService, Mockito.times(1)).editarVechiculo(vehiculoCaptor.capture());
	        assertEquals(vehiculoCaptor.getValue().getPlaca(), vehiculoDto.getPlaca());
	        assertEquals(vehiculoCaptor.getValue().getId(), vehiculoDto.getId());
	    } 
	   
	   @Test
	    void actualizar_vehiculo_retorna_400() throws Exception {
		   vehiculoDto.setPlaca(null);
	        mockMvc.perform(MockMvcRequestBuilders.put("/vehiculo/actualizar")
	                        .contentType("application/json")
	                        .content(objectMapper.writeValueAsString(vehiculoDto))
	                )
	                .andExpect(status().isBadRequest());
	    } 
	   
	   
	   @Test
	    void eliminar_vehiculo_retorna_200() throws Exception {
	        mockMvc.perform(MockMvcRequestBuilders.delete("/vehiculo/eliminar?id=1")
	                        .contentType("application/json")
	                )
	                .andExpect(status().isOk());

	        //se verifica la llamada a la logica de negocio
	        Mockito.verify(vehiculoService, Mockito.times(1)).eliminarVechiculo(Integer.valueOf(1));
	    } 
	   
	   @Test
	    void eliminar_vehiculo_retorna_400() throws Exception {
		   vehiculoDto.setPlaca(null);
	        mockMvc.perform(MockMvcRequestBuilders.delete("/vehiculo/eliminar?id=")
	                        .contentType("application/json")
	                )
	                .andExpect(status().isBadRequest());
	    } 
}
