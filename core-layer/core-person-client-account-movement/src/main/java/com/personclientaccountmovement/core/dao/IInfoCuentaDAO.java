package com.personclientaccountmovement.core.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.personclientaccountmovement.dto.BusquedaGlobalDTO;
import com.personclientaccountmovement.dto.InfoCuentaPersonaUnifiedReqDTO;
import com.personclientaccountmovement.dto.InfoCuentaPersonaUnifiedResDTO;
import com.personclientaccountmovement.dto.ObjectIdDTO;
import com.personclientaccountmovement.dto.PageDTO;
import com.personclientaccountmovement.exception.GenericException;

/**
* DAO InfoCuenta
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Service
public interface IInfoCuentaDAO {
	public InfoCuentaPersonaUnifiedResDTO saveInfoCuenta(
			InfoCuentaPersonaUnifiedReqDTO request) throws GenericException;

	public InfoCuentaPersonaUnifiedResDTO updateInfoCuenta(
			InfoCuentaPersonaUnifiedReqDTO request) throws GenericException;

	public Boolean deleteInfoCuenta(ObjectIdDTO request) throws GenericException;

	public List<InfoCuentaPersonaUnifiedResDTO> listByInfoCuenta(
			InfoCuentaPersonaUnifiedReqDTO request) throws GenericException;

	public Page<InfoCuentaPersonaUnifiedResDTO> pageListByInfoCuenta(
			PageDTO<BusquedaGlobalDTO> request) throws GenericException;

	public Boolean validarInfoCuentaByPersona(ObjectIdDTO request) throws GenericException;
}
