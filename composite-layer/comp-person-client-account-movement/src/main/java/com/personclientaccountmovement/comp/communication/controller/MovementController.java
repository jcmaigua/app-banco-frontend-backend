package com.personclientaccountmovement.comp.communication.controller;

import java.math.BigDecimal;
import java.util.Date;
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
import com.personclientaccountmovement.dto.AdmiTipoMovimientoReqDTO;
import com.personclientaccountmovement.dto.BusquedaGlobalDTO;
import com.personclientaccountmovement.dto.InfoCuentaResDTO;
import com.personclientaccountmovement.dto.InfoCuentaPersonaUnifiedReqDTO;
import com.personclientaccountmovement.dto.InfoMovimientoReqDTO;
import com.personclientaccountmovement.dto.InfoMovimientoResDTO;
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
* Controlador REST de movimientos y tipos de movimiento.
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@CrossOrigin
@Validated
@Tag(name = "MovementController", description = "Movimientos y tipos de movimiento (REST + DTO)")
@RestController
@RequestMapping("movimientos")
public class MovementController {

	Logger log = LogManager.getLogger(this.getClass());

	private static final String USER_DEFAULT = "REST";

	@Autowired
	private BeanFactory beanFactory;

	@Autowired
	private IGenericProducer iGenericProducer;

