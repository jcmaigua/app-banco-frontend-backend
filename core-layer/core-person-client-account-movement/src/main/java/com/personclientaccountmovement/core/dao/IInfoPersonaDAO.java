package com.personclientaccountmovement.core.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.personclientaccountmovement.dto.InfoPersonaReqDTO;
import com.personclientaccountmovement.dto.InfoPersonaResDTO;
import com.personclientaccountmovement.dto.ObjectIdDTO;
import com.personclientaccountmovement.dto.PageDTO;
import com.personclientaccountmovement.dto.PersonaClienteUnifiedReqDTO;
import com.personclientaccountmovement.dto.PersonaClienteUnifiedResDTO;
import com.personclientaccountmovement.exception.GenericException;

/**
* DAO InfoPersona
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Service
public interface IInfoPersonaDAO {
	public PersonaClienteUnifiedResDTO saveInfoPersona(
			PersonaClienteUnifiedReqDTO request) throws GenericException;

	public PersonaClienteUnifiedResDTO updateInfoPersona(
			PersonaClienteUnifiedReqDTO request) throws GenericException;

	public Boolean deleteInfoPersona(ObjectIdDTO request) throws GenericException;

	public List<InfoPersonaResDTO> listByInfoPersona(
			InfoPersonaReqDTO request) throws GenericException;

	public Page<InfoPersonaResDTO> pageListByInfoPersona(
			PageDTO<InfoPersonaReqDTO> request) throws GenericException;

	PersonaClienteUnifiedResDTO retrieveInfoPersona(ObjectIdDTO request) throws GenericException;
}
