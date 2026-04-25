package com.personclientaccountmovement.core.handler.impl;

import java.util.List;
import org.springframework.data.domain.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.personclientaccountmovement.integration.dto.request.GenericRequestDTO;
import com.personclientaccountmovement.integration.util.CommonFormat;
import com.personclientaccountmovement.dto.InfoMovimientoReqDTO;
import com.personclientaccountmovement.dto.BusquedaGlobalDTO;
import com.personclientaccountmovement.dto.InfoMovimientoCuentaResDTO;
import com.personclientaccountmovement.dto.InfoMovimientoPageResDTO;
import com.personclientaccountmovement.dto.ObjectIdDTO;
import com.personclientaccountmovement.dto.PageDTO;
import com.personclientaccountmovement.dto.ReportePdfDTO;
import com.personclientaccountmovement.enums.EnumStatus;
import com.personclientaccountmovement.exception.GenericException;
import com.personclientaccountmovement.support.ExceptionLogSupport;
import com.personclientaccountmovement.dto.InfoMovimientoReporteResDTO;
import com.personclientaccountmovement.dto.InfoMovimientoReporteReqDTO;
import com.personclientaccountmovement.core.dao.IInfoMovimientoDAO;
import com.personclientaccountmovement.core.handler.IMovimientoHandler;

/**
* Handler implement de la entidad InfoMovimiento
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Service
public class MovimientoHandlerImpl implements IMovimientoHandler {

	Logger log = LogManager.getLogger(this.getClass());

	@Autowired
	private IInfoMovimientoDAO iInfoMovimientoDAO;

	/**
	 * Método para guardar un InfoMovimiento
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public InfoMovimientoCuentaResDTO saveInfoMovimiento(
			GenericRequestDTO<?> request) throws GenericException {
				log.info("Metodo core saveInfoMovimiento iniciado");
		InfoMovimientoCuentaResDTO response = null;
		InfoMovimientoReqDTO requestDTO = null;
		try {
			requestDTO = CommonFormat.objectMapping(request.getPayload(),
			InfoMovimientoReqDTO.class);
			response = iInfoMovimientoDAO.saveInfoMovimiento(requestDTO);
		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("core-account-movement", e);
			throw e;
		}
		return response;
	}

	/**
	 * Método para actualizar un InfoMovimiento
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public InfoMovimientoCuentaResDTO updateInfoMovimiento(
			GenericRequestDTO<?> request) throws GenericException {
				log.info("Metodo core updateInfoMovimiento iniciado");
		InfoMovimientoCuentaResDTO response = null;
		InfoMovimientoReqDTO requestDTO = null;
		try {
			requestDTO = CommonFormat.objectMapping(request.getPayload(),
			InfoMovimientoReqDTO.class);
			response = iInfoMovimientoDAO.updateInfoMovimiento(requestDTO);
		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("core-account-movement", e);
			throw e;
		}
		return response;
	}

	/**
	 * Método para retornar lista de InfoMovimiento
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public List<InfoMovimientoCuentaResDTO> listByInfoMovimiento(
			GenericRequestDTO<?> request) throws GenericException {
				log.info("Metodo core listByInfoMovimiento iniciado");
		List<InfoMovimientoCuentaResDTO> response = null;
		InfoMovimientoReqDTO requestDTO = null;
		try {
			requestDTO = CommonFormat.objectMapping(request.getPayload(),
					InfoMovimientoReqDTO.class);
		    requestDTO.setEstado(EnumStatus.ACTIVO.toString());
			response = iInfoMovimientoDAO.listByInfoMovimiento(requestDTO);
		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("core-account-movement", e);
			throw e;
		}
		return response;
	}

	/**
	 * Método para eliminar un InfoMovimiento
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public Boolean deleteInfoMovimiento(
			GenericRequestDTO<?> request) throws GenericException {
				log.info("Metodo core deleteInfoMovimiento iniciado");
		Boolean response = false;
		ObjectIdDTO requestDTO = null;
		try {
			requestDTO = CommonFormat.objectMapping(request.getPayload(), ObjectIdDTO.class);
			response = iInfoMovimientoDAO.deleteInfoMovimiento(requestDTO);
		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("core-account-movement", e);
			throw e;
		}
		return response;
	}


	/**
	 * Método para retornar lista paginada con filtros de InfoMovimiento
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public Page<InfoMovimientoPageResDTO> pageListByInfoMovimiento(
			GenericRequestDTO<?> request) throws GenericException {
				log.info("Metodo core pageListByInfoMovimiento iniciado");
		Page<InfoMovimientoPageResDTO> response = null;
		PageDTO<BusquedaGlobalDTO> requestDTO = null;
		try {

			requestDTO = CommonFormat.pageMapping(request.getPayload(),
			BusquedaGlobalDTO.class);
			response = iInfoMovimientoDAO.pageListByInfoMovimiento(requestDTO);
		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("core-account-movement", e);
			throw e;
		}
		return response;
	}

	/**
	 * Lista reportes de movimiento y agregar la respuesta con datos de persona cuando aplica.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public List<InfoMovimientoReporteResDTO> listByInfoMovimientoReporte(
			GenericRequestDTO<?> request) throws GenericException {
				log.info("Metodo core listByInfoMovimientoReporte iniciado");
		List<InfoMovimientoReporteResDTO> response = null;
		InfoMovimientoReporteReqDTO requestDTO = null;
		try {
			requestDTO = CommonFormat.objectMapping(request.getPayload(), InfoMovimientoReporteReqDTO.class);
			response = iInfoMovimientoDAO.listByInfoMovimientoReporte(requestDTO);
		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("core-account-movement", e);
			throw e;
		}
		return response;
	}

	/**
	 * Lista paginada de reportes de movimiento y agregar la respuesta con datos de persona cuando aplica.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public Page<InfoMovimientoReporteResDTO> pageListByInfoMovimientoReporte(
			GenericRequestDTO<?> request) throws GenericException {
				log.info("Metodo core listByInfoMovimientoReporte iniciado");
		Page<InfoMovimientoReporteResDTO> response = null;
		PageDTO<InfoMovimientoReporteReqDTO> requestDTO = null;
		try {

			requestDTO = CommonFormat.pageMapping(request.getPayload(),
			InfoMovimientoReporteReqDTO.class);
			response = iInfoMovimientoDAO.pageListByInfoMovimientoReporte(requestDTO);
		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("core-account-movement", e);
			throw e;
		}
		return response;
	}

	/**
	 * Lista paginada de reportes de movimiento y agregar la respuesta con datos de persona cuando aplica.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public ReportePdfDTO pageListByInfoMovimientoReportePdf(
			GenericRequestDTO<?> request) throws GenericException {
				log.info("Metodo core listByInfoMovimientoReporte iniciado");
		ReportePdfDTO response = null;
		PageDTO<InfoMovimientoReporteReqDTO> requestDTO = null;
		try {

			requestDTO = CommonFormat.pageMapping(request.getPayload(),
			InfoMovimientoReporteReqDTO.class);
			response = iInfoMovimientoDAO.pageListByInfoMovimientoReportePdf(requestDTO);
		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("core-account-movement", e);
			throw e;
		}
		return response;
	}
}
