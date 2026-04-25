package com.personclientaccountmovement.core.handler;

import java.util.List;
import org.springframework.data.domain.Page;
import com.personclientaccountmovement.integration.dto.request.GenericRequestDTO;

import com.personclientaccountmovement.dto.InfoPersonaResDTO;
import com.personclientaccountmovement.dto.ObjectIdDTO;
import com.personclientaccountmovement.dto.PersonaClienteUnifiedResDTO;
import com.personclientaccountmovement.exception.GenericException;

/**
* Contrato del manejador de persona en el núcleo (orquestación hacia persistencia).
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
public interface IPersonaHandler {

		
	public PersonaClienteUnifiedResDTO saveInfoPersona(
			GenericRequestDTO<?> request) throws GenericException;

	public PersonaClienteUnifiedResDTO updateInfoPersona(
			GenericRequestDTO<?> request) throws GenericException;

	public Boolean deleteInfoPersona(
			GenericRequestDTO<?> request) throws GenericException;

	public Page<InfoPersonaResDTO> pageListByInfoPersona(
			GenericRequestDTO<?> request) throws GenericException;

	public List<InfoPersonaResDTO> listByInfoPersona(
			GenericRequestDTO<?> request) throws GenericException;

	PersonaClienteUnifiedResDTO retrieveInfoPersona(GenericRequestDTO<?> request) throws GenericException;

}
