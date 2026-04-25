package com.personclientaccountmovement.core.handler.impl;

import java.util.List;

import org.springframework.data.domain.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.personclientaccountmovement.integration.dto.request.GenericRequestDTO;
import com.personclientaccountmovement.integration.util.CommonFormat;
import com.personclientaccountmovement.dto.AdmiTipoCuentaReqDTO;
import com.personclientaccountmovement.dto.AdmiTipoCuentaResDTO;
import com.personclientaccountmovement.dto.ObjectIdDTO;
import com.personclientaccountmovement.dto.PageDTO;
import com.personclientaccountmovement.exception.GenericException;
import com.personclientaccountmovement.support.ExceptionLogSupport;

import com.personclientaccountmovement.core.dao.IAdmiTipoCuentaDAO;
import com.personclientaccountmovement.core.handler.ITipoCuentaHandler;

/**
* Handler implement de la entidad AdmiTipoCuenta
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Service
public class TipoCuentaHandlerImpl implements ITipoCuentaHandler {

	Logger log = LogManager.getLogger(this.getClass());

	@Autowired
	private IAdmiTipoCuentaDAO iAdmiTipoCuentaDAO;


	/**
	 * Método para guardar un AdmiTipoCuenta
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public AdmiTipoCuentaResDTO saveAdmiTipoCuenta(
			GenericRequestDTO<?> request) throws GenericException {
				log.info("Metodo core saveAdmiTipoCuenta iniciado");
		AdmiTipoCuentaResDTO response = null;
		AdmiTipoCuentaReqDTO requestDTO = null;
		try {
			requestDTO = CommonFormat.objectMapping(request.getPayload(),
			AdmiTipoCuentaReqDTO.class);
			response = iAdmiTipoCuentaDAO.saveAdmiTipoCuenta(requestDTO);
		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("core-account-movement", e);
			throw e;
		}
		return response;
	}

	/**
	 * Método para actualizar un AdmiTipoCuenta
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public AdmiTipoCuentaResDTO updateAdmiTipoCuenta(
			GenericRequestDTO<?> request) throws GenericException {
				log.info("Metodo core updateAdmiTipoCuenta iniciado");
		AdmiTipoCuentaResDTO response = null;
		AdmiTipoCuentaReqDTO requestDTO = null;
		try {
			requestDTO = CommonFormat.objectMapping(request.getPayload(),
			AdmiTipoCuentaReqDTO.class);
			response = iAdmiTipoCuentaDAO.updateAdmiTipoCuenta(requestDTO);
		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("core-account-movement", e);
			throw e;
		}
		return response;
	}

	/**
	 * Método para retornar lista de AdmiTipoCuenta
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public List<AdmiTipoCuentaResDTO> listByAdmiTipoCuenta(
			GenericRequestDTO<?> request) throws GenericException {
				log.info("Metodo core listByAdmiTipoCuenta iniciado");
		List<AdmiTipoCuentaResDTO> response = null;
		AdmiTipoCuentaReqDTO requestDTO = null;
		try {
			requestDTO = CommonFormat.objectMapping(request.getPayload(),
					AdmiTipoCuentaReqDTO.class);
			response = iAdmiTipoCuentaDAO.listByAdmiTipoCuenta(requestDTO);
		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("core-account-movement", e);
			throw e;
		}
		return response;
	}

	/**
	 * Método para eliminar un AdmiTipoCuenta
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public Boolean deleteAdmiTipoCuenta(
			GenericRequestDTO<?> request) throws GenericException {
				log.info("Metodo core deleteAdmiTipoCuenta iniciado");
		Boolean response = false;
		ObjectIdDTO requestDTO = null;
		try {
			requestDTO = CommonFormat.objectMapping(request.getPayload(), ObjectIdDTO.class);
			response = iAdmiTipoCuentaDAO.deleteAdmiTipoCuenta(requestDTO);
		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("core-account-movement", e);
			throw e;
		}
		return response;
	}


	/**
	 * Método para retornar lista paginada con filtros de AdmiTipoCuenta
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public Page<AdmiTipoCuentaResDTO> pageListByAdmiTipoCuenta(
			GenericRequestDTO<?> request) throws GenericException {
				log.info("Metodo core pageListByAdmiTipoCuenta iniciado");
		Page<AdmiTipoCuentaResDTO> response = null;
		PageDTO<AdmiTipoCuentaReqDTO> requestDTO = null;
		AdmiTipoCuentaReqDTO requestDTOActive = null;
		try {

			requestDTO = CommonFormat.pageMapping(request.getPayload(),
			AdmiTipoCuentaReqDTO.class);
			requestDTOActive = requestDTO.getTabla();
			requestDTO.setTabla(requestDTOActive);
			response = iAdmiTipoCuentaDAO.pageListByAdmiTipoCuenta(requestDTO);
		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("core-account-movement", e);
			throw e;
		}

		return response;
	}

}
