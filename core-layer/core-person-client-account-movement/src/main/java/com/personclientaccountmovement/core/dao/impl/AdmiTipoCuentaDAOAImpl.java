package com.personclientaccountmovement.core.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.personclientaccountmovement.repository.postgres.handler.IAdmiTipoCuentaHandler;
import com.personclientaccountmovement.core.dao.IAdmiTipoCuentaDAO;
import com.personclientaccountmovement.dto.AdmiTipoCuentaReqDTO;
import com.personclientaccountmovement.dto.AdmiTipoCuentaResDTO;
import com.personclientaccountmovement.dto.ObjectIdDTO;
import com.personclientaccountmovement.dto.PageDTO;
import com.personclientaccountmovement.exception.GenericException;

/**
* DAO Impl AdmiTipoCuenta
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Service
public class AdmiTipoCuentaDAOAImpl implements IAdmiTipoCuentaDAO {

	Logger log = LogManager.getLogger(this.getClass());

	@Autowired
	private IAdmiTipoCuentaHandler iAdmiTipoCuentaHandler;

	/**
	 * Método que guarda un AdmiTipoCuenta
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public AdmiTipoCuentaResDTO saveAdmiTipoCuenta(
			AdmiTipoCuentaReqDTO requestDTO) throws GenericException {
		return iAdmiTipoCuentaHandler.saveAdmiTipoCuenta(requestDTO);
	}

	/**
	 * Método que actualiza un AdmiTipoCuenta
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public AdmiTipoCuentaResDTO updateAdmiTipoCuenta(
			AdmiTipoCuentaReqDTO requestDTO) throws GenericException {
		return iAdmiTipoCuentaHandler.updateAdmiTipoCuenta(requestDTO);
	}

	/**
	 * Método que elimina un AdmiTipoCuenta
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public Boolean deleteAdmiTipoCuenta(ObjectIdDTO requestDTO) throws GenericException {
		return iAdmiTipoCuentaHandler.deleteAdmiTipoCuenta(requestDTO);
	}

	/**
	 * Método que retorna la lista de AdmiTipoCuentas con filtros
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public List<AdmiTipoCuentaResDTO> listByAdmiTipoCuenta(
			AdmiTipoCuentaReqDTO requestDto) throws GenericException {
		return iAdmiTipoCuentaHandler.listByAdmiTipoCuenta(requestDto);
	}

	/**
	 * Método que retorna la paginación de una lista de AdmiTipoCuentas
	 * con filtros
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public Page<AdmiTipoCuentaResDTO> pageListByAdmiTipoCuenta(
			PageDTO<AdmiTipoCuentaReqDTO> requestDto) throws GenericException {
		return iAdmiTipoCuentaHandler.pageListByAdmiTipoCuenta(requestDto);
	}
	
}
