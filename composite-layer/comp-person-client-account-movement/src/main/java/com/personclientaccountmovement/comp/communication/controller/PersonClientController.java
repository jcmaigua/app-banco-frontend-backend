package com.personclientaccountmovement.comp.communication.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

import com.personclientaccountmovement.comp.communication.support.GenericResponseUnwrapUtil;
import com.personclientaccountmovement.comp.cons.CompPersonClientAccountMovementConstants;
import com.personclientaccountmovement.cons.PersonClientAccountMovementConfigConstants;
import com.personclientaccountmovement.dto.BusquedaGlobalDTO;
import com.personclientaccountmovement.dto.InfoClienteReqDTO;
import com.personclientaccountmovement.dto.InfoClienteResDTO;
import com.personclientaccountmovement.dto.InfoPersonaReqDTO;
import com.personclientaccountmovement.dto.InfoPersonaResDTO;
import com.personclientaccountmovement.dto.ObjectIdDTO;
import com.personclientaccountmovement.dto.PageDTO;
import com.personclientaccountmovement.dto.PersonaClienteUnifiedReqDTO;
import com.personclientaccountmovement.dto.PersonaClienteUnifiedResDTO;
import com.personclientaccountmovement.exception.GenericException;
import com.personclientaccountmovement.integration.dto.request.GenericRequestDTO;
import com.personclientaccountmovement.integration.dto.response.GenericResponseDTO;
import com.personclientaccountmovement.integration.enums.EnumRequestType;
import com.personclientaccountmovement.integration.interfaces.IGenericProducer;
import com.personclientaccountmovement.integration.model.GenericProducerModel;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
* Controlador REST de clientes y personas.
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@CrossOrigin
@Validated
@Tag(name = "PersonClientController", description = "Clientes y personas (REST + DTO)")
@RestController
@RequestMapping("clientes")
public class PersonClientController {

	private static final String USER_DEFAULT = "REST";

	@Autowired
	private BeanFactory beanFactory;

	@Autowired
	private IGenericProducer iGenericProducer;

	Logger log = LogManager.getLogger(this.getClass());

