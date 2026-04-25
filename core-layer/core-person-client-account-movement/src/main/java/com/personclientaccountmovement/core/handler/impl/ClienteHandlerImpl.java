package com.personclientaccountmovement.core.handler.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.personclientaccountmovement.core.dao.IInfoClienteDAO;
import com.personclientaccountmovement.core.handler.IClienteHandler;
import com.personclientaccountmovement.dto.BusquedaGlobalDTO;
import com.personclientaccountmovement.dto.InfoClienteReqDTO;
import com.personclientaccountmovement.dto.InfoClienteResDTO;
import com.personclientaccountmovement.dto.PersonaClienteUnifiedReqDTO;
import com.personclientaccountmovement.dto.PersonaClienteUnifiedResDTO;
import com.personclientaccountmovement.dto.ObjectIdDTO;
import com.personclientaccountmovement.dto.PageDTO;
import com.personclientaccountmovement.enums.EnumStatus;
import com.personclientaccountmovement.exception.GenericException;
import com.personclientaccountmovement.integration.dto.request.GenericRequestDTO;
import com.personclientaccountmovement.integration.util.CommonFormat;
import com.personclientaccountmovement.support.ExceptionLogSupport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
* Implementación del manejador de cliente en el núcleo.
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Service
public class ClienteHandlerImpl implements IClienteHandler {

	@Autowired
	private IInfoClienteDAO iInfoClienteDAO;

	Logger log = LogManager.getLogger(this.getClass());

	/**
	 * Persiste el registro indicado en la solicitud.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public InfoClienteResDTO saveInfoCliente(GenericRequestDTO<?> request) throws GenericException {
		log.info("Metodo core saveInfoCliente iniciado");
		try {
			InfoClienteReqDTO requestDTO = CommonFormat.objectMapping(request.getPayload(), InfoClienteReqDTO.class);
			return iInfoClienteDAO.saveInfoCliente(requestDTO);
		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("core-person-client-account-movement", e);
			throw e;
		}
	}

	/**
	 * Actualiza el registro indicado en la solicitud.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public InfoClienteResDTO updateInfoCliente(GenericRequestDTO<?> request) throws GenericException {
		log.info("Metodo core updateInfoCliente iniciado");
		try {
			InfoClienteReqDTO requestDTO = CommonFormat.objectMapping(request.getPayload(), InfoClienteReqDTO.class);
			return iInfoClienteDAO.updateInfoCliente(requestDTO);
		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("core-person-client-account-movement", e);
			throw e;
		}
	}

	/**
	 * Realiza baja lógica u operación de borrado según reglas de negocio.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public Boolean deleteInfoCliente(GenericRequestDTO<?> request) throws GenericException {
		log.info("Metodo core deleteInfoCliente iniciado");
		try {
			ObjectIdDTO requestDTO = CommonFormat.objectMapping(request.getPayload(), ObjectIdDTO.class);
			return iInfoClienteDAO.deleteInfoCliente(requestDTO);
		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("core-person-client-account-movement", e);
			throw e;
		}
	}

	/**
	 * Consulta listados filtrados según criterios de la solicitud.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public List<PersonaClienteUnifiedResDTO> listByInfoCliente(GenericRequestDTO<?> request) throws GenericException {
		log.info("Metodo core listByInfoCliente iniciado");
		try {
			PersonaClienteUnifiedReqDTO requestDTO = CommonFormat.objectMapping(request.getPayload(), PersonaClienteUnifiedReqDTO.class);
			return iInfoClienteDAO.listByInfoCliente(requestDTO);
		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("core-person-client-account-movement", e);
			throw e;
		}
	}

	/**
	 * Operación retrieveInfoCliente.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public PersonaClienteUnifiedResDTO retrieveInfoCliente(GenericRequestDTO<?> request) throws GenericException {
		log.info("Metodo core retrieveInfoCliente iniciado");
		try {
			ObjectIdDTO requestDTO = CommonFormat.objectMapping(request.getPayload(), ObjectIdDTO.class);
			return iInfoClienteDAO.retrieveInfoCliente(requestDTO);
		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("core-person-client-account-movement", e);
			throw e;
		}
	}
	
	/**
	 * Consulta paginada con filtros y orden aplicados.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public Page<PersonaClienteUnifiedResDTO> pageListByInfoCliente(GenericRequestDTO<?> request) throws GenericException {
		log.info("Metodo core pageListByInfoCliente iniciado");
		try {
			PageDTO<BusquedaGlobalDTO> requestDTO = CommonFormat.pageMapping(request.getPayload(),
					BusquedaGlobalDTO.class);
			return iInfoClienteDAO.pageListByInfoCliente(requestDTO);
		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("core-person-client-account-movement", e);
			throw e;
		}
	}
}
