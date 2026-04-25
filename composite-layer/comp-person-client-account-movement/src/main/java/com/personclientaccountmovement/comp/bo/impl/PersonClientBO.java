package com.personclientaccountmovement.comp.bo.impl;

import java.util.UUID;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personclientaccountmovement.comp.bo.IPersonClientBO;
import com.personclientaccountmovement.comp.cons.CompPersonClientAccountMovementConstants;
import com.personclientaccountmovement.cons.PersonClientAccountMovementConfigConstants;
import com.personclientaccountmovement.dto.PageDTO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.personclientaccountmovement.dto.AdmiTipoCuentaReqDTO;
import com.personclientaccountmovement.dto.AdmiTipoMovimientoReqDTO;
import com.personclientaccountmovement.dto.BusquedaGlobalDTO;
import com.personclientaccountmovement.dto.InfoClienteReqDTO;
import com.personclientaccountmovement.dto.InfoCuentaPersonaUnifiedReqDTO;
import com.personclientaccountmovement.dto.InfoMovimientoReporteReqDTO;
import com.personclientaccountmovement.dto.InfoMovimientoReqDTO;
import com.personclientaccountmovement.dto.InfoPersonaReqDTO;
import com.personclientaccountmovement.dto.ObjectIdDTO;
import com.personclientaccountmovement.dto.PersonaClienteUnifiedReqDTO;
import com.personclientaccountmovement.exception.GenericException;
import com.personclientaccountmovement.integration.enums.EnumRequestType;
import com.personclientaccountmovement.integration.interfaces.IGenericProducer;
import com.personclientaccountmovement.integration.model.GenericProducerModel;

