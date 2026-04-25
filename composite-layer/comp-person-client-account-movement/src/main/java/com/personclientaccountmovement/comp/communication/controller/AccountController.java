package com.personclientaccountmovement.comp.communication.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
import com.personclientaccountmovement.dto.AdmiTipoCuentaReqDTO;
import com.personclientaccountmovement.dto.BusquedaGlobalDTO;
import com.personclientaccountmovement.dto.InfoCuentaPersonaUnifiedReqDTO;
import com.personclientaccountmovement.dto.ObjectIdDTO;
import com.personclientaccountmovement.dto.PageDTO;
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
* Controlador REST de cuentas y tipos de cuenta.
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@CrossOrigin
@Validated
@Tag(name = "AccountController", description = "Cuentas y tipos de cuenta (REST + DTO)")
@RestController
@RequestMapping("cuentas")
public class AccountController {

	Logger log = LogManager.getLogger(this.getClass());

	@Autowired
	private BeanFactory beanFactory;

	@Autowired
	private IGenericProducer iGenericProducer;

	// --- InfoCuenta ---

	/**
	 * Expone el endpoint REST saveInfoCuenta.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@PostMapping(value = "/saveInfoCuenta")
	public GenericResponseDTO<Object> saveInfoCuenta(
			@Valid @RequestBody GenericRequestDTO<InfoCuentaPersonaUnifiedReqDTO> request) throws GenericException {
				log.info("Metodo controller saveInfoCuenta iniciado");
		iGenericProducer = beanFactory.getBean(CompPersonClientAccountMovementConstants.PERSON_CLIENT_ACCOUNT_MOVEMENT_HANDLER,
				IGenericProducer.class);
		InfoCuentaPersonaUnifiedReqDTO payload = request.getPayload();
		payload.setPersonaRequest(request.getUserRequest());
		GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
				.userRequest(request.getUserRequest())
				.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_SAVE_INFO_CUENTA_METHOD)
				.payload(payload)
				.type(EnumRequestType.SYNC)
				.transactionId(UUID.randomUUID().toString())
				.build();
		return GenericResponseDTO
				.success(GenericResponseUnwrapUtil.unwrap(iGenericProducer.process(producerModel)));
	}

	/**
	 * Expone el endpoint REST updateInfoCuenta.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@PutMapping(value = "/updateInfoCuenta")
	public GenericResponseDTO<Object> updateInfoCuenta(
			@Valid @RequestBody GenericRequestDTO<InfoCuentaPersonaUnifiedReqDTO> request) throws GenericException {
				log.info("Metodo controller updateInfoCuenta iniciado");
		iGenericProducer = beanFactory.getBean(CompPersonClientAccountMovementConstants.PERSON_CLIENT_ACCOUNT_MOVEMENT_HANDLER,
				IGenericProducer.class);
		InfoCuentaPersonaUnifiedReqDTO payload = request.getPayload();
		payload.setPersonaRequest(request.getUserRequest());
		GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
				.userRequest(request.getUserRequest())
				.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_UPDATE_INFO_CUENTA_METHOD)
				.payload(payload)
				.type(EnumRequestType.SYNC)
				.transactionId(UUID.randomUUID().toString())
				.build();
		return GenericResponseDTO
				.success(GenericResponseUnwrapUtil.unwrap(iGenericProducer.process(producerModel)));
	}

	/**
	 * Expone el endpoint REST pageListByInfoCuenta.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@PostMapping(value = "/pageListByInfoCuenta")
	public GenericResponseDTO<Object> pageListByInfoCuenta(
			@RequestBody GenericRequestDTO<PageDTO<BusquedaGlobalDTO>> request) throws GenericException {
				log.info("Metodo controller pageListByInfoCuenta iniciado");
		iGenericProducer = beanFactory.getBean(CompPersonClientAccountMovementConstants.PERSON_CLIENT_ACCOUNT_MOVEMENT_HANDLER,
				IGenericProducer.class);
		GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
				.userRequest(request.getUserRequest())
				.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_PAGE_LIST_BY_INFO_CUENTA_METHOD)
				.payload(request.getPayload())
				.type(EnumRequestType.SYNC)
				.transactionId(UUID.randomUUID().toString())
				.build();
		return GenericResponseDTO
				.success(GenericResponseUnwrapUtil.unwrap(iGenericProducer.process(producerModel)));
	}

	/**
	 * Expone el endpoint REST retrieveInfoCuenta.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@GetMapping("/retrieveInfoCuenta/{idCuenta}")
	public GenericResponseDTO<Object> retrieveInfoCuenta(@PathVariable Long idCuenta) throws GenericException {
		log.info("Metodo controller retrieveInfoCuenta iniciado");
		InfoCuentaPersonaUnifiedReqDTO payload = InfoCuentaPersonaUnifiedReqDTO.builder().idCuenta(idCuenta).build();
		Object raw = processAccountMovementUnwrapped(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_LIST_BY_INFO_CUENTA_METHOD,
				payload);
		if (raw instanceof List<?> l && l.size() == 1) {
			return GenericResponseDTO.success(l.get(0));
		}
		throw new GenericException("Cuenta no encontrada", PersonClientAccountMovementConfigConstants.MISSING_VALUES);
	}

	/**
	 * Expone el endpoint REST deleteInfoCuenta.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@DeleteMapping("/deleteInfoCuenta/{idCuenta}")
	public GenericResponseDTO<Object> deleteInfoCuenta(@PathVariable Long idCuenta) throws GenericException {
		log.info("Metodo controller deleteInfoCuenta iniciado");
		ObjectIdDTO payload = ObjectIdDTO.builder().objectId(idCuenta).build();
		iGenericProducer = beanFactory.getBean(CompPersonClientAccountMovementConstants.PERSON_CLIENT_ACCOUNT_MOVEMENT_HANDLER,
				IGenericProducer.class);
		GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
				.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_ELIMINADO_INFO_CUENTA_METHOD)
				.payload(payload)
				.type(EnumRequestType.SYNC)
				.transactionId(UUID.randomUUID().toString())
				.build();
		return GenericResponseDTO
				.success(GenericResponseUnwrapUtil.unwrap(iGenericProducer.process(producerModel)));
	}

	/**
	 * Expone el endpoint REST listByInfoCuenta.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@GetMapping(value = "/listByInfoCuenta")
	public GenericResponseDTO<Object> listByInfoCuenta(@RequestParam(required = false) Long idCuenta,
			@RequestParam(required = false) String numeroCuenta,
			@RequestParam(required = false) Long personaId,
			@RequestParam(required = false) Long tipoCuentaId,
			@RequestParam(required = false) String nombreTipoCuenta,
			@RequestParam(required = false) String estado) throws GenericException {
				log.info("Metodo controller RequestParam iniciado");
		InfoCuentaPersonaUnifiedReqDTO payload = InfoCuentaPersonaUnifiedReqDTO.builder()
				.idCuenta(idCuenta)
				.numeroCuenta(numeroCuenta)
				.personaId(personaId)
				.tipoCuentaId(tipoCuentaId)
				.nombreTipoCuenta(nombreTipoCuenta)
				.estado(estado)
				.build();
		iGenericProducer = beanFactory.getBean(CompPersonClientAccountMovementConstants.PERSON_CLIENT_ACCOUNT_MOVEMENT_HANDLER,
				IGenericProducer.class);
		GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
				.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_LIST_BY_INFO_CUENTA_METHOD)
				.payload(payload)
				.type(EnumRequestType.SYNC)
				.transactionId(UUID.randomUUID().toString())
				.build();
		return GenericResponseDTO
				.success(GenericResponseUnwrapUtil.unwrap(iGenericProducer.process(producerModel)));
	}

	// --- AdmiTipoCuenta ---

	/**
	 * Expone el endpoint REST saveAdmiTipoCuenta.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@PostMapping(value = "/saveAdmiTipoCuenta")
	public GenericResponseDTO<Object> saveAdmiTipoCuenta(
			@RequestBody GenericRequestDTO<AdmiTipoCuentaReqDTO> request) throws GenericException {
				log.info("Metodo controller saveAdmiTipoCuenta iniciado");
		iGenericProducer = beanFactory.getBean(CompPersonClientAccountMovementConstants.PERSON_CLIENT_ACCOUNT_MOVEMENT_HANDLER,
				IGenericProducer.class);
		GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
				.userRequest(request.getUserRequest())
				.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_SAVE_ADMI_TIPO_CUENTA_METHOD)
				.payload(request.getPayload())
				.type(EnumRequestType.SYNC)
				.transactionId(UUID.randomUUID().toString())
				.build();
		return GenericResponseDTO
				.success(GenericResponseUnwrapUtil.unwrap(iGenericProducer.process(producerModel)));
	}

	/**
	 * Expone el endpoint REST updateAdmiTipoCuenta.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@PutMapping(value = "/updateAdmiTipoCuenta")
	public GenericResponseDTO<Object> updateAdmiTipoCuenta(
			@RequestBody GenericRequestDTO<AdmiTipoCuentaReqDTO> request) throws GenericException {
				log.info("Metodo controller updateAdmiTipoCuenta iniciado");
		iGenericProducer = beanFactory.getBean(CompPersonClientAccountMovementConstants.PERSON_CLIENT_ACCOUNT_MOVEMENT_HANDLER,
				IGenericProducer.class);
		GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
				.userRequest(request.getUserRequest())
				.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_UPDATE_ADMI_TIPO_CUENTA_METHOD)
				.payload(request.getPayload())
				.type(EnumRequestType.SYNC)
				.transactionId(UUID.randomUUID().toString())
				.build();
		return GenericResponseDTO
				.success(GenericResponseUnwrapUtil.unwrap(iGenericProducer.process(producerModel)));
	}

	/**
	 * Expone el endpoint REST pageListByAdmiTipoCuenta.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@PostMapping(value = "/pageListByAdmiTipoCuenta")
	public GenericResponseDTO<Object> pageListByAdmiTipoCuenta(
			@RequestBody GenericRequestDTO<PageDTO<AdmiTipoCuentaReqDTO>> request) throws GenericException {
				log.info("Metodo controller pageListByAdmiTipoCuenta iniciado");
		iGenericProducer = beanFactory.getBean(CompPersonClientAccountMovementConstants.PERSON_CLIENT_ACCOUNT_MOVEMENT_HANDLER,
				IGenericProducer.class);
		GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
				.userRequest(request.getUserRequest())
				.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_PAGE_LIST_BY_ADMI_TIPO_CUENTA_METHOD)
				.payload(request.getPayload())
				.type(EnumRequestType.SYNC)
				.transactionId(UUID.randomUUID().toString())
				.build();
		return GenericResponseDTO
				.success(GenericResponseUnwrapUtil.unwrap(iGenericProducer.process(producerModel)));
	}

	/**
	 * Expone el endpoint REST retrieveAdmiTipoCuenta.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@GetMapping("/retrieveAdmiTipoCuenta/{idTipoCuenta}")
	public GenericResponseDTO<Object> retrieveAdmiTipoCuenta(@PathVariable Long idTipoCuenta)
			throws GenericException {
				log.info("Metodo controller retrieveAdmiTipoCuenta iniciado");
		AdmiTipoCuentaReqDTO payload = AdmiTipoCuentaReqDTO.builder().idTipoCuenta(idTipoCuenta).build();
		Object raw = processAccountMovementUnwrapped(
				PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_LIST_BY_ADMI_TIPO_CUENTA_METHOD, payload);
		if (raw instanceof List<?> l && l.size() == 1) {
			return GenericResponseDTO.success(l.get(0));
		}
		throw new GenericException("Tipo de cuenta no encontrado", PersonClientAccountMovementConfigConstants.MISSING_VALUES);
	}

	/**
	 * Expone el endpoint REST deleteAdmiTipoCuenta.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@DeleteMapping("/deleteAdmiTipoCuenta/{idTipoCuenta}")
	public GenericResponseDTO<Object> deleteAdmiTipoCuenta(@PathVariable Long idTipoCuenta)
			throws GenericException {
				log.info("Metodo controller deleteAdmiTipoCuenta iniciado");
		ObjectIdDTO payload = ObjectIdDTO.builder().objectId(idTipoCuenta).build();
		iGenericProducer = beanFactory.getBean(CompPersonClientAccountMovementConstants.PERSON_CLIENT_ACCOUNT_MOVEMENT_HANDLER,
				IGenericProducer.class);
		GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
				.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_ELIMINADO_ADMI_TIPO_CUENTA_METHOD)
				.payload(payload)
				.type(EnumRequestType.SYNC)
				.transactionId(UUID.randomUUID().toString())
				.build();
		return GenericResponseDTO
				.success(GenericResponseUnwrapUtil.unwrap(iGenericProducer.process(producerModel)));
	}

	/**
	 * Expone el endpoint REST listByAdmiTipoCuenta.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@GetMapping(value = "/listByAdmiTipoCuenta")
	public GenericResponseDTO<Object> listByAdmiTipoCuenta(
			@RequestParam(required = false) Long idTipoCuenta,
			@RequestParam(required = false) String nombreCuenta,
			@RequestParam(required = false) String descripcion,
			@RequestParam(required = false) String estado) throws GenericException {
				log.info("Metodo controller RequestParam iniciado");
		AdmiTipoCuentaReqDTO payload = AdmiTipoCuentaReqDTO.builder()
				.idTipoCuenta(idTipoCuenta)
				.nombreCuenta(nombreCuenta)
				.descripcion(descripcion)
				.estado(estado)
				.build();
		iGenericProducer = beanFactory.getBean(CompPersonClientAccountMovementConstants.PERSON_CLIENT_ACCOUNT_MOVEMENT_HANDLER,
				IGenericProducer.class);
		GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
				.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_LIST_BY_ADMI_TIPO_CUENTA_METHOD)
				.payload(payload)
				.type(EnumRequestType.SYNC)
				.transactionId(UUID.randomUUID().toString())
				.build();
		return GenericResponseDTO
				.success(GenericResponseUnwrapUtil.unwrap(iGenericProducer.process(producerModel)));
	}

	private Object processAccountMovementUnwrapped(String option, Object payload) throws GenericException {
		log.info("Metodo controller processAccountMovementUnwrapped iniciado");
		iGenericProducer = beanFactory.getBean(CompPersonClientAccountMovementConstants.PERSON_CLIENT_ACCOUNT_MOVEMENT_HANDLER,
				IGenericProducer.class);
		GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
				.option(option)
				.payload(payload)
				.type(EnumRequestType.SYNC)
				.transactionId(UUID.randomUUID().toString())
				.build();
		return GenericResponseUnwrapUtil.unwrap(iGenericProducer.process(producerModel));
	}
}
