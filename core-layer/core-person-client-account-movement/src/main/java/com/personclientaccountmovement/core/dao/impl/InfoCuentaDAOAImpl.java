package com.personclientaccountmovement.core.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.personclientaccountmovement.repository.postgres.handler.IInfoCuentaHandler;

import com.personclientaccountmovement.core.dao.IInfoCuentaDAO;
import com.personclientaccountmovement.dto.BusquedaGlobalDTO;
import com.personclientaccountmovement.dto.InfoCuentaPersonaUnifiedReqDTO;
import com.personclientaccountmovement.dto.InfoCuentaPersonaUnifiedResDTO;
import com.personclientaccountmovement.dto.ObjectIdDTO;
import com.personclientaccountmovement.dto.PageDTO;
import com.personclientaccountmovement.exception.GenericException;

/**
* DAO Impl InfoCuenta
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Service
public class InfoCuentaDAOAImpl implements IInfoCuentaDAO {

	Logger log = LogManager.getLogger(this.getClass());

	@Autowired
	private IInfoCuentaHandler iInfoCuentaHandler;

	/**
	 * Método que guarda un InfoCuenta
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public InfoCuentaPersonaUnifiedResDTO saveInfoCuenta(
			InfoCuentaPersonaUnifiedReqDTO requestDTO) throws GenericException {
		return iInfoCuentaHandler.saveInfoCuenta(requestDTO);
	}

	/**
	 * Método que actualiza un InfoCuenta
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public InfoCuentaPersonaUnifiedResDTO updateInfoCuenta(
			InfoCuentaPersonaUnifiedReqDTO requestDTO) throws GenericException {
		return iInfoCuentaHandler.updateInfoCuenta(requestDTO);
	}

	/**
	 * Método que elimina un InfoCuenta
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public Boolean deleteInfoCuenta(ObjectIdDTO requestDTO) throws GenericException {
		return iInfoCuentaHandler.deleteInfoCuenta(requestDTO);
	}

	/**
	 * Método que retorna la lista de InfoCuentas con filtros
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public List<InfoCuentaPersonaUnifiedResDTO> listByInfoCuenta(
			InfoCuentaPersonaUnifiedReqDTO requestDto) throws GenericException {
		return iInfoCuentaHandler.listByInfoCuenta(requestDto);
	}

	/**
	 * Método que retorna la paginación de una lista de InfoCuentas
	 * con filtros
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public Page<InfoCuentaPersonaUnifiedResDTO> pageListByInfoCuenta(
			PageDTO<BusquedaGlobalDTO> requestDto) throws GenericException {
		return iInfoCuentaHandler.pageListByInfoCuenta(requestDto);
	}
	
	/**
	 * Valida reglas de negocio para la operación solicitada.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public Boolean validarInfoCuentaByPersona(ObjectIdDTO request) throws GenericException {
		return iInfoCuentaHandler.validarInfoCuentaByPersona(request);
	}
}
