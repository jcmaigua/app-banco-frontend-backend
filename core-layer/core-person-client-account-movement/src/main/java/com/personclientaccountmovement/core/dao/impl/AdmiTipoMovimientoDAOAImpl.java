package com.personclientaccountmovement.core.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.personclientaccountmovement.repository.postgres.handler.IAdmiTipoMovimientoHandler;

import com.personclientaccountmovement.core.dao.IAdmiTipoMovimientoDAO;
import com.personclientaccountmovement.dto.AdmiTipoMovimientoReqDTO;
import com.personclientaccountmovement.dto.AdmiTipoMovimientoResDTO;
import com.personclientaccountmovement.dto.ObjectIdDTO;
import com.personclientaccountmovement.dto.PageDTO;
import com.personclientaccountmovement.exception.GenericException;

/**
* DAO Impl AdmiTipoMovimiento
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Service
public class AdmiTipoMovimientoDAOAImpl implements IAdmiTipoMovimientoDAO {

	Logger log = LogManager.getLogger(this.getClass());

	@Autowired
	private IAdmiTipoMovimientoHandler iAdmiTipoMovimientoHandler;

	/**
	 * Método que guarda un AdmiTipoMovimiento
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public AdmiTipoMovimientoResDTO saveAdmiTipoMovimiento(
			AdmiTipoMovimientoReqDTO requestDTO) throws GenericException {
		return iAdmiTipoMovimientoHandler.saveAdmiTipoMovimiento(requestDTO);
	}

	/**
	 * Método que actualiza un AdmiTipoMovimiento
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public AdmiTipoMovimientoResDTO updateAdmiTipoMovimiento(
			AdmiTipoMovimientoReqDTO requestDTO) throws GenericException {
		return iAdmiTipoMovimientoHandler.updateAdmiTipoMovimiento(requestDTO);
	}

	/**
	 * Método que elimina un AdmiTipoMovimiento
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public Boolean deleteAdmiTipoMovimiento(ObjectIdDTO requestDTO) throws GenericException {
		return iAdmiTipoMovimientoHandler.deleteAdmiTipoMovimiento(requestDTO);
	}

	/**
	 * Método que retorna la lista de AdmiTipoMovimientos con filtros
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public List<AdmiTipoMovimientoResDTO> listByAdmiTipoMovimiento(
			AdmiTipoMovimientoReqDTO requestDto) throws GenericException {
		return iAdmiTipoMovimientoHandler.listByAdmiTipoMovimiento(requestDto);
	}

	/**
	 * Método que retorna la paginación de una lista de AdmiTipoMovimientos
	 * con filtros
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public Page<AdmiTipoMovimientoResDTO> pageListByAdmiTipoMovimiento(
			PageDTO<AdmiTipoMovimientoReqDTO> requestDto) throws GenericException {
		return iAdmiTipoMovimientoHandler.pageListByAdmiTipoMovimiento(requestDto);
	}
	
}
