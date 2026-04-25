package com.personclientaccountmovement.core.handler.impl;

import java.util.List;

import org.springframework.data.domain.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.personclientaccountmovement.integration.dto.request.GenericRequestDTO;
import com.personclientaccountmovement.integration.util.CommonFormat;
import com.personclientaccountmovement.dto.AdmiTipoMovimientoReqDTO;
import com.personclientaccountmovement.dto.AdmiTipoMovimientoResDTO;
import com.personclientaccountmovement.dto.ObjectIdDTO;
import com.personclientaccountmovement.dto.PageDTO;
import com.personclientaccountmovement.exception.GenericException;
import com.personclientaccountmovement.support.ExceptionLogSupport;
import com.personclientaccountmovement.core.dao.IAdmiTipoMovimientoDAO;
import com.personclientaccountmovement.core.handler.ITipoMovimientoHandler;

/**
* Handler implement de la entidad AdmiTipoMovimiento
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Service
public class TipoMovimientoHandlerImpl implements ITipoMovimientoHandler {

	Logger log = LogManager.getLogger(this.getClass());

	@Autowired
	private IAdmiTipoMovimientoDAO iAdmiTipoMovimientoDAO;


	/**
	 * Método para guardar un AdmiTipoMovimiento
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public AdmiTipoMovimientoResDTO saveAdmiTipoMovimiento(
			GenericRequestDTO<?> request) throws GenericException {
				log.info("Metodo core saveAdmiTipoMovimiento iniciado");
		AdmiTipoMovimientoResDTO response = null;
		AdmiTipoMovimientoReqDTO requestDTO = null;
		try {
			requestDTO = CommonFormat.objectMapping(request.getPayload(),
			AdmiTipoMovimientoReqDTO.class);
			response = iAdmiTipoMovimientoDAO.saveAdmiTipoMovimiento(requestDTO);
		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("core-account-movement", e);
			throw e;
		}
		return response;
	}

	/**
	 * Método para actualizar un AdmiTipoMovimiento
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public AdmiTipoMovimientoResDTO updateAdmiTipoMovimiento(
			GenericRequestDTO<?> request) throws GenericException {
				log.info("Metodo core updateAdmiTipoMovimiento iniciado");
		AdmiTipoMovimientoResDTO response = null;
		AdmiTipoMovimientoReqDTO requestDTO = null;
		try {
			requestDTO = CommonFormat.objectMapping(request.getPayload(),
			AdmiTipoMovimientoReqDTO.class);
			response = iAdmiTipoMovimientoDAO.updateAdmiTipoMovimiento(requestDTO);
		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("core-account-movement", e);
			throw e;
		}
		return response;
	}

	/**
	 * Método para retornar lista de AdmiTipoMovimiento
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public List<AdmiTipoMovimientoResDTO> listByAdmiTipoMovimiento(
			GenericRequestDTO<?> request) throws GenericException {
				log.info("Metodo core listByAdmiTipoMovimiento iniciado");
		List<AdmiTipoMovimientoResDTO> response = null;
		AdmiTipoMovimientoReqDTO requestDTO = null;
		try {
			requestDTO = CommonFormat.objectMapping(request.getPayload(),
					AdmiTipoMovimientoReqDTO.class);
			response = iAdmiTipoMovimientoDAO.listByAdmiTipoMovimiento(requestDTO);
		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("core-account-movement", e);
			throw e;
		}
		return response;
	}

	/**
	 * Método para eliminar un AdmiTipoMovimiento
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public Boolean deleteAdmiTipoMovimiento(
			GenericRequestDTO<?> request) throws GenericException {
				log.info("Metodo core deleteAdmiTipoMovimiento iniciado");
		Boolean response = false;
		ObjectIdDTO requestDTO = null;
		try {
			requestDTO = CommonFormat.objectMapping(request.getPayload(), ObjectIdDTO.class);
			response = iAdmiTipoMovimientoDAO.deleteAdmiTipoMovimiento(requestDTO);
		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("core-account-movement", e);
			throw e;
		}
		return response;
	}


	/**
	 * Método para retornar lista paginada con filtros de AdmiTipoMovimiento
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public Page<AdmiTipoMovimientoResDTO> pageListByAdmiTipoMovimiento(
			GenericRequestDTO<?> request) throws GenericException {
				log.info("Metodo core pageListByAdmiTipoMovimiento iniciado");
		Page<AdmiTipoMovimientoResDTO> response = null;
		PageDTO<AdmiTipoMovimientoReqDTO> requestDTO = null;
		AdmiTipoMovimientoReqDTO requestDTOActive = null;
		try {

			requestDTO = CommonFormat.pageMapping(request.getPayload(),
			AdmiTipoMovimientoReqDTO.class);
			requestDTOActive = requestDTO.getTabla();
			requestDTO.setTabla(requestDTOActive);
			response = iAdmiTipoMovimientoDAO.pageListByAdmiTipoMovimiento(requestDTO);
		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("core-account-movement", e);
			throw e;
		}

		return response;
	}

}
