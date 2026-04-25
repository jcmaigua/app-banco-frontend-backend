package com.personclientaccountmovement.core.handler;

import java.util.List;
import org.springframework.data.domain.Page;
import com.personclientaccountmovement.integration.dto.request.GenericRequestDTO;

import com.personclientaccountmovement.dto.AdmiTipoCuentaResDTO;
import com.personclientaccountmovement.exception.GenericException;

/**
* Contrato del manejador de tipo de cuenta administrado en el núcleo.
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
public interface ITipoCuentaHandler {

		
	public AdmiTipoCuentaResDTO saveAdmiTipoCuenta(
			GenericRequestDTO<?> request) throws GenericException;

	public AdmiTipoCuentaResDTO updateAdmiTipoCuenta(
			GenericRequestDTO<?> request) throws GenericException;

	public Boolean deleteAdmiTipoCuenta(
			GenericRequestDTO<?> request) throws GenericException;

	public Page<AdmiTipoCuentaResDTO> pageListByAdmiTipoCuenta(
			GenericRequestDTO<?> request) throws GenericException;

	public List<AdmiTipoCuentaResDTO> listByAdmiTipoCuenta(
			GenericRequestDTO<?> request) throws GenericException;

}
