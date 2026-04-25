package com.personclientaccountmovement.core.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.personclientaccountmovement.core.dao.IInfoPersonaDAO;
import com.personclientaccountmovement.dto.InfoPersonaReqDTO;
import com.personclientaccountmovement.dto.InfoPersonaResDTO;
import com.personclientaccountmovement.dto.ObjectIdDTO;
import com.personclientaccountmovement.dto.PageDTO;
import com.personclientaccountmovement.dto.PersonaClienteUnifiedReqDTO;
import com.personclientaccountmovement.dto.PersonaClienteUnifiedResDTO;
import com.personclientaccountmovement.exception.GenericException;
import com.personclientaccountmovement.repository.postgres.handler.IInfoPersonaHandler;

/**
* DAO Impl InfoPersona
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Service
public class InfoPersonaDAOAImpl implements IInfoPersonaDAO {

	Logger log = LogManager.getLogger(this.getClass());

	@Autowired
	private IInfoPersonaHandler iInfoPersonaHandler;

	/**
	 * Método que guarda un InfoPersona
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public PersonaClienteUnifiedResDTO saveInfoPersona(
			PersonaClienteUnifiedReqDTO requestDTO) throws GenericException {
		return iInfoPersonaHandler.saveInfoPersona(requestDTO);
	}

	/**
	 * Método que actualiza un InfoPersona
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public PersonaClienteUnifiedResDTO updateInfoPersona(
			PersonaClienteUnifiedReqDTO requestDTO) throws GenericException {
		return iInfoPersonaHandler.updateInfoPersona(requestDTO);
	}

	/**
	 * Método que elimina un InfoPersona
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public Boolean deleteInfoPersona(ObjectIdDTO requestDTO) throws GenericException {
		return iInfoPersonaHandler.deleteInfoPersona(requestDTO);
	}

	/**
	 * Método que retorna la lista de InfoPersonas con filtros
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public List<InfoPersonaResDTO> listByInfoPersona(
			InfoPersonaReqDTO requestDto) throws GenericException {
		return iInfoPersonaHandler.listByInfoPersona(requestDto);
	}

	/**
	 * Método que retorna la paginación de una lista de InfoPersonas
	 * con filtros
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public Page<InfoPersonaResDTO> pageListByInfoPersona(
			PageDTO<InfoPersonaReqDTO> requestDto) throws GenericException {
		return iInfoPersonaHandler.pageListByInfoPersona(requestDto);
	}

	/**
	 * Operación retrieveInfoPersona.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public PersonaClienteUnifiedResDTO retrieveInfoPersona(ObjectIdDTO requestDTO) throws GenericException {
		return iInfoPersonaHandler.retrieveInfoPersona(requestDTO);
	}
	
}