	/**
	 * Expone el endpoint REST saveInfoMovimiento.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@PostMapping(value = "/saveInfoMovimiento")
	public GenericResponseDTO<Object> saveInfoMovimiento(
			@Valid @RequestBody GenericRequestDTO<InfoMovimientoReqDTO> request) throws GenericException {
				log.info("Metodo controller saveInfoMovimiento iniciado");
		iGenericProducer = beanFactory.getBean(CompPersonClientAccountMovementConstants.PERSON_CLIENT_ACCOUNT_MOVEMENT_HANDLER,
				IGenericProducer.class);
		InfoMovimientoReqDTO payload = request.getPayload();
		payload.setPersonaCreacion(request.getUserRequest());
		GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
				.userRequest(request.getUserRequest())
				.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_SAVE_INFO_MOVIMIENTO_METHOD)
				.payload(request.getPayload())
				.type(EnumRequestType.SYNC)
				.transactionId(UUID.randomUUID().toString())
				.build();
		return GenericResponseDTO
				.success(GenericResponseUnwrapUtil.unwrap(iGenericProducer.process(producerModel)));
	}

	/**
	 * Expone el endpoint REST updateInfoMovimiento.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@PutMapping(value = "/updateInfoMovimiento")
	public GenericResponseDTO<Object> updateInfoMovimiento(
			@Valid @RequestBody GenericRequestDTO<InfoMovimientoReqDTO> request) throws GenericException {
				log.info("Metodo controller updateInfoMovimiento iniciado");
		iGenericProducer = beanFactory.getBean(CompPersonClientAccountMovementConstants.PERSON_CLIENT_ACCOUNT_MOVEMENT_HANDLER,
				IGenericProducer.class);
		InfoMovimientoReqDTO payload = request.getPayload();
		payload.setPersonaCreacion(request.getUserRequest());
		GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
				.userRequest(request.getUserRequest())
				.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_UPDATE_INFO_MOVIMIENTO_METHOD)
				.payload(request.getPayload())
				.type(EnumRequestType.SYNC)
				.transactionId(UUID.randomUUID().toString())
				.build();
		return GenericResponseDTO
				.success(GenericResponseUnwrapUtil.unwrap(iGenericProducer.process(producerModel)));
	}

	/**
	 * Expone el endpoint REST pageListByInfoMovimiento.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@PostMapping(value = "/pageListByInfoMovimiento")
	public GenericResponseDTO<Object> pageListByInfoMovimiento(
			@RequestBody GenericRequestDTO<PageDTO<BusquedaGlobalDTO>> request) throws GenericException {
				log.info("Metodo controller pageListByInfoMovimiento iniciado");
		iGenericProducer = beanFactory.getBean(CompPersonClientAccountMovementConstants.PERSON_CLIENT_ACCOUNT_MOVEMENT_HANDLER,
				IGenericProducer.class);
		GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
				.userRequest(request.getUserRequest())
				.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_PAGE_LIST_BY_INFO_MOVIMIENTO_METHOD)
				.payload(request.getPayload())
				.type(EnumRequestType.SYNC)
				.transactionId(UUID.randomUUID().toString())
				.build();
		return GenericResponseDTO
				.success(GenericResponseUnwrapUtil.unwrap(iGenericProducer.process(producerModel)));
	}

	/**
	 * Expone el endpoint REST retrieveInfoMovimiento.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@GetMapping("/retrieveInfoMovimiento/{idMovimiento}")
	public GenericResponseDTO<Object> retrieveInfoMovimiento(@PathVariable Long idMovimiento)
			throws GenericException {
				log.info("Metodo controller retrieveInfoMovimiento iniciado");
		InfoMovimientoReqDTO payload = InfoMovimientoReqDTO.builder().idMovimiento(idMovimiento).build();
		Object raw = processAccountMovementUnwrapped(
				PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_LIST_BY_INFO_MOVIMIENTO_METHOD, payload);
		if (raw instanceof List<?> l && l.size() == 1) {
			return GenericResponseDTO.success(l.get(0));
		}
		throw new GenericException("Movimiento no encontrado", PersonClientAccountMovementConfigConstants.MISSING_VALUES);
	}

	/**
	 * Expone el endpoint REST deleteInfoMovimiento.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@DeleteMapping("/deleteInfoMovimiento/{idMovimiento}")
	public GenericResponseDTO<Object> deleteInfoMovimiento(@PathVariable Long idMovimiento)
			throws GenericException {
				log.info("Metodo controller deleteInfoMovimiento iniciado");
		ObjectIdDTO payload = ObjectIdDTO.builder().objectId(idMovimiento).build();
		iGenericProducer = beanFactory.getBean(CompPersonClientAccountMovementConstants.PERSON_CLIENT_ACCOUNT_MOVEMENT_HANDLER,
				IGenericProducer.class);
		GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
				.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_ELIMINADO_INFO_MOVIMIENTO_METHOD)
				.payload(payload)
				.type(EnumRequestType.SYNC)
				.transactionId(UUID.randomUUID().toString())
				.build();
		return GenericResponseDTO
				.success(GenericResponseUnwrapUtil.unwrap(iGenericProducer.process(producerModel)));
	}

	/**
	 * Expone el endpoint REST listByInfoMovimiento.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@GetMapping(value = "/listByInfoMovimiento")
	public GenericResponseDTO<Object> listByInfoMovimiento(@RequestParam(required = false) Long idMovimiento,
			@RequestParam(required = false) Long cuentaId,
			@RequestParam(required = false) String numeroCuenta,
			@RequestParam(required = false) Long tipoMovimientoId) throws GenericException {
				log.info("Metodo controller RequestParam iniciado");
		InfoMovimientoReqDTO payload = InfoMovimientoReqDTO.builder()
				.idMovimiento(idMovimiento)
				.cuentaId(cuentaId)
				.numeroCuenta(numeroCuenta)
				.tipoMovimientoId(tipoMovimientoId)
				.build();
		iGenericProducer = beanFactory.getBean(CompPersonClientAccountMovementConstants.PERSON_CLIENT_ACCOUNT_MOVEMENT_HANDLER,
				IGenericProducer.class);
		GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
				.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_LIST_BY_INFO_MOVIMIENTO_METHOD)
				.payload(payload)
				.type(EnumRequestType.SYNC)
				.transactionId(UUID.randomUUID().toString())
				.build();
		return GenericResponseDTO
				.success(GenericResponseUnwrapUtil.unwrap(iGenericProducer.process(producerModel)));
	}

	// --- AdmiTipoMovimiento ---

	/**
	 * Expone el endpoint REST saveAdmiTipoMovimiento.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@PostMapping(value = "/saveAdmiTipoMovimiento")
	public GenericResponseDTO<Object> saveAdmiTipoMovimiento(
			@RequestBody GenericRequestDTO<AdmiTipoMovimientoReqDTO> request) throws GenericException {
				log.info("Metodo controller saveAdmiTipoMovimiento iniciado");
		iGenericProducer = beanFactory.getBean(CompPersonClientAccountMovementConstants.PERSON_CLIENT_ACCOUNT_MOVEMENT_HANDLER,
				IGenericProducer.class);
		GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
				.userRequest(request.getUserRequest())
				.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_SAVE_ADMI_TIPO_MOVIMIENTO_METHOD)
				.payload(request.getPayload())
				.type(EnumRequestType.SYNC)
				.transactionId(UUID.randomUUID().toString())
				.build();
		return GenericResponseDTO
				.success(GenericResponseUnwrapUtil.unwrap(iGenericProducer.process(producerModel)));
	}

	/**
	 * Expone el endpoint REST updateAdmiTipoMovimiento.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@PutMapping(value = "/updateAdmiTipoMovimiento")
	public GenericResponseDTO<Object> updateAdmiTipoMovimiento(
			@RequestBody GenericRequestDTO<AdmiTipoMovimientoReqDTO> request) throws GenericException {
				log.info("Metodo controller updateAdmiTipoMovimiento iniciado");
		iGenericProducer = beanFactory.getBean(CompPersonClientAccountMovementConstants.PERSON_CLIENT_ACCOUNT_MOVEMENT_HANDLER,
				IGenericProducer.class);
		GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
				.userRequest(request.getUserRequest())
				.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_UPDATE_ADMI_TIPO_MOVIMIENTO_METHOD)
				.payload(request.getPayload())
				.type(EnumRequestType.SYNC)
				.transactionId(UUID.randomUUID().toString())
				.build();
		return GenericResponseDTO
				.success(GenericResponseUnwrapUtil.unwrap(iGenericProducer.process(producerModel)));
	}

	/**
	 * Expone el endpoint REST pageListByAdmiTipoMovimiento.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@PostMapping(value = "/pageListByAdmiTipoMovimiento")
	public GenericResponseDTO<Object> pageListByAdmiTipoMovimiento(
			@RequestBody GenericRequestDTO<PageDTO<AdmiTipoMovimientoReqDTO>> request) throws GenericException {
				log.info("Metodo controller pageListByAdmiTipoMovimiento iniciado");
		iGenericProducer = beanFactory.getBean(CompPersonClientAccountMovementConstants.PERSON_CLIENT_ACCOUNT_MOVEMENT_HANDLER,
				IGenericProducer.class);
		GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
				.userRequest(request.getUserRequest())
				.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_PAGE_LIST_BY_ADMI_TIPO_MOVIMIENTO_METHOD)
				.payload(request.getPayload())
				.type(EnumRequestType.SYNC)
				.transactionId(UUID.randomUUID().toString())
				.build();
		return GenericResponseDTO
				.success(GenericResponseUnwrapUtil.unwrap(iGenericProducer.process(producerModel)));
	}

	/**
	 * Expone el endpoint REST retrieveAdmiTipoMovimiento.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@GetMapping("/retrieveAdmiTipoMovimiento/{idTipoMovimiento}")
	public GenericResponseDTO<Object> retrieveAdmiTipoMovimiento(@PathVariable Long idTipoMovimiento)
			throws GenericException {
				log.info("Metodo controller retrieveAdmiTipoMovimiento iniciado");
		AdmiTipoMovimientoReqDTO payload = AdmiTipoMovimientoReqDTO.builder().idTipoMovimiento(idTipoMovimiento)
				.build();
		Object raw = processAccountMovementUnwrapped(
				PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_LIST_BY_ADMI_TIPO_MOVIMIENTO_METHOD, payload);
		if (raw instanceof List<?> l && l.size() == 1) {
			return GenericResponseDTO.success(l.get(0));
		}
		throw new GenericException("Tipo de movimiento no encontrado", PersonClientAccountMovementConfigConstants.MISSING_VALUES);
	}

	/**
	 * Expone el endpoint REST deleteAdmiTipoMovimiento.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@DeleteMapping("/deleteAdmiTipoMovimiento/{idTipoMovimiento}")
	public GenericResponseDTO<Object> deleteAdmiTipoMovimiento(@PathVariable Long idTipoMovimiento)
			throws GenericException {
				log.info("Metodo controller deleteAdmiTipoMovimiento iniciado");
		ObjectIdDTO payload = ObjectIdDTO.builder().objectId(idTipoMovimiento).build();
		iGenericProducer = beanFactory.getBean(CompPersonClientAccountMovementConstants.PERSON_CLIENT_ACCOUNT_MOVEMENT_HANDLER,
				IGenericProducer.class);
		GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
				.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_ELIMINADO_ADMI_TIPO_MOVIMIENTO_METHOD)
				.payload(payload)
				.type(EnumRequestType.SYNC)
				.transactionId(UUID.randomUUID().toString())
				.build();
		return GenericResponseDTO
				.success(GenericResponseUnwrapUtil.unwrap(iGenericProducer.process(producerModel)));
	}

	/**
	 * Expone el endpoint REST listByAdmiTipoMovimiento.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@GetMapping(value = "/listByAdmiTipoMovimiento")
	public GenericResponseDTO<Object> listByAdmiTipoMovimiento(
			@RequestParam(required = false) Long idTipoMovimiento,
			@RequestParam(required = false) String nombreMovimiento,
			@RequestParam(required = false) String descripcion,
			@RequestParam(required = false) String estado) throws GenericException {
				log.info("Metodo controller RequestParam iniciado");
		AdmiTipoMovimientoReqDTO payload = AdmiTipoMovimientoReqDTO.builder()
				.idTipoMovimiento(idTipoMovimiento)
				.nombreMovimiento(nombreMovimiento)
				.descripcion(descripcion)
				.estado(estado)
				.build();
		iGenericProducer = beanFactory.getBean(CompPersonClientAccountMovementConstants.PERSON_CLIENT_ACCOUNT_MOVEMENT_HANDLER,
				IGenericProducer.class);
		GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
				.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_LIST_BY_ADMI_TIPO_MOVIMIENTO_METHOD)
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
