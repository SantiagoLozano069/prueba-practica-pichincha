package com.pichincha.camsacreditoauto.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigInteger;
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
import com.pichincha.camsacreditoauto.domain.enums.EstadoSolicitudCreditoEnum;
import com.pichincha.camsacreditoauto.service.CreditoService;
import com.pichincha.camsacreditoauto.service.PatioService;
import com.pichincha.camsacreditoauto.service.dto.ClienteDto;
import com.pichincha.camsacreditoauto.service.dto.CreditoDto;
import com.pichincha.camsacreditoauto.service.dto.EjecutivoDto;
import com.pichincha.camsacreditoauto.service.dto.PatioDto;
import com.pichincha.camsacreditoauto.service.dto.VehiculoDto;

@RunWith(SpringRunner.class)
@WebMvcTest(CreditoController.class)
public class CreditoControllerTest {

	@Autowired
    private MockMvc mockMvc;
 	
 	@Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CreditoService creditoService;

    
    private CreditoDto creditoDto;

    @BeforeEach
    void init() {
    	ClienteDto clienteDto = new ClienteDto();
		clienteDto.setId(1);

		PatioDto patioDto = new PatioDto();
		patioDto.setId(1);

		EjecutivoDto ejecutivoDto = new EjecutivoDto();
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
    }

   @Test
    void crear_credito_retorna_200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/solicitudCredito/crear")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(creditoDto))
                )
                .andExpect(status().isOk());

        //se verifica la llamada a la logica de negocio
        ArgumentCaptor<CreditoDto> creditoCaptor = ArgumentCaptor.forClass(CreditoDto.class);
        Mockito.verify(creditoService, Mockito.times(1)).crearCredito(creditoCaptor.capture());
        assertEquals(creditoCaptor.getValue().getFechaElaboracion(), creditoDto.getFechaElaboracion());
        assertEquals(creditoCaptor.getValue().getPatio().getId(), creditoDto.getPatio().getId());
    } 
   
   @Test
    void crear_patio_retorna_400() throws Exception {
	   creditoDto.setCliente(null);
        mockMvc.perform(MockMvcRequestBuilders.post("/solicitudCredito/crear")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(creditoDto))
                )
                .andExpect(status().isBadRequest());
    } 
}
