package com.pichincha.camsacreditoauto.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.pichincha.camsacreditoauto.service.PatioService;
import com.pichincha.camsacreditoauto.service.dto.PatioDto;

@RunWith(SpringRunner.class)
@WebMvcTest(PatioController.class)
public class PatioControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
 	
 	@Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PatioService patioService;

    
    private PatioDto patioDto;

    @BeforeEach
    void init() {
    	patioDto = new PatioDto();
		patioDto.setNombre("patio tuerca2");
		patioDto.setTelefono("1233232");
		patioDto.setDireccion("Av amazonas y esquina");
		patioDto.setNumeroPuntoVenta(Long.valueOf(12333));
    }

   @Test
    void crear_patio_retorna_200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/patio/crear")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(patioDto))
                )
                .andExpect(status().isOk());

        //se verifica la llamada a la logica de negocio
        ArgumentCaptor<PatioDto> patioCaptor = ArgumentCaptor.forClass(PatioDto.class);
        Mockito.verify(patioService, Mockito.times(1)).crearPatio(patioCaptor.capture());
        assertEquals(patioCaptor.getValue().getNombre(), patioDto.getNombre());
        assertEquals(patioCaptor.getValue().getDireccion(), patioDto.getDireccion());
    } 
   
   @Test
    void crear_patio_retorna_400() throws Exception {
	   patioDto.setNombre(null);
        mockMvc.perform(MockMvcRequestBuilders.post("/patio/crear")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(patioDto))
                )
                .andExpect(status().isBadRequest());
    } 
   
   @Test
    void consultar_patio_por_id_retorna_200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/patio/buscar?id=1")
                        .contentType("application/json")
                )
                .andExpect(status().isOk());

        //se verifica la llamada a la logica de negocio
        Mockito.verify(patioService, Mockito.times(1)).consultarPatioPorId(Integer.valueOf(1));
    } 
   
   @Test
    void consultar_patio_por_id_retorna_400() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/patio/buscar?id=")
                        .contentType("application/json")
                )
                .andExpect(status().isBadRequest());
    } 
   
   @Test
    void actualizar_patio_retorna_200() throws Exception {
	   	patioDto.setId(1);
        mockMvc.perform(MockMvcRequestBuilders.put("/patio/actualizar")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(patioDto))
                )
                .andExpect(status().isOk());

        //se verifica la llamada a la logica de negocio
        ArgumentCaptor<PatioDto> patioCaptor = ArgumentCaptor.forClass(PatioDto.class);
        Mockito.verify(patioService, Mockito.times(1)).editarPatio(patioCaptor.capture());
        assertEquals(patioCaptor.getValue().getNombre(), patioDto.getNombre());
        assertEquals(patioCaptor.getValue().getDireccion(), patioDto.getDireccion());
    } 
   
   @Test
    void actualizar_patio_retorna_400() throws Exception {
	   patioDto.setDireccion(null);
        mockMvc.perform(MockMvcRequestBuilders.put("/patio/actualizar")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(patioDto))
                )
                .andExpect(status().isBadRequest());
    } 
   
   
   @Test
    void eliminar_patio_retorna_200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/patio/eliminar?id=1")
                        .contentType("application/json")
                )
                .andExpect(status().isOk());

        //se verifica la llamada a la logica de negocio
        Mockito.verify(patioService, Mockito.times(1)).eliminarPatio(Integer.valueOf(1));
    } 
   
   @Test
    void eliminar_patio_retorna_400() throws Exception {
	   patioDto.setDireccion(null);
        mockMvc.perform(MockMvcRequestBuilders.delete("/patio/eliminar?id=")
                        .contentType("application/json")
                )
                .andExpect(status().isBadRequest());
    } 

}
