package com.personclientaccountmovement.core.handler.impl;

import java.util.List;
import org.springframework.data.domain.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personclientaccountmovement.integration.dto.request.GenericRequestDTO;
import com.personclientaccountmovement.integration.util.CommonFormat;
import com.personclientaccountmovement.core.dao.IInfoPersonaDAO;
import com.personclientaccountmovement.core.handler.IPersonaHandler;
import com.personclientaccountmovement.dto.InfoPersonaReqDTO;
import com.personclientaccountmovement.dto.InfoPersonaResDTO;
import com.personclientaccountmovement.dto.ObjectIdDTO;
import com.personclientaccountmovement.dto.PageDTO;
import com.personclientaccountmovement.dto.PersonaClienteUnifiedReqDTO;
import com.personclientaccountmovement.dto.PersonaClienteUnifiedResDTO;
import com.personclientaccountmovement.exception.GenericException;
import com.personclientaccountmovement.support.ExceptionLogSupport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
* Handler implement de la entidad InfoPersona
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Service
public class PersonaHandlerImpl implements IPersonaHandler {

	@Autowired
	private IInfoPersonaDAO iInfoPersonaDAO;

	Logger log = LogManager.getLogger(this.getClass());

	/**
	 * Método para guardar un InfoPersona
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public PersonaClienteUnifiedResDTO saveInfoPersona(
			GenericRequestDTO<?> request) throws GenericException {
		log.info("Metodo core saveInfoPersona iniciado");
		PersonaClienteUnifiedResDTO response = null;
		PersonaClienteUnifiedReqDTO requestDTO = null;
		try {
			requestDTO = CommonFormat.objectMapping(request.getPayload(),
					PersonaClienteUnifiedReqDTO.class);
			response = iInfoPersonaDAO.saveInfoPersona(requestDTO);
		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("core-person-client-account-movement", e);
			throw e;
		}
		return response;
	}

	/**
	 * Método para actualizar un InfoPersona
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public PersonaClienteUnifiedResDTO updateInfoPersona(
			GenericRequestDTO<?> request) throws GenericException {
		log.info("Metodo core updateInfoPersona iniciado");
		PersonaClienteUnifiedResDTO response = null;
		PersonaClienteUnifiedReqDTO requestDTO = null;
		try {
			requestDTO = CommonFormat.objectMapping(request.getPayload(),
					PersonaClienteUnifiedReqDTO.class);
			response = iInfoPersonaDAO.updateInfoPersona(requestDTO);
		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("core-person-client-account-movement", e);
			throw e;
		}
		return response;
	}

	/**
	 * Método para retornar lista de InfoPersona
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public List<InfoPersonaResDTO> listByInfoPersona(
			GenericRequestDTO<?> request) throws GenericException {
		log.info("Metodo core listByInfoPersona iniciado");
		List<InfoPersonaResDTO> response = null;
		InfoPersonaReqDTO requestDTO = null;
		try {
			requestDTO = CommonFormat.objectMapping(request.getPayload(),
					InfoPersonaReqDTO.class);
			response = iInfoPersonaDAO.listByInfoPersona(requestDTO);
		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("core-person-client-account-movement", e);
			throw e;
		}
		return response;
	}

	/**
	 * Método para eliminar un InfoPersona
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public Boolean deleteInfoPersona(
			GenericRequestDTO<?> request) throws GenericException {
		log.info("Metodo core deleteInfoPersona iniciado");
		Boolean response = false;
		ObjectIdDTO requestDTO = null;
		try {
			requestDTO = CommonFormat.objectMapping(request.getPayload(), ObjectIdDTO.class);
			response = iInfoPersonaDAO.deleteInfoPersona(requestDTO);
		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("core-person-client-account-movement", e);
			throw e;
		}
		return response;
	}


	/**
	 * Método para retornar lista paginada con filtros de InfoPersona
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public Page<InfoPersonaResDTO> pageListByInfoPersona(
			GenericRequestDTO<?> request) throws GenericException {
		log.info("Metodo core pageListByInfoPersona iniciado");
		Page<InfoPersonaResDTO> response = null;
		PageDTO<InfoPersonaReqDTO> requestDTO = null;
		InfoPersonaReqDTO requestDTOActive = null;
		try {

			requestDTO = CommonFormat.pageMapping(request.getPayload(),
			InfoPersonaReqDTO.class);
			requestDTOActive = requestDTO.getTabla();
			requestDTO.setTabla(requestDTOActive);
			response = iInfoPersonaDAO.pageListByInfoPersona(requestDTO);
		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("core-person-client-account-movement", e);
			throw e;
		}

		return response;
	}

	/**
	 * Operación retrieveInfoPersona.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public PersonaClienteUnifiedResDTO retrieveInfoPersona(GenericRequestDTO<?> request) throws GenericException {
		log.info("Metodo core retrieveInfoPersona iniciado");
		try {
			ObjectIdDTO requestDTO = CommonFormat.objectMapping(request.getPayload(), ObjectIdDTO.class);
			return iInfoPersonaDAO.retrieveInfoPersona(requestDTO);
		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("core-person-client-account-movement", e);
			throw e;
		}
	}

}
