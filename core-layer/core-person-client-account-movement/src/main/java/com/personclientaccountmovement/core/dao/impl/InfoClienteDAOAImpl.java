package com.personclientaccountmovement.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.personclientaccountmovement.repository.postgres.handler.IInfoClienteHandler;
import com.personclientaccountmovement.dto.PersonaClienteUnifiedReqDTO;
import com.personclientaccountmovement.dto.PersonaClienteUnifiedResDTO;
import com.personclientaccountmovement.core.dao.IInfoClienteDAO;
import com.personclientaccountmovement.dto.BusquedaGlobalDTO;
import com.personclientaccountmovement.dto.InfoClienteReqDTO;
import com.personclientaccountmovement.dto.InfoClienteResDTO;
import com.personclientaccountmovement.dto.ObjectIdDTO;
import com.personclientaccountmovement.dto.PageDTO;
import com.personclientaccountmovement.exception.GenericException;

/**
* Adaptador de acceso a datos de InfoCliente hacia repositorio.
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Service
public class InfoClienteDAOAImpl implements IInfoClienteDAO {

	@Autowired
	private IInfoClienteHandler iInfoClienteHandler;

	/**
	 * Persiste el registro indicado en la solicitud.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public InfoClienteResDTO saveInfoCliente(InfoClienteReqDTO requestDTO) throws GenericException {
		return iInfoClienteHandler.saveInfoCliente(requestDTO);
	}

	/**
	 * Actualiza el registro indicado en la solicitud.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public InfoClienteResDTO updateInfoCliente(InfoClienteReqDTO requestDTO) throws GenericException {
		return iInfoClienteHandler.updateInfoCliente(requestDTO);
	}

	/**
	 * Realiza baja lógica u operación de borrado según reglas de negocio.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public Boolean deleteInfoCliente(ObjectIdDTO requestDTO) throws GenericException {
		return iInfoClienteHandler.deleteInfoCliente(requestDTO);
	}

	/**
	 * Operación retrieveInfoCliente.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public PersonaClienteUnifiedResDTO retrieveInfoCliente(ObjectIdDTO requestDTO) throws GenericException {
		return iInfoClienteHandler.retrieveInfoCliente(requestDTO);
	}

	/**
	 * Consulta listados filtrados según criterios de la solicitud.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public List<PersonaClienteUnifiedResDTO> listByInfoCliente(PersonaClienteUnifiedReqDTO requestDto) throws GenericException {
		return iInfoClienteHandler.listByInfoCliente(requestDto);
	}

	/**
	 * Consulta paginada con filtros y orden aplicados.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public Page<PersonaClienteUnifiedResDTO> pageListByInfoCliente(PageDTO<BusquedaGlobalDTO> requestDto)
			throws GenericException {
		return iInfoClienteHandler.pageListByInfoCliente(requestDto);
	}
}
