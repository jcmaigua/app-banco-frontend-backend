package com.personclientaccountmovement.repository.postgres.handler;

import java.util.List;

import org.springframework.data.domain.Page;

import com.personclientaccountmovement.dto.InfoPersonaReqDTO;
import com.personclientaccountmovement.dto.InfoPersonaResDTO;
import com.personclientaccountmovement.dto.ObjectIdDTO;
import com.personclientaccountmovement.dto.PageDTO;
import com.personclientaccountmovement.dto.PersonaClienteUnifiedReqDTO;
import com.personclientaccountmovement.dto.PersonaClienteUnifiedResDTO;
import com.personclientaccountmovement.exception.GenericException;

/**
* Contrato del manejador de InfoPersona en repositorio.
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
public interface IInfoPersonaHandler {

	PersonaClienteUnifiedResDTO saveInfoPersona(PersonaClienteUnifiedReqDTO request) throws GenericException;

	PersonaClienteUnifiedResDTO updateInfoPersona(PersonaClienteUnifiedReqDTO request) throws GenericException;

	Boolean deleteInfoPersona(ObjectIdDTO request) throws GenericException;

	List<InfoPersonaResDTO> listByInfoPersona(InfoPersonaReqDTO request) throws GenericException;

	Page<InfoPersonaResDTO> pageListByInfoPersona(PageDTO<InfoPersonaReqDTO> request) throws GenericException;

	PersonaClienteUnifiedResDTO retrieveInfoPersona(ObjectIdDTO request) throws GenericException;
}
