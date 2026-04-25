package com.personclientaccountmovement.core.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.personclientaccountmovement.repository.postgres.handler.IInfoMovimientoHandler;

import com.personclientaccountmovement.core.dao.IInfoMovimientoDAO;
import com.personclientaccountmovement.dto.InfoMovimientoReqDTO;
import com.personclientaccountmovement.dto.BusquedaGlobalDTO;
import com.personclientaccountmovement.dto.InfoMovimientoCuentaResDTO;
import com.personclientaccountmovement.dto.InfoMovimientoPageResDTO;
import com.personclientaccountmovement.dto.ObjectIdDTO;
import com.personclientaccountmovement.dto.PageDTO;
import com.personclientaccountmovement.dto.ReportePdfDTO;
import com.personclientaccountmovement.exception.GenericException;
import com.personclientaccountmovement.dto.InfoMovimientoReporteResDTO;
import com.personclientaccountmovement.dto.InfoMovimientoReporteReqDTO;

/**
* DAO Impl InfoMovimiento
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Service
public class InfoMovimientoDAOAImpl implements IInfoMovimientoDAO {

	Logger log = LogManager.getLogger(this.getClass());

	@Autowired
	private IInfoMovimientoHandler iInfoMovimientoHandler;

	/**
	 * Método que guarda un InfoMovimiento
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public InfoMovimientoCuentaResDTO saveInfoMovimiento(
			InfoMovimientoReqDTO requestDTO) throws GenericException {
		return iInfoMovimientoHandler.saveInfoMovimiento(requestDTO);
	}

	/**
	 * Método que actualiza un InfoMovimiento
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public InfoMovimientoCuentaResDTO updateInfoMovimiento(
			InfoMovimientoReqDTO requestDTO) throws GenericException {
		return iInfoMovimientoHandler.updateInfoMovimiento(requestDTO);
	}

	/**
	 * Método que elimina un InfoMovimiento
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public Boolean deleteInfoMovimiento(ObjectIdDTO requestDTO) throws GenericException {
		return iInfoMovimientoHandler.deleteInfoMovimiento(requestDTO);
	}

	/**
	 * Método que retorna la lista de InfoMovimientos con filtros
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public List<InfoMovimientoCuentaResDTO> listByInfoMovimiento(
			InfoMovimientoReqDTO requestDto) throws GenericException {
		return iInfoMovimientoHandler.listByInfoMovimiento(requestDto);
	}

	/**
	 * Método que retorna la paginación de una lista de InfoMovimientos
	 * con filtros
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public Page<InfoMovimientoPageResDTO> pageListByInfoMovimiento(
			PageDTO<BusquedaGlobalDTO> requestDto) throws GenericException {
		return iInfoMovimientoHandler.pageListByInfoMovimiento(requestDto);
	}
	

	/**
	 * Consulta listados filtrados según criterios de la solicitud.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public List<InfoMovimientoReporteResDTO> listByInfoMovimientoReporte(
			InfoMovimientoReporteReqDTO requestDto) throws GenericException {
		return iInfoMovimientoHandler.listByInfoMovimientoReporte(requestDto);
	}

	/**
	 * Consulta listados paginada filtrados según criterios de la solicitud.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public Page<InfoMovimientoReporteResDTO> pageListByInfoMovimientoReporte(
			PageDTO<InfoMovimientoReporteReqDTO> requestDto) throws GenericException {
		return iInfoMovimientoHandler.pageListByInfoMovimientoReporte(requestDto);
	}

	/**
	 * Consulta listados paginada filtrados según criterios de la solicitud.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public ReportePdfDTO pageListByInfoMovimientoReportePdf(
			PageDTO<InfoMovimientoReporteReqDTO> requestDto) throws GenericException {
		return iInfoMovimientoHandler.pageListByInfoMovimientoReportePdf(requestDto);
	}
}
