package com.personclientaccountmovement.core.handler;

import java.util.List;
import org.springframework.data.domain.Page;
import com.personclientaccountmovement.integration.dto.request.GenericRequestDTO;

import com.personclientaccountmovement.dto.AdmiTipoMovimientoResDTO;
import com.personclientaccountmovement.exception.GenericException;

/**
* Contrato del manejador de tipo de movimiento administrado en el núcleo.
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
public interface ITipoMovimientoHandler {

		
	public AdmiTipoMovimientoResDTO saveAdmiTipoMovimiento(
			GenericRequestDTO<?> request) throws GenericException;

	public AdmiTipoMovimientoResDTO updateAdmiTipoMovimiento(
			GenericRequestDTO<?> request) throws GenericException;

	public Boolean deleteAdmiTipoMovimiento(
			GenericRequestDTO<?> request) throws GenericException;

	public Page<AdmiTipoMovimientoResDTO> pageListByAdmiTipoMovimiento(
			GenericRequestDTO<?> request) throws GenericException;

	public List<AdmiTipoMovimientoResDTO> listByAdmiTipoMovimiento(
			GenericRequestDTO<?> request) throws GenericException;


}
