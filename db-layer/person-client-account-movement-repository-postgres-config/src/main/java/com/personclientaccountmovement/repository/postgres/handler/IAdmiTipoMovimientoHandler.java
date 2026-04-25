package com.personclientaccountmovement.repository.postgres.handler;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.personclientaccountmovement.dto.AdmiTipoMovimientoReqDTO;
import com.personclientaccountmovement.dto.AdmiTipoMovimientoResDTO;
import com.personclientaccountmovement.dto.ObjectIdDTO;
import com.personclientaccountmovement.dto.PageDTO;
import com.personclientaccountmovement.exception.GenericException;

/**
* Contrato del manejador de tipo de movimiento en persistencia: alta, actualización, baja lógica, listados y paginación.
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Service
public interface IAdmiTipoMovimientoHandler {
	public AdmiTipoMovimientoResDTO saveAdmiTipoMovimiento(
			AdmiTipoMovimientoReqDTO request) throws GenericException;

	public AdmiTipoMovimientoResDTO updateAdmiTipoMovimiento(
			AdmiTipoMovimientoReqDTO request) throws GenericException;

	public Boolean deleteAdmiTipoMovimiento(
			ObjectIdDTO request) throws GenericException;

	public List<AdmiTipoMovimientoResDTO> listByAdmiTipoMovimiento(
			AdmiTipoMovimientoReqDTO request) throws GenericException;

	public Page<AdmiTipoMovimientoResDTO> pageListByAdmiTipoMovimiento(
			PageDTO<AdmiTipoMovimientoReqDTO> request) throws GenericException;
}