/**
* Lógica de negocio compuesta para PersonClient.
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Service
public class PersonClientBO implements IPersonClientBO {

	Logger log = LogManager.getLogger(this.getClass());

	@Autowired
	private BeanFactory factory;

	private IGenericProducer producer;


	//InfoPersona
	
	/**
	 * Persiste el registro indicado en la solicitud.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public <T, R> R saveInfoPersona(T data) throws GenericException {
		log.info("Metodo bo saveInfoPersona iniciado");
		producer = (IGenericProducer) factory.getBean(CompPersonClientAccountMovementConstants.MESSAGE_PRODUCER);
		PersonaClienteUnifiedReqDTO payload = (PersonaClienteUnifiedReqDTO) data;
		GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
				.type(EnumRequestType.SYNC)
				.transactionId(UUID.randomUUID().toString())
				.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_SAVE_INFO_PERSONA_METHOD)
				.payload(payload)
				.service(CompPersonClientAccountMovementConstants.PERSON_CLIENTE_ACCOUNT_MOVEMENT_MAP)
				.build();
		return (R) producer.process(producerModel);
	}

	
	/**
	 * Actualiza el registro indicado en la solicitud.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public <T, R> R updateInfoPersona(T data) throws GenericException {
		log.info("Metodo bo updateInfoPersona iniciado");
		producer = (IGenericProducer) factory.getBean(CompPersonClientAccountMovementConstants.MESSAGE_PRODUCER);
		PersonaClienteUnifiedReqDTO payload = (PersonaClienteUnifiedReqDTO) data;
		GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
				.type(EnumRequestType.SYNC)
				.transactionId(UUID.randomUUID().toString())
				.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_UPDATE_INFO_PERSONA_METHOD)
				.payload(payload)
				.service(CompPersonClientAccountMovementConstants.PERSON_CLIENTE_ACCOUNT_MOVEMENT_MAP)
				.build();
		return (R) producer.process(producerModel);
	}

	
	/**
	 * Realiza baja lógica u operación de borrado según reglas de negocio.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public <T, R> R deleteInfoPersona(T data) throws GenericException {
		log.info("Metodo bo deleteInfoPersona iniciado");
		producer = (IGenericProducer) factory.getBean(CompPersonClientAccountMovementConstants.MESSAGE_PRODUCER);
		ObjectIdDTO payload = (ObjectIdDTO) data;
		GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
				.type(EnumRequestType.SYNC)
				.transactionId(UUID.randomUUID().toString())
				.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_ELIMINADO_INFO_PERSONA_METHOD)
				.payload(payload)
				.service(CompPersonClientAccountMovementConstants.PERSON_CLIENTE_ACCOUNT_MOVEMENT_MAP)
				.build();
		return (R) producer.process(producerModel);
	}

	
	/**
	 * Consulta listados filtrados según criterios de la solicitud.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public <T, R> R listByInfoPersona(T data) throws GenericException {
		log.info("Metodo bo listByInfoPersona iniciado");
		producer = (IGenericProducer) factory.getBean(CompPersonClientAccountMovementConstants.MESSAGE_PRODUCER);
		InfoPersonaReqDTO payload = (InfoPersonaReqDTO) data;
		GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
				.type(EnumRequestType.SYNC)
				.transactionId(UUID.randomUUID().toString())
				.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_LIST_BY_INFO_PERSONA_METHOD)
				.payload(payload)
				.service(CompPersonClientAccountMovementConstants.PERSON_CLIENTE_ACCOUNT_MOVEMENT_MAP)
				.build();
		return (R) producer.process(producerModel);
	}

	
	/**
	 * Consulta paginada con filtros y orden aplicados.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public <T, R> R pageListByInfoPersona(T data) throws GenericException {
		log.info("Metodo bo pageListByInfoPersona iniciado");
		producer = (IGenericProducer) factory.getBean(CompPersonClientAccountMovementConstants.MESSAGE_PRODUCER);
		PageDTO<InfoPersonaReqDTO> payload = (PageDTO<InfoPersonaReqDTO>) data;
		GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
				.type(EnumRequestType.SYNC)
				.transactionId(UUID.randomUUID().toString())
				.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_PAGE_LIST_BY_INFO_PERSONA_METHOD)
				.payload(payload)
				.service(CompPersonClientAccountMovementConstants.PERSON_CLIENTE_ACCOUNT_MOVEMENT_MAP)
				.build();
		return (R) producer.process(producerModel);
	}

	//InfoCliente
	
	/**
	 * Persiste el registro indicado en la solicitud.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public <T, R> R saveInfoCliente(T data) throws GenericException {
		log.info("Metodo bo saveInfoCliente iniciado");
		producer = (IGenericProducer) factory.getBean(CompPersonClientAccountMovementConstants.MESSAGE_PRODUCER);
		InfoClienteReqDTO payload = (InfoClienteReqDTO) data;
		GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
				.type(EnumRequestType.SYNC)
				.transactionId(UUID.randomUUID().toString())
				.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_SAVE_INFO_CLIENTE_METHOD)
				.payload(payload)
				.service(CompPersonClientAccountMovementConstants.PERSON_CLIENTE_ACCOUNT_MOVEMENT_MAP)
				.build();
		return (R) producer.process(producerModel);
	}

	
	/**
	 * Actualiza el registro indicado en la solicitud.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public <T, R> R updateInfoCliente(T data) throws GenericException {
		log.info("Metodo bo updateInfoCliente iniciado");
		producer = (IGenericProducer) factory.getBean(CompPersonClientAccountMovementConstants.MESSAGE_PRODUCER);
		InfoClienteReqDTO payload = (InfoClienteReqDTO) data;
		GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
				.type(EnumRequestType.SYNC)
				.transactionId(UUID.randomUUID().toString())
				.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_UPDATE_INFO_CLIENTE_METHOD)
				.payload(payload)
				.service(CompPersonClientAccountMovementConstants.PERSON_CLIENTE_ACCOUNT_MOVEMENT_MAP)
				.build();
		return (R) producer.process(producerModel);
	}

	
	/**
	 * Realiza baja lógica u operación de borrado según reglas de negocio.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public <T, R> R deleteInfoCliente(T data) throws GenericException {
		log.info("Metodo bo deleteInfoCliente iniciado");
		producer = (IGenericProducer) factory.getBean(CompPersonClientAccountMovementConstants.MESSAGE_PRODUCER);
		ObjectIdDTO payload = (ObjectIdDTO) data;
		GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
				.type(EnumRequestType.SYNC)
				.transactionId(UUID.randomUUID().toString())
				.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_ELIMINADO_INFO_CLIENTE_METHOD)
				.payload(payload)
				.service(CompPersonClientAccountMovementConstants.PERSON_CLIENTE_ACCOUNT_MOVEMENT_MAP)
				.build();
		return (R) producer.process(producerModel);
	}

	
	/**
	 * Operación retrieveInfoCliente.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public <T, R> R retrieveInfoCliente(T data) throws GenericException {
		log.info("Metodo bo retrieveInfoCliente iniciado");
		producer = (IGenericProducer) factory.getBean(CompPersonClientAccountMovementConstants.MESSAGE_PRODUCER);
		ObjectIdDTO payload = (ObjectIdDTO) data;
		GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
				.type(EnumRequestType.SYNC)
				.transactionId(UUID.randomUUID().toString())
				.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_RETRIEVE_INFO_CLIENTE_METHOD)
				.payload(payload)
				.service(CompPersonClientAccountMovementConstants.PERSON_CLIENTE_ACCOUNT_MOVEMENT_MAP)
				.build();
		return (R) producer.process(producerModel);
	}

	
	/**
	 * Consulta listados filtrados según criterios de la solicitud.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public <T, R> R listByInfoCliente(T data) throws GenericException {
		log.info("Metodo bo listByInfoCliente iniciado");
		producer = (IGenericProducer) factory.getBean(CompPersonClientAccountMovementConstants.MESSAGE_PRODUCER);
		PersonaClienteUnifiedReqDTO payload = (PersonaClienteUnifiedReqDTO) data;
		GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
				.type(EnumRequestType.SYNC)
				.transactionId(UUID.randomUUID().toString())
				.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_LIST_BY_INFO_CLIENTE_METHOD)
				.payload(payload)
				.service(CompPersonClientAccountMovementConstants.PERSON_CLIENTE_ACCOUNT_MOVEMENT_MAP)
				.build();
		return (R) producer.process(producerModel);
	}

	
	/**
	 * Consulta paginada con filtros y orden aplicados.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public <T, R> R pageListByInfoCliente(T data) throws GenericException {
		log.info("Metodo bo pageListByInfoCliente iniciado");
		producer = (IGenericProducer) factory.getBean(CompPersonClientAccountMovementConstants.MESSAGE_PRODUCER);
		PageDTO<BusquedaGlobalDTO> payload = (PageDTO<BusquedaGlobalDTO>) data;
		GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
				.type(EnumRequestType.SYNC)
				.transactionId(UUID.randomUUID().toString())
				.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_PAGE_LIST_BY_INFO_CLIENTE_METHOD)
				.payload(payload)
				.service(CompPersonClientAccountMovementConstants.PERSON_CLIENTE_ACCOUNT_MOVEMENT_MAP)
				.build();
		return (R) producer.process(producerModel);
	}

	//AdmiTipoMovimiento
	
	/**
	 * Persiste el registro indicado en la solicitud.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public <T, R> R saveAdmiTipoMovimiento(T data) throws GenericException {
		log.info("Metodo bo saveAdmiTipoMovimiento iniciado");
		producer = (IGenericProducer) factory.getBean(CompPersonClientAccountMovementConstants.MESSAGE_PRODUCER);
		AdmiTipoMovimientoReqDTO payload = (AdmiTipoMovimientoReqDTO) data;
		GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
				.type(EnumRequestType.SYNC)
				.transactionId(UUID.randomUUID().toString())
				.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_SAVE_ADMI_TIPO_MOVIMIENTO_METHOD)
				.payload(payload)
				.service(CompPersonClientAccountMovementConstants.PERSON_CLIENTE_ACCOUNT_MOVEMENT_MAP)
				.build();
		return (R) producer.process(producerModel);
	}

	
	/**
	 * Actualiza el registro indicado en la solicitud.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public <T, R> R updateAdmiTipoMovimiento(T data) throws GenericException {
		log.info("Metodo bo updateAdmiTipoMovimiento iniciado");
		producer = (IGenericProducer) factory.getBean(CompPersonClientAccountMovementConstants.MESSAGE_PRODUCER);
		AdmiTipoMovimientoReqDTO payload = (AdmiTipoMovimientoReqDTO) data;
		GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
				.type(EnumRequestType.SYNC)
				.transactionId(UUID.randomUUID().toString())
				.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_UPDATE_ADMI_TIPO_MOVIMIENTO_METHOD)
				.payload(payload)
				.service(CompPersonClientAccountMovementConstants.PERSON_CLIENTE_ACCOUNT_MOVEMENT_MAP)
				.build();
		return (R) producer.process(producerModel);
	}

	
	/**
	 * Realiza baja lógica u operación de borrado según reglas de negocio.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public <T, R> R deleteAdmiTipoMovimiento(T data) throws GenericException {
		log.info("Metodo bo deleteAdmiTipoMovimiento iniciado");
		producer = (IGenericProducer) factory.getBean(CompPersonClientAccountMovementConstants.MESSAGE_PRODUCER);
		ObjectIdDTO payload = (ObjectIdDTO) data;
		GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
				.type(EnumRequestType.SYNC)
				.transactionId(UUID.randomUUID().toString())
				.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_ELIMINADO_ADMI_TIPO_MOVIMIENTO_METHOD)
				.payload(payload)
				.service(CompPersonClientAccountMovementConstants.PERSON_CLIENTE_ACCOUNT_MOVEMENT_MAP)
				.build();
		return (R) producer.process(producerModel);
	}

	
	/**
	 * Consulta listados filtrados según criterios de la solicitud.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public <T, R> R listByAdmiTipoMovimiento(T data) throws GenericException {
		log.info("Metodo bo listByAdmiTipoMovimiento iniciado");
		producer = (IGenericProducer) factory.getBean(CompPersonClientAccountMovementConstants.MESSAGE_PRODUCER);
		AdmiTipoMovimientoReqDTO payload = (AdmiTipoMovimientoReqDTO) data;
		GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
				.type(EnumRequestType.SYNC)
				.transactionId(UUID.randomUUID().toString())
				.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_LIST_BY_ADMI_TIPO_MOVIMIENTO_METHOD)
				.payload(payload)
				.service(CompPersonClientAccountMovementConstants.PERSON_CLIENTE_ACCOUNT_MOVEMENT_MAP)
				.build();
		return (R) producer.process(producerModel);
	}

	
	/**
	 * Consulta paginada con filtros y orden aplicados.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public <T, R> R pageListByAdmiTipoMovimiento(T data) throws GenericException {
		log.info("Metodo bo pageListByAdmiTipoMovimiento iniciado");
		producer = (IGenericProducer) factory.getBean(CompPersonClientAccountMovementConstants.MESSAGE_PRODUCER);
		PageDTO<AdmiTipoMovimientoReqDTO> payload = (PageDTO<AdmiTipoMovimientoReqDTO>) data;
		GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
				.type(EnumRequestType.SYNC)
				.transactionId(UUID.randomUUID().toString())
				.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_PAGE_LIST_BY_ADMI_TIPO_MOVIMIENTO_METHOD)
				.payload(payload)
				.service(CompPersonClientAccountMovementConstants.PERSON_CLIENTE_ACCOUNT_MOVEMENT_MAP)
				.build();
		return (R) producer.process(producerModel);
	}


	//AdmiTipoCuenta
	
	/**
	 * Persiste el registro indicado en la solicitud.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public <T, R> R saveAdmiTipoCuenta(T data) throws GenericException {
		log.info("Metodo bo saveAdmiTipoCuenta iniciado");
		producer = (IGenericProducer) factory.getBean(CompPersonClientAccountMovementConstants.MESSAGE_PRODUCER);
		AdmiTipoCuentaReqDTO payload = (AdmiTipoCuentaReqDTO) data;
		GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
				.type(EnumRequestType.SYNC)
				.transactionId(UUID.randomUUID().toString())
				.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_SAVE_ADMI_TIPO_CUENTA_METHOD)
				.payload(payload)
				.service(CompPersonClientAccountMovementConstants.PERSON_CLIENTE_ACCOUNT_MOVEMENT_MAP)
				.build();
		return (R) producer.process(producerModel);
	}

	
	/**
	 * Actualiza el registro indicado en la solicitud.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public <T, R> R updateAdmiTipoCuenta(T data) throws GenericException {
		log.info("Metodo bo updateAdmiTipoCuenta iniciado");
		producer = (IGenericProducer) factory.getBean(CompPersonClientAccountMovementConstants.MESSAGE_PRODUCER);
		AdmiTipoCuentaReqDTO payload = (AdmiTipoCuentaReqDTO) data;
		GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
				.type(EnumRequestType.SYNC)
				.transactionId(UUID.randomUUID().toString())
				.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_UPDATE_ADMI_TIPO_CUENTA_METHOD)
				.payload(payload)
				.service(CompPersonClientAccountMovementConstants.PERSON_CLIENTE_ACCOUNT_MOVEMENT_MAP)
				.build();
		return (R) producer.process(producerModel);
	}

	
	/**
	 * Realiza baja lógica u operación de borrado según reglas de negocio.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public <T, R> R deleteAdmiTipoCuenta(T data) throws GenericException {
		log.info("Metodo bo deleteAdmiTipoCuenta iniciado");
		producer = (IGenericProducer) factory.getBean(CompPersonClientAccountMovementConstants.MESSAGE_PRODUCER);
		ObjectIdDTO payload = (ObjectIdDTO) data;
		GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
				.type(EnumRequestType.SYNC)
				.transactionId(UUID.randomUUID().toString())
				.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_ELIMINADO_ADMI_TIPO_CUENTA_METHOD)
				.payload(payload)
				.service(CompPersonClientAccountMovementConstants.PERSON_CLIENTE_ACCOUNT_MOVEMENT_MAP)
				.build();
		return (R) producer.process(producerModel);
	}

	
	/**
	 * Consulta listados filtrados según criterios de la solicitud.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public <T, R> R listByAdmiTipoCuenta(T data) throws GenericException {
		log.info("Metodo bo listByAdmiTipoCuenta iniciado");
		producer = (IGenericProducer) factory.getBean(CompPersonClientAccountMovementConstants.MESSAGE_PRODUCER);
		AdmiTipoCuentaReqDTO payload = (AdmiTipoCuentaReqDTO) data;
		GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
				.type(EnumRequestType.SYNC)
				.transactionId(UUID.randomUUID().toString())
				.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_LIST_BY_ADMI_TIPO_CUENTA_METHOD)
				.payload(payload)
				.service(CompPersonClientAccountMovementConstants.PERSON_CLIENTE_ACCOUNT_MOVEMENT_MAP)
				.build();
		return (R) producer.process(producerModel);
	}

	
	/**
	 * Consulta paginada con filtros y orden aplicados.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public <T, R> R pageListByAdmiTipoCuenta(T data) throws GenericException {
		log.info("Metodo bo pageListByAdmiTipoCuenta iniciado");
		producer = (IGenericProducer) factory.getBean(CompPersonClientAccountMovementConstants.MESSAGE_PRODUCER);
		PageDTO<AdmiTipoCuentaReqDTO> payload = (PageDTO<AdmiTipoCuentaReqDTO>) data;
		GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
				.type(EnumRequestType.SYNC)
				.transactionId(UUID.randomUUID().toString())
				.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_PAGE_LIST_BY_ADMI_TIPO_CUENTA_METHOD)
				.payload(payload)
				.service(CompPersonClientAccountMovementConstants.PERSON_CLIENTE_ACCOUNT_MOVEMENT_MAP)
				.build();
		return (R) producer.process(producerModel);
	}


	//InfoMovimiento
	
	/**
	 * Persiste el registro indicado en la solicitud.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public <T, R> R saveInfoMovimiento(T data) throws GenericException {
		log.info("Metodo bo saveInfoMovimiento iniciado");
		producer = (IGenericProducer) factory.getBean(CompPersonClientAccountMovementConstants.MESSAGE_PRODUCER);
		InfoMovimientoReqDTO payload = (InfoMovimientoReqDTO) data;
		GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
				.type(EnumRequestType.SYNC)
				.transactionId(UUID.randomUUID().toString())
				.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_SAVE_INFO_MOVIMIENTO_METHOD)
				.payload(payload)
				.service(CompPersonClientAccountMovementConstants.PERSON_CLIENTE_ACCOUNT_MOVEMENT_MAP)
				.build();
		return (R) producer.process(producerModel);
	}

	
	/**
	 * Actualiza el registro indicado en la solicitud.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public <T, R> R updateInfoMovimiento(T data) throws GenericException {
		log.info("Metodo bo updateInfoMovimiento iniciado");
		producer = (IGenericProducer) factory.getBean(CompPersonClientAccountMovementConstants.MESSAGE_PRODUCER);
		InfoMovimientoReqDTO payload = (InfoMovimientoReqDTO) data;
		GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
				.type(EnumRequestType.SYNC)
				.transactionId(UUID.randomUUID().toString())
				.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_UPDATE_INFO_MOVIMIENTO_METHOD)
				.payload(payload)
				.service(CompPersonClientAccountMovementConstants.PERSON_CLIENTE_ACCOUNT_MOVEMENT_MAP)
				.build();
		return (R) producer.process(producerModel);
	}

	
	/**
	 * Realiza baja lógica u operación de borrado según reglas de negocio.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public <T, R> R deleteInfoMovimiento(T data) throws GenericException {
		log.info("Metodo bo deleteInfoMovimiento iniciado");
		producer = (IGenericProducer) factory.getBean(CompPersonClientAccountMovementConstants.MESSAGE_PRODUCER);
		ObjectIdDTO payload = (ObjectIdDTO) data;
		GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
				.type(EnumRequestType.SYNC)
				.transactionId(UUID.randomUUID().toString())
				.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_ELIMINADO_INFO_MOVIMIENTO_METHOD)
				.payload(payload)
				.service(CompPersonClientAccountMovementConstants.PERSON_CLIENTE_ACCOUNT_MOVEMENT_MAP)
				.build();
		return (R) producer.process(producerModel);
	}

	
	/**
	 * Consulta listados filtrados según criterios de la solicitud.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public <T, R> R listByInfoMovimiento(T data) throws GenericException {
		log.info("Metodo bo listByInfoMovimiento iniciado");
		producer = (IGenericProducer) factory.getBean(CompPersonClientAccountMovementConstants.MESSAGE_PRODUCER);
		InfoMovimientoReqDTO payload = (InfoMovimientoReqDTO) data;
		GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
				.type(EnumRequestType.SYNC)
				.transactionId(UUID.randomUUID().toString())
				.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_LIST_BY_INFO_MOVIMIENTO_METHOD)
				.payload(payload)
				.service(CompPersonClientAccountMovementConstants.PERSON_CLIENTE_ACCOUNT_MOVEMENT_MAP)
				.build();
		return (R) producer.process(producerModel);
	}

	
	/**
	 * Consulta paginada con filtros y orden aplicados.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public <T, R> R pageListByInfoMovimiento(T data) throws GenericException {
		log.info("Metodo bo pageListByInfoMovimiento iniciado");
		producer = (IGenericProducer) factory.getBean(CompPersonClientAccountMovementConstants.MESSAGE_PRODUCER);
		PageDTO<BusquedaGlobalDTO> payload = (PageDTO<BusquedaGlobalDTO>) data;
		GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
				.type(EnumRequestType.SYNC)
				.transactionId(UUID.randomUUID().toString())
				.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_PAGE_LIST_BY_INFO_MOVIMIENTO_METHOD)
				.payload(payload)
				.service(CompPersonClientAccountMovementConstants.PERSON_CLIENTE_ACCOUNT_MOVEMENT_MAP)
				.build();
		return (R) producer.process(producerModel);
	}

	//InfoCuenta
	
	/**
	 * Persiste el registro indicado en la solicitud.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public <T, R> R saveInfoCuenta(T data) throws GenericException {
		log.info("Metodo bo saveInfoCuenta iniciado");
		producer = (IGenericProducer) factory.getBean(CompPersonClientAccountMovementConstants.MESSAGE_PRODUCER);
		InfoCuentaPersonaUnifiedReqDTO payload = (InfoCuentaPersonaUnifiedReqDTO) data;
		GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
				.type(EnumRequestType.SYNC)
				.transactionId(UUID.randomUUID().toString())
				.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_SAVE_INFO_CUENTA_METHOD)
				.payload(payload)
				.service(CompPersonClientAccountMovementConstants.PERSON_CLIENTE_ACCOUNT_MOVEMENT_MAP)
				.build();
		return (R) producer.process(producerModel);
	}

	
	/**
	 * Actualiza el registro indicado en la solicitud.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public <T, R> R updateInfoCuenta(T data) throws GenericException {
		log.info("Metodo bo updateInfoCuenta iniciado");
		producer = (IGenericProducer) factory.getBean(CompPersonClientAccountMovementConstants.MESSAGE_PRODUCER);
		InfoCuentaPersonaUnifiedReqDTO payload = (InfoCuentaPersonaUnifiedReqDTO) data;
		GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
				.type(EnumRequestType.SYNC)
				.transactionId(UUID.randomUUID().toString())
				.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_UPDATE_INFO_CUENTA_METHOD)
				.payload(payload)
				.service(CompPersonClientAccountMovementConstants.PERSON_CLIENTE_ACCOUNT_MOVEMENT_MAP)
				.build();
		return (R) producer.process(producerModel);
	}

	
	/**
	 * Realiza baja lógica u operación de borrado según reglas de negocio.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public <T, R> R deleteInfoCuenta(T data) throws GenericException {
		log.info("Metodo bo deleteInfoCuenta iniciado");
		producer = (IGenericProducer) factory.getBean(CompPersonClientAccountMovementConstants.MESSAGE_PRODUCER);
		ObjectIdDTO payload = (ObjectIdDTO) data;
		GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
				.type(EnumRequestType.SYNC)
				.transactionId(UUID.randomUUID().toString())
				.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_ELIMINADO_INFO_CUENTA_METHOD)
				.payload(payload)
				.service(CompPersonClientAccountMovementConstants.PERSON_CLIENTE_ACCOUNT_MOVEMENT_MAP)
				.build();
		return (R) producer.process(producerModel);
	}

	
	/**
	 * Consulta listados filtrados según criterios de la solicitud.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public <T, R> R listByInfoCuenta(T data) throws GenericException {
		log.info("Metodo bo listByInfoCuenta iniciado");
		producer = (IGenericProducer) factory.getBean(CompPersonClientAccountMovementConstants.MESSAGE_PRODUCER);
		InfoCuentaPersonaUnifiedReqDTO payload = (InfoCuentaPersonaUnifiedReqDTO) data;
		GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
				.type(EnumRequestType.SYNC)
				.transactionId(UUID.randomUUID().toString())
				.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_LIST_BY_INFO_CUENTA_METHOD)
				.payload(payload)
				.service(CompPersonClientAccountMovementConstants.PERSON_CLIENTE_ACCOUNT_MOVEMENT_MAP)
				.build();
		return (R) producer.process(producerModel);
	}

	
	/**
	 * Consulta paginada con filtros y orden aplicados.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public <T, R> R pageListByInfoCuenta(T data) throws GenericException {
		log.info("Metodo bo pageListByInfoCuenta iniciado");
		producer = (IGenericProducer) factory.getBean(CompPersonClientAccountMovementConstants.MESSAGE_PRODUCER);
		PageDTO<BusquedaGlobalDTO> payload = (PageDTO<BusquedaGlobalDTO>) data;
		GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
				.type(EnumRequestType.SYNC)
				.transactionId(UUID.randomUUID().toString())
				.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_PAGE_LIST_BY_INFO_CUENTA_METHOD)
				.payload(payload)
				.service(CompPersonClientAccountMovementConstants.PERSON_CLIENTE_ACCOUNT_MOVEMENT_MAP)
				.build();
		return (R) producer.process(producerModel);
	}

	
	/**
	 * Consulta listados filtrados según criterios de la solicitud.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public <T, R> R listByInfoMovimientoReporte(T data) throws GenericException {
		log.info("Metodo bo listByInfoMovimientoReporte iniciado");
		producer = (IGenericProducer) factory.getBean(CompPersonClientAccountMovementConstants.MESSAGE_PRODUCER);
		InfoMovimientoReporteReqDTO payload = (InfoMovimientoReporteReqDTO) data;
		GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
				.type(EnumRequestType.SYNC)
				.transactionId(UUID.randomUUID().toString())
				.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_LIST_BY_INFO_MOVIMIENTO_REPORTE_METHOD)
				.payload(payload)
				.service(CompPersonClientAccountMovementConstants.PERSON_CLIENTE_ACCOUNT_MOVEMENT_MAP)
				.build();
		return (R) producer.process(producerModel);
	}

	/**
	 * Consulta listados filtrados según criterios de la solicitud.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public <T, R> R pageListByInfoMovimientoReporte(T data) throws GenericException {
		log.info("Metodo bo listByInfoMovimientoReporte iniciado");
		producer = (IGenericProducer) factory.getBean(CompPersonClientAccountMovementConstants.MESSAGE_PRODUCER);
		PageDTO<InfoMovimientoReporteReqDTO> payload = (PageDTO<InfoMovimientoReporteReqDTO>) data;
		GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
				.type(EnumRequestType.SYNC)
				.transactionId(UUID.randomUUID().toString())
				.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_PAGE_LIST_BY_INFO_MOVIMIENTO_REPORTE_METHOD)
				.payload(payload)
				.service(CompPersonClientAccountMovementConstants.PERSON_CLIENTE_ACCOUNT_MOVEMENT_MAP)
				.build();
		return (R) producer.process(producerModel);
	}

	/**
	 * Consulta listados filtrados según criterios de la solicitud.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public <T, R> R pageListByInfoMovimientoReportePdf(T data) throws GenericException {
		log.info("Metodo bo listByInfoMovimientoReporte iniciado");
		producer = (IGenericProducer) factory.getBean(CompPersonClientAccountMovementConstants.MESSAGE_PRODUCER);
		PageDTO<InfoMovimientoReporteReqDTO> payload = (PageDTO<InfoMovimientoReporteReqDTO>) data;
		GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
				.type(EnumRequestType.SYNC)
				.transactionId(UUID.randomUUID().toString())
				.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_PAGE_LIST_BY_INFO_MOVIMIENTO_REPORTE_PDF_METHOD)
				.payload(payload)
				.service(CompPersonClientAccountMovementConstants.PERSON_CLIENTE_ACCOUNT_MOVEMENT_MAP)
				.build();
		return (R) producer.process(producerModel);
	}
	
	/**
	 * Valida reglas de negocio para la operación solicitada.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public <T, R> R validarInfoCuentaByPersona(T data) throws GenericException {
		log.info("Metodo bo validarInfoCuentaByPersona iniciado");
		producer = (IGenericProducer) factory.getBean(CompPersonClientAccountMovementConstants.MESSAGE_PRODUCER);
		ObjectIdDTO payload = (ObjectIdDTO) data;
		GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
				.type(EnumRequestType.SYNC)
				.transactionId(UUID.randomUUID().toString())
				.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_VALIDAR_INFO_CUENTA_BY_USUARIO_METHOD)
				.payload(payload)
				.service(CompPersonClientAccountMovementConstants.PERSON_CLIENTE_ACCOUNT_MOVEMENT_MAP)
				.build();
		return (R) producer.process(producerModel);
	}
}
