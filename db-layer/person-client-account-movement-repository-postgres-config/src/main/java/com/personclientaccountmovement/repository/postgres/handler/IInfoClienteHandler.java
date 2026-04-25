package com.personclientaccountmovement.repository.postgres.handler;

import java.util.List;

import org.springframework.data.domain.Page;

import com.personclientaccountmovement.dto.BusquedaGlobalDTO;
import com.personclientaccountmovement.dto.InfoClienteReqDTO;
import com.personclientaccountmovement.dto.InfoClienteResDTO;
import com.personclientaccountmovement.dto.ObjectIdDTO;
import com.personclientaccountmovement.dto.PageDTO;
import com.personclientaccountmovement.exception.GenericException;
import com.personclientaccountmovement.dto.PersonaClienteUnifiedReqDTO;
import com.personclientaccountmovement.dto.PersonaClienteUnifiedResDTO;

/**
* Contrato del manejador de InfoCliente en repositorio.
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
public interface IInfoClienteHandler {

	InfoClienteResDTO saveInfoCliente(InfoClienteReqDTO request) throws GenericException;

	InfoClienteResDTO updateInfoCliente(InfoClienteReqDTO request) throws GenericException;

	PersonaClienteUnifiedResDTO retrieveInfoCliente(ObjectIdDTO request) throws GenericException;

	Boolean deleteInfoCliente(ObjectIdDTO request) throws GenericException;

	Boolean deleteInfoClienteByPersonaId(ObjectIdDTO request) throws GenericException;

	List<PersonaClienteUnifiedResDTO> listByInfoCliente(PersonaClienteUnifiedReqDTO request) throws GenericException;

    List<InfoClienteResDTO> listByInfoClienteOrigin(InfoClienteReqDTO request) throws GenericException;

	Page<PersonaClienteUnifiedResDTO> pageListByInfoCliente(PageDTO<BusquedaGlobalDTO> request) throws GenericException;
}
