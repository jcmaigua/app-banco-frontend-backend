package com.personclientaccountmovement.core.handler;

import java.util.List;

import org.springframework.data.domain.Page;

import com.personclientaccountmovement.dto.InfoCuentaPersonaUnifiedResDTO;
import com.personclientaccountmovement.exception.GenericException;
import com.personclientaccountmovement.integration.dto.request.GenericRequestDTO;

/**
* Contrato del manejador de cuenta en el núcleo (orquestación hacia persistencia).
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
public interface ICuentaHandler {

	public InfoCuentaPersonaUnifiedResDTO saveInfoCuenta(GenericRequestDTO<?> request) throws GenericException;

	public InfoCuentaPersonaUnifiedResDTO updateInfoCuenta(GenericRequestDTO<?> request) throws GenericException;

	public Page<InfoCuentaPersonaUnifiedResDTO> pageListByInfoCuenta(GenericRequestDTO<?> request) throws GenericException;

	public List<InfoCuentaPersonaUnifiedResDTO> listByInfoCuenta(GenericRequestDTO<?> request) throws GenericException;

	public Boolean deleteInfoCuenta(GenericRequestDTO<?> request) throws GenericException;

	public Boolean validarInfoCuentaByPersona(GenericRequestDTO<?> request) throws GenericException;
}
