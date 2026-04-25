package com.personclientaccountmovement.core.handler;

import java.util.List;

import org.springframework.data.domain.Page;

import com.personclientaccountmovement.dto.InfoClienteReqDTO;
import com.personclientaccountmovement.dto.InfoClienteResDTO;
import com.personclientaccountmovement.dto.PersonaClienteUnifiedResDTO;
import com.personclientaccountmovement.exception.GenericException;
import com.personclientaccountmovement.integration.dto.request.GenericRequestDTO;

/**
* Contrato del manejador de cliente en el núcleo.
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
public interface IClienteHandler {

	InfoClienteResDTO saveInfoCliente(GenericRequestDTO<?> request) throws GenericException;

	InfoClienteResDTO updateInfoCliente(GenericRequestDTO<?> request) throws GenericException;

	Boolean deleteInfoCliente(GenericRequestDTO<?> request) throws GenericException;

	List<PersonaClienteUnifiedResDTO> listByInfoCliente(GenericRequestDTO<?> request) throws GenericException;

	PersonaClienteUnifiedResDTO retrieveInfoCliente(GenericRequestDTO<?> request) throws GenericException;
	
	Page<PersonaClienteUnifiedResDTO> pageListByInfoCliente(GenericRequestDTO<?> request) throws GenericException;
}
