package com.personclientaccountmovement.comp.communication.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.personclientaccountmovement.comp.cons.CompPersonClientAccountMovementConstants;
import com.personclientaccountmovement.exception.GenericException;
import com.personclientaccountmovement.integration.dto.response.GenericResponseDTO;
import com.personclientaccountmovement.integration.interfaces.IGenericProducer;

/**
* Pruebas de capa web (MockMvc) sobre endpoints REST del composite.
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@WebMvcTest(controllers = { PersonClientController.class, MovementController.class, AccountController.class,
		ReportesRestController.class })
@Import(GlobalExceptionHandler.class)
@ActiveProfiles("test")
class CompositeControllerWebMvcTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BeanFactory beanFactory;

	@MockBean
	private IGenericProducer genericProducer;

	@BeforeEach
	void wireProducerFromBeanFactory() {
		when(beanFactory.getBean(eq(CompPersonClientAccountMovementConstants.PERSON_CLIENT_ACCOUNT_MOVEMENT_HANDLER),
				eq(IGenericProducer.class))).thenReturn(genericProducer);
	}

	//Persona-Cliente
	@Test
	void saveInfoPersona_conPayloadValido_devuelve200() throws Exception {
		mockMvc.perform(post("/clientes/saveInfoPersona").contentType(MediaType.APPLICATION_JSON)
				.content("{\"userRequest\":\"tester\",\"payload\":{\"nombre\":\"Juan\",\"apellido\":\"Perez\",\"genero\":\"Masculino\",\"edad\":30,\"identificacion\":\"1234567890\",\"direccion\":\"Calle 123\",\"telefono\":\"123456789\",\"contrasena\":\"123456\"}}"))
				.andExpect(status().isOk()).andExpect(jsonPath("$.status").value("OK"));
	}

	@Test
	void updateInfoPersona_conPayloadValido_devuelve200() throws Exception {
		mockMvc.perform(put("/clientes/updateInfoPersona").contentType(MediaType.APPLICATION_JSON)
				.content("{\"userRequest\":\"tester\",\"payload\":{\"idPersona\":1,\"idCliente\":1,\"nombre\":\"Juan\",\"apellido\":\"Perez\",\"genero\":\"Masculino\",\"edad\":30,\"identificacion\":\"1234567890\",\"direccion\":\"Calle 123\",\"telefono\":\"123456789\",\"contrasena\":\"123456\"}}"))
				.andExpect(status().isOk()).andExpect(jsonPath("$.status").value("OK"));
	}

	@Test
	void deleteInfoPersona_conPayloadValido_devuelve200() throws Exception {
		when(genericProducer.process(any())).thenReturn(GenericResponseDTO.success(true));
		mockMvc.perform(delete("/clientes/deleteInfoCliente/1").param("userRequest", "tester"))
				.andExpect(status().isOk()).andExpect(jsonPath("$.status").value("OK"));
	}

	@Test
	void pageListByInfoCliente_conPayloadValido_devuelve200() throws Exception {
		when(genericProducer.process(any())).thenReturn(GenericResponseDTO.success(java.util.Map.of("id", 1L)));
		mockMvc.perform(post("/clientes/pageListByInfoCliente").contentType(MediaType.APPLICATION_JSON)
				.content("{\"userRequest\":\"tester\",\"payload\":{\"page\":0,\"size\":10,\"tabla\":{\"valor\":\"jose\"}}}"))
				.andExpect(status().isOk()).andExpect(jsonPath("$.status").value("OK"));
	}

	@Test
	void updateContrasenia_conPayloadValido_devuelve200() throws Exception {
		when(genericProducer.process(any())).thenReturn(GenericResponseDTO.success(java.util.Map.of("id", 1L)));
		mockMvc.perform(patch("/clientes/updateContrasenia/1").contentType(MediaType.APPLICATION_JSON)
				.content("{\"userRequest\":\"tester\",\"payload\":{\"contrasena\":\"123456\"}}"))
				.andExpect(status().isOk()).andExpect(jsonPath("$.status").value("OK"));
	}

	//Cuenta
	@Test
	void saveInfoCuenta_conPayloadValido_devuelve200() throws Exception {
		when(genericProducer.process(any())).thenReturn(GenericResponseDTO.success(java.util.Map.of("id", 1L)));
		mockMvc.perform(post("/cuentas/saveInfoCuenta").contentType(MediaType.APPLICATION_JSON)
				.content("{\"userRequest\":\"tester\",\"payload\":{\"numeroCuenta\":\"4710000\",\"saldoInicial\":1000000,\"tipoCuenta\":\"Ahorro\"}}"))
				.andExpect(status().isOk()).andExpect(jsonPath("$.status").value("OK"));
	}
	
	@Test
	void updateInfoCuenta_conPayloadValido_devuelve200() throws Exception {
		when(genericProducer.process(any())).thenReturn(GenericResponseDTO.success(java.util.Map.of("id", 1L)));
		mockMvc.perform(put("/cuentas/updateInfoCuenta").contentType(MediaType.APPLICATION_JSON)
				.content("{\"userRequest\":\"tester\",\"payload\":{\"idCuenta\":\"4710000\",\"numeroCuenta\":\"4710000\",\"saldoInicial\":1000000,\"tipoCuenta\":\"Ahorro\"}}"))
				.andExpect(status().isOk()).andExpect(jsonPath("$.status").value("OK"));
	}

	@Test
	void deleteInfoCuenta_conPayloadValido_devuelve200() throws Exception {
		when(genericProducer.process(any())).thenReturn(GenericResponseDTO.success(java.util.Map.of("id", 1L)));
		mockMvc.perform(delete("/cuentas/deleteInfoCuenta/1").param("userRequest", "tester"))
				.andExpect(status().isOk()).andExpect(jsonPath("$.status").value("OK"));
	}

	@Test
	void pageListByInfoCuenta_conPayloadValido_devuelve200() throws Exception {
		when(genericProducer.process(any())).thenReturn(GenericResponseDTO.success(java.util.Map.of("id", 1L)));
		mockMvc.perform(post("/cuentas/pageListByInfoCuenta").contentType(MediaType.APPLICATION_JSON)
				.content("{\"userRequest\":\"tester\",\"payload\":{\"page\":0,\"size\":10,\"tabla\":{\"valor\":\"jose\"}}}"))
				.andExpect(status().isOk()).andExpect(jsonPath("$.status").value("OK"));
	}

	//Movimiento
	@Test
	void saveInfoMovimiento_conPayloadValido_devuelve200() throws Exception {
		when(genericProducer.process(any())).thenReturn(GenericResponseDTO.success(java.util.Map.of("id", 1L)));
		mockMvc.perform(post("/movimientos/saveInfoMovimiento").contentType(MediaType.APPLICATION_JSON)
				.content("{\"userRequest\":\"tester\",\"payload\":{\"numeroCuenta\":\"4710000\",\"valor\":10}}"))
				.andExpect(status().isOk()).andExpect(jsonPath("$.status").value("OK"));
	}

	@Test
	void updateInfoMovimiento_conPayloadValido_devuelve200() throws Exception {
		when(genericProducer.process(any())).thenReturn(GenericResponseDTO.success(java.util.Map.of("id", 1L)));
		mockMvc.perform(put("/movimientos/updateInfoMovimiento").contentType(MediaType.APPLICATION_JSON)
				.content("{\"userRequest\":\"tester\",\"payload\":{\"idMovimiento\":1,\"numeroCuenta\":\"4710000\",\"valor\":10}}"))
				.andExpect(status().isOk()).andExpect(jsonPath("$.status").value("OK"));
	}	

	void retrieveInfoMovimiento_conPayloadValido_devuelve200() throws Exception {
		when(genericProducer.process(any())).thenReturn(GenericResponseDTO.success(java.util.Map.of("id", 1L)));
		mockMvc.perform(get("/movimientos/retrieveInfoMovimiento/1").param("userRequest", "tester"))
				.andExpect(status().isOk()).andExpect(jsonPath("$.status").value("OK"));
	}

	@Test
	void deleteInfoMovimiento_conPayloadValido_devuelve200() throws Exception {
		when(genericProducer.process(any())).thenReturn(GenericResponseDTO.success(java.util.Map.of("id", 1L)));
		mockMvc.perform(delete("/movimientos/deleteInfoMovimiento/1").param("userRequest", "tester"))
				.andExpect(status().isOk()).andExpect(jsonPath("$.status").value("OK"));
	}

	@Test
	void pageListByInfoMovimiento_conPayloadValido_devuelve200() throws Exception {
		when(genericProducer.process(any())).thenReturn(GenericResponseDTO.success(java.util.Map.of("id", 1L)));
		mockMvc.perform(post("/movimientos/pageListByInfoMovimiento").contentType(MediaType.APPLICATION_JSON)
				.content("{\"userRequest\":\"tester\",\"payload\":{\"page\":0,\"size\":10,\"tabla\":{\"valor\":\"jose\"}}}"))
				.andExpect(status().isOk()).andExpect(jsonPath("$.status").value("OK"));
	}

	//Reportes
	@Test
	void pageListByInfoMovimientoReporte_conPayloadValido_devuelve200() throws Exception {
		when(genericProducer.process(any())).thenReturn(GenericResponseDTO.success(java.util.Map.of("id", 1L)));
		MvcResult result = mockMvc
				.perform(get("/reportes/page").param("fechaDesde", "2026-01-01").param("fechaHasta", "2026-01-31")
						.param("identificacion", "1234567890").param("page", "0").param("size", "10"))
				.andExpect(request().asyncStarted()).andReturn();
		mockMvc.perform(asyncDispatch(result)).andExpect(status().isOk()).andExpect(jsonPath("$.status").value("OK"));
	}

	@Test
	void pageListByInfoMovimientoReportePdf_conPayloadValido_devuelve200() throws Exception {
		when(genericProducer.process(any())).thenReturn(GenericResponseDTO.success(java.util.Map.of("id", 1L)));
		MvcResult result = mockMvc
				.perform(get("/reportes/pagePdf").param("fechaDesde", "2026-01-01").param("fechaHasta", "2026-01-31")
						.param("identificacion", "1234567890").param("page", "0").param("size", "10"))
				.andExpect(request().asyncStarted()).andReturn();
		mockMvc.perform(asyncDispatch(result)).andExpect(status().isOk()).andExpect(jsonPath("$.status").value("OK"));
	}
	
}
