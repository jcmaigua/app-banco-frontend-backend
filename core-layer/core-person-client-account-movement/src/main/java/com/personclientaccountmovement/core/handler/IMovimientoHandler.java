package com.personclientaccountmovement.core.handler;

import java.util.List;
import org.springframework.data.domain.Page;
import com.personclientaccountmovement.integration.dto.request.GenericRequestDTO;

import com.personclientaccountmovement.dto.InfoMovimientoCuentaResDTO;
import com.personclientaccountmovement.dto.InfoMovimientoPageResDTO;
import com.personclientaccountmovement.exception.GenericException;
import com.personclientaccountmovement.dto.InfoMovimientoReporteResDTO;
import com.personclientaccountmovement.dto.ReportePdfDTO;

/**
* Contrato del manejador de movimiento en el núcleo (orquestación y enriquecimiento de reportes).
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
public interface IMovimientoHandler {

		
	public InfoMovimientoCuentaResDTO saveInfoMovimiento(
			GenericRequestDTO<?> request) throws GenericException;

	public InfoMovimientoCuentaResDTO updateInfoMovimiento(
			GenericRequestDTO<?> request) throws GenericException;

	public Boolean deleteInfoMovimiento(
			GenericRequestDTO<?> request) throws GenericException;

	public Page<InfoMovimientoPageResDTO> pageListByInfoMovimiento(
			GenericRequestDTO<?> request) throws GenericException;

	public List<InfoMovimientoCuentaResDTO> listByInfoMovimiento(
			GenericRequestDTO<?> request) throws GenericException;

	public List<InfoMovimientoReporteResDTO> listByInfoMovimientoReporte(
			GenericRequestDTO<?> request) throws GenericException;

	public Page<InfoMovimientoReporteResDTO> pageListByInfoMovimientoReporte(
			GenericRequestDTO<?> request) throws GenericException;

	public ReportePdfDTO pageListByInfoMovimientoReportePdf(
			GenericRequestDTO<?> request) throws GenericException;

}
