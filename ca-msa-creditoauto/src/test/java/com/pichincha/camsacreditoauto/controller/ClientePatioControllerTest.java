package com.pichincha.camsacreditoauto.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

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
import com.pichincha.camsacreditoauto.service.ClientePatioService;
import com.pichincha.camsacreditoauto.service.dto.ClienteDto;
import com.pichincha.camsacreditoauto.service.dto.ClientePatioDto;
import com.pichincha.camsacreditoauto.service.dto.PatioDto;

@RunWith(SpringRunner.class)
@WebMvcTest(ClientePatioController.class)
public class ClientePatioControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
 	
 	@Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ClientePatioService clientePatioService;

    
	private ClienteDto clienteDto;
	private PatioDto patioDto;
	private ClientePatioDto clientePatioDto;
    

    @BeforeEach
    void init() {
    	patioDto = new PatioDto();
		patioDto.setId(1);
		
		clienteDto = new ClienteDto(1);
		
		clientePatioDto = new ClientePatioDto();
		clientePatioDto.setCliente(clienteDto);
		clientePatioDto.setPatio(patioDto);
		clientePatioDto.setFechaAsignacion(LocalDate.now());
    }

   @Test
    void crear_cliente_patio_retorna_200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/clientePatio/crear")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(clientePatioDto))
                )
                .andExpect(status().isOk());

        //se verifica la llamada a la logica de negocio
        ArgumentCaptor<ClientePatioDto> clientePatioCaptor = ArgumentCaptor.forClass(ClientePatioDto.class);
        Mockito.verify(clientePatioService, Mockito.times(1)).crearClientePatio(clientePatioCaptor.capture());
        assertEquals(clientePatioCaptor.getValue().getCliente().getId(), clientePatioDto.getCliente().getId());
        assertEquals(clientePatioCaptor.getValue().getPatio().getId(), clientePatioDto.getPatio().getId());
    } 
   
   @Test
    void crear_cliente_patio_retorna_400() throws Exception {
	   clientePatioDto.setFechaAsignacion(null);
        mockMvc.perform(MockMvcRequestBuilders.post("/clientePatio/crear")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(clientePatioDto))
                )
                .andExpect(status().isBadRequest());
    } 
   
   @Test
    void consultar_cliente_patio_por_id_retorna_200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/clientePatio/buscar?id=1")
                        .contentType("application/json")
                )
                .andExpect(status().isOk());

        //se verifica la llamada a la logica de negocio
        Mockito.verify(clientePatioService, Mockito.times(1)).consultarClientePatioPorId(Integer.valueOf(1));
    } 
   
   @Test
    void consultar_cliente_patio_por_id_retorna_400() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/clientePatio/buscar?id=")
                        .contentType("application/json")
                )
                .andExpect(status().isBadRequest());
    } 
   
   @Test
    void actualizar_cliente_patio_retorna_200() throws Exception {
	    clientePatioDto.setId(1);
        mockMvc.perform(MockMvcRequestBuilders.put("/clientePatio/actualizar")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(clientePatioDto))
                )
                .andExpect(status().isOk());

        //se verifica la llamada a la logica de negocio
        ArgumentCaptor<ClientePatioDto> clientePatioCaptor = ArgumentCaptor.forClass(ClientePatioDto.class);
        Mockito.verify(clientePatioService, Mockito.times(1)).editarClientePatio(clientePatioCaptor.capture());
        assertEquals(clientePatioCaptor.getValue().getCliente().getId(), clientePatioDto.getCliente().getId());
        assertEquals(clientePatioCaptor.getValue().getPatio().getId(), clientePatioDto.getPatio().getId());
    } 
   
   @Test
    void actualizar_cliente_patio_retorna_400() throws Exception {
	   clientePatioDto.setFechaAsignacion(null);
        mockMvc.perform(MockMvcRequestBuilders.put("/clientePatio/actualizar")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(clientePatioDto))
                )
                .andExpect(status().isBadRequest());
    } 
   
   
   @Test
    void eliminar_cliente_patio_por_marca_retorna_200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/clientePatio/eliminar?id=1")
                        .contentType("application/json")
                )
                .andExpect(status().isOk());

        //se verifica la llamada a la logica de negocio
        Mockito.verify(clientePatioService, Mockito.times(1)).eliminarClientePatio(Integer.valueOf(1));
    } 
   
   @Test
    void eliminar_cliente_patio_por_marca_retorna_400() throws Exception {
	   clientePatioDto.setFechaAsignacion(null);
        mockMvc.perform(MockMvcRequestBuilders.delete("/clientePatio/eliminar?id=")
                        .contentType("application/json")
                )
                .andExpect(status().isBadRequest());
    } 

}
