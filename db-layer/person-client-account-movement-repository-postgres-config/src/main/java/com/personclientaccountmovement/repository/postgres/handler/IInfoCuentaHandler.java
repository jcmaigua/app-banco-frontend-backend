package com.personclientaccountmovement.repository.postgres.handler;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.personclientaccountmovement.dto.BusquedaGlobalDTO;
import com.personclientaccountmovement.dto.InfoCuentaPersonaUnifiedReqDTO;
import com.personclientaccountmovement.dto.InfoCuentaPersonaUnifiedResDTO;
import com.personclientaccountmovement.dto.ObjectIdDTO;
import com.personclientaccountmovement.dto.PageDTO;
import com.personclientaccountmovement.exception.GenericException;
import com.personclientaccountmovement.repository.postgres.entity.InfoCuenta;

/**
* Contrato del manejador de cuenta en persistencia: CRUD lógico, listados, paginación y validaciones por persona.
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Service
public interface IInfoCuentaHandler {
	public InfoCuentaPersonaUnifiedResDTO saveInfoCuenta(
			InfoCuentaPersonaUnifiedReqDTO request) throws GenericException;

	public InfoCuentaPersonaUnifiedResDTO updateInfoCuenta(
			InfoCuentaPersonaUnifiedReqDTO request) throws GenericException;

	public void updateInfoCuentaByMovimiento(
			InfoCuenta request);

	public Boolean deleteInfoCuenta(
			ObjectIdDTO request) throws GenericException;

   public Boolean validarInfoCuentaByPersona(
			ObjectIdDTO request) throws GenericException;

	public List<InfoCuentaPersonaUnifiedResDTO> listByInfoCuenta(
			InfoCuentaPersonaUnifiedReqDTO request) throws GenericException;

	public Page<InfoCuentaPersonaUnifiedResDTO> pageListByInfoCuenta1(
			PageDTO<InfoCuentaPersonaUnifiedReqDTO> request) throws GenericException;

	public Page<InfoCuentaPersonaUnifiedResDTO> pageListByInfoCuenta(
			PageDTO<BusquedaGlobalDTO> request) throws GenericException;
}