	/**
	 * Expone el endpoint REST saveInfoPersona.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@PostMapping(value = "/saveInfoPersona")
	public GenericResponseDTO<Object> saveInfoPersona(
			@Valid @RequestBody GenericRequestDTO<PersonaClienteUnifiedReqDTO> request) throws GenericException {
		log.info("Metodo controller saveInfoPersona iniciado");
		iGenericProducer = beanFactory.getBean(CompPersonClientAccountMovementConstants.PERSON_CLIENT_ACCOUNT_MOVEMENT_HANDLER,
				IGenericProducer.class);
		PersonaClienteUnifiedReqDTO payload = request.getPayload();
		payload.setUserRequest(request.getUserRequest());
		GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
				.userRequest(request.getUserRequest())
				.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_SAVE_INFO_PERSONA_METHOD)
				.payload(payload)
				.type(EnumRequestType.SYNC)
				.transactionId(UUID.randomUUID().toString())
				.build();
		return GenericResponseDTO
				.success(GenericResponseUnwrapUtil.unwrap(iGenericProducer.process(producerModel)));
	}

	/**
	 * Expone el endpoint REST actualizarPersonaCliente.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@PutMapping(value = "/updateInfoPersona")
	public GenericResponseDTO<Object> actualizarPersonaCliente(
			@Valid @RequestBody GenericRequestDTO<PersonaClienteUnifiedReqDTO> request) throws GenericException {
		log.info("Metodo controller actualizarPersonaCliente iniciado");
		iGenericProducer = beanFactory.getBean(CompPersonClientAccountMovementConstants.PERSON_CLIENT_ACCOUNT_MOVEMENT_HANDLER,
				IGenericProducer.class);
		PersonaClienteUnifiedReqDTO payload = request.getPayload();
		payload.setUserRequest(request.getUserRequest());
		GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
				.userRequest(request.getUserRequest())
				.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_UPDATE_INFO_PERSONA_METHOD)
				.payload(payload)
				.type(EnumRequestType.SYNC)
				.transactionId(UUID.randomUUID().toString())
				.build();
		return GenericResponseDTO
				.success(GenericResponseUnwrapUtil.unwrap(iGenericProducer.process(producerModel)));
	}

	/**
	 * Expone el endpoint REST pageListByInfoCliente.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@PostMapping(value = "/pageListByInfoCliente")
	public GenericResponseDTO<Object> pageListByInfoCliente(
			@Valid @RequestBody GenericRequestDTO<PageDTO<BusquedaGlobalDTO>> request) throws GenericException {
		log.info("Metodo controller pageListByInfoCliente iniciado");
		iGenericProducer = beanFactory.getBean(CompPersonClientAccountMovementConstants.PERSON_CLIENT_ACCOUNT_MOVEMENT_HANDLER,
				IGenericProducer.class);
		GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
				.userRequest(request.getUserRequest())
				.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_PAGE_LIST_BY_INFO_CLIENTE_METHOD)
				.payload(request.getPayload())
				.type(EnumRequestType.SYNC)
				.transactionId(UUID.randomUUID().toString())
				.build();
		return GenericResponseDTO
				.success(GenericResponseUnwrapUtil.unwrap(iGenericProducer.process(producerModel)));
	}



	/**
	 * Expone el endpoint REST retrieveInfoCliente.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
    @GetMapping("/retrieveInfoCliente/{idCliente}")
	public GenericResponseDTO<Object> retrieveInfoCliente(@PathVariable Long idCliente,
			@RequestParam(required = false) String userRequest) throws GenericException {
		log.info("Metodo controller retrieveInfoCliente iniciado");
		ObjectIdDTO payload = ObjectIdDTO.builder().objectId(idCliente).build();
		iGenericProducer = beanFactory.getBean(CompPersonClientAccountMovementConstants.PERSON_CLIENT_ACCOUNT_MOVEMENT_HANDLER,
				IGenericProducer.class);
		GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
				.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_RETRIEVE_INFO_CLIENTE_METHOD)
				.payload(payload)
				.type(EnumRequestType.SYNC)
				.transactionId(UUID.randomUUID().toString())
				.build();
		return GenericResponseDTO
				.success(GenericResponseUnwrapUtil.unwrap(iGenericProducer.process(producerModel)));

	}

	/**
	 * Expone el endpoint REST updateContrasenia.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@PatchMapping("/updateContrasenia/{idCliente}")
	public GenericResponseDTO<Object> updateContrasenia(@PathVariable Long idCliente,
			@Valid @RequestBody GenericRequestDTO<InfoClienteReqDTO> request) throws GenericException {
		log.info("Metodo controller updateContrasenia iniciado");
		iGenericProducer = beanFactory.getBean(CompPersonClientAccountMovementConstants.PERSON_CLIENT_ACCOUNT_MOVEMENT_HANDLER,
				IGenericProducer.class);
		InfoClienteReqDTO payload = request.getPayload();
		payload.setPersonaModificacion(request.getUserRequest());
		payload.setIdCliente(idCliente);
		GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
				.userRequest(request.getUserRequest())
				.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_UPDATE_INFO_CLIENTE_METHOD)
				.payload(payload)
				.type(EnumRequestType.SYNC)
				.transactionId(UUID.randomUUID().toString())
				.build();
		return GenericResponseDTO
				.success(GenericResponseUnwrapUtil.unwrap(iGenericProducer.process(producerModel)));
		
	}

	/**
	 * Expone el endpoint REST retrieveInfoPersona.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@GetMapping("/retrieveInfoPersona/{idPersona}")
	public GenericResponseDTO<Object> retrieveInfoPersona(@PathVariable Long idPersona) throws GenericException {
		log.info("Metodo controller retrieveInfoPersona iniciado");
		ObjectIdDTO payload = ObjectIdDTO.builder().objectId(idPersona).build();
		iGenericProducer = beanFactory.getBean(CompPersonClientAccountMovementConstants.PERSON_CLIENT_ACCOUNT_MOVEMENT_HANDLER,
				IGenericProducer.class);
		GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
				.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_RETRIEVE_INFO_PERSONA_METHOD)
				.payload(payload)
				.type(EnumRequestType.SYNC)
				.transactionId(UUID.randomUUID().toString())
				.build();
		return GenericResponseDTO
				.success(GenericResponseUnwrapUtil.unwrap(iGenericProducer.process(producerModel)));
	}

	/**
	 * Expone el endpoint REST deleteInfoCliente.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@DeleteMapping("/deleteInfoCliente/{idCliente}")
	public GenericResponseDTO<Object> deleteInfoCliente(@PathVariable Long idCliente,
			@RequestParam(required = false) String userRequest) throws GenericException {
		log.info("Metodo controller deleteInfoCliente iniciado");
		ObjectIdDTO payload = ObjectIdDTO.builder().objectId(idCliente).build();
		//Validar cuentas de cliente
		iGenericProducer = beanFactory.getBean(CompPersonClientAccountMovementConstants.PERSON_CLIENT_ACCOUNT_MOVEMENT_HANDLER,
				IGenericProducer.class);
		GenericProducerModel<Object> producerModelValidaCuenta = GenericProducerModel.<Object>builder()
				.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_VALIDAR_INFO_CUENTA_BY_USUARIO_METHOD)
				.payload(payload)
				.type(EnumRequestType.SYNC)
				.transactionId(UUID.randomUUID().toString())
				.build();
	    GenericResponseDTO.success(GenericResponseUnwrapUtil.unwrap(iGenericProducer.process(producerModelValidaCuenta)));

		//Elimina Cliente
		iGenericProducer = beanFactory.getBean(CompPersonClientAccountMovementConstants.PERSON_CLIENT_ACCOUNT_MOVEMENT_HANDLER,
				IGenericProducer.class);
		GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
				.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_ELIMINADO_INFO_PERSONA_METHOD)
				.payload(payload)
				.type(EnumRequestType.SYNC)
				.transactionId(UUID.randomUUID().toString())
				.build();
		return GenericResponseDTO
				.success(GenericResponseUnwrapUtil.unwrap(iGenericProducer.process(producerModel)));

	}

	/**
	 * Expone el endpoint REST listByInfoCliente.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@GetMapping(value = "/listByInfoCliente")
	public GenericResponseDTO<Object> listByInfoCliente(
			@RequestParam(required = false) Long idPersona,
			@RequestParam(required = false) Long idCliente,
			@RequestParam(required = false) String nombre,
			@RequestParam(required = false) String apellido,
			@RequestParam(required = false) String identificacion,
			@RequestParam(required = false) String direccion,
			@RequestParam(required = false) String telefono,
			@RequestParam(required = false) String estado) throws GenericException {
		log.info("Metodo controller listByInfoCliente iniciado");
		PersonaClienteUnifiedReqDTO payload = PersonaClienteUnifiedReqDTO.builder()
				.idPersona(idPersona)
				.idCliente(idCliente)
				.nombre(nombre)
				.apellido(apellido)
				.identificacion(identificacion)
				.direccion(direccion)
				.telefono(telefono)
				.estado(estado)
				.build();
		
		iGenericProducer = beanFactory.getBean(CompPersonClientAccountMovementConstants.PERSON_CLIENT_ACCOUNT_MOVEMENT_HANDLER,
				IGenericProducer.class);
		GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
				.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_LIST_BY_INFO_CLIENTE_METHOD)
				.payload(payload)
				.type(EnumRequestType.SYNC)
				.transactionId(UUID.randomUUID().toString())
				.build();
		return GenericResponseDTO
				.success(GenericResponseUnwrapUtil.unwrap(iGenericProducer.process(producerModel)));

	}


	
}
