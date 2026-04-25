package com.personclientaccountmovement.core.handler.impl;

import java.util.List;

import org.springframework.data.domain.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.personclientaccountmovement.core.dao.IInfoCuentaDAO;
import com.personclientaccountmovement.core.handler.ICuentaHandler;
import com.personclientaccountmovement.dto.BusquedaGlobalDTO;
import com.personclientaccountmovement.dto.InfoCuentaPersonaUnifiedReqDTO;
import com.personclientaccountmovement.dto.InfoCuentaPersonaUnifiedResDTO;
import com.personclientaccountmovement.dto.ObjectIdDTO;
import com.personclientaccountmovement.dto.PageDTO;
import com.personclientaccountmovement.exception.GenericException;
import com.personclientaccountmovement.integration.dto.request.GenericRequestDTO;
import com.personclientaccountmovement.integration.util.CommonFormat;
import com.personclientaccountmovement.support.ExceptionLogSupport;

/**
* Handler implement de la entidad InfoCuenta
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Service
public class CuentaHandlerImpl implements ICuentaHandler {

	Logger log = LogManager.getLogger(this.getClass());

	@Autowired
	private IInfoCuentaDAO iInfoCuentaDAO;

	/**
	 * Persiste el registro indicado en la solicitud.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public InfoCuentaPersonaUnifiedResDTO saveInfoCuenta(GenericRequestDTO<?> request) throws GenericException {
		log.info("Metodo core saveInfoCuenta iniciado");
		try {
			InfoCuentaPersonaUnifiedReqDTO payload = CommonFormat.objectMapping(request.getPayload(),
					InfoCuentaPersonaUnifiedReqDTO.class);
			return iInfoCuentaDAO.saveInfoCuenta(payload);
		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("core-account-movement", e);
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
	public InfoCuentaPersonaUnifiedResDTO updateInfoCuenta(GenericRequestDTO<?> request) throws GenericException {
		log.info("Metodo core updateInfoCuenta iniciado");
		try {
			InfoCuentaPersonaUnifiedReqDTO payload = CommonFormat.objectMapping(request.getPayload(),
					InfoCuentaPersonaUnifiedReqDTO.class);
			return iInfoCuentaDAO.updateInfoCuenta(payload);
		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("core-account-movement", e);
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
	public List<InfoCuentaPersonaUnifiedResDTO> listByInfoCuenta(GenericRequestDTO<?> request) throws GenericException {
		log.info("Metodo core listByInfoCuenta iniciado");
		try {
			InfoCuentaPersonaUnifiedReqDTO payload = CommonFormat.objectMapping(request.getPayload(),
					InfoCuentaPersonaUnifiedReqDTO.class);
			return iInfoCuentaDAO.listByInfoCuenta(payload);
		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("core-account-movement", e);
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
	public Boolean deleteInfoCuenta(GenericRequestDTO<?> request) throws GenericException {
		log.info("Metodo core deleteInfoCuenta iniciado");
		Boolean response = false;
		ObjectIdDTO requestDTO = null;
		try {
			requestDTO = CommonFormat.objectMapping(request.getPayload(), ObjectIdDTO.class);
			response = iInfoCuentaDAO.deleteInfoCuenta(requestDTO);
		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("core-account-movement", e);
			throw e;
		}
		return response;
	}

	/**
	 * Consulta paginada con filtros y orden aplicados.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public Page<InfoCuentaPersonaUnifiedResDTO> pageListByInfoCuenta(GenericRequestDTO<?> request)
			throws GenericException {
				log.info("Metodo core pageListByInfoCuenta iniciado");
		try {
			PageDTO<BusquedaGlobalDTO> pageUnified = CommonFormat.pageMapping(request.getPayload(),
					BusquedaGlobalDTO.class);
			return iInfoCuentaDAO.pageListByInfoCuenta(pageUnified);
		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("core-account-movement", e);
			throw e;
		}
	}

	/**
	 * Valida reglas de negocio para la operación solicitada.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public  Boolean validarInfoCuentaByPersona(GenericRequestDTO<?> request) throws GenericException {
		log.info("Metodo core validarInfoCuentaByPersona iniciado");
		Boolean response = false;
		try {
			ObjectIdDTO payload = CommonFormat.objectMapping(request.getPayload(),
					ObjectIdDTO.class);
			response = iInfoCuentaDAO.validarInfoCuentaByPersona(payload);
		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("core-account-movement", e);
			throw e;
		}
		return response;
	}
}
