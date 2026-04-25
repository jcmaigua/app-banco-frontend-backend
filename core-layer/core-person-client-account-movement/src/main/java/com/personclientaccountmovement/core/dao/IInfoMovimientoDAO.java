package com.personclientaccountmovement.core.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.personclientaccountmovement.dto.InfoMovimientoReqDTO;
import com.personclientaccountmovement.dto.BusquedaGlobalDTO;
import com.personclientaccountmovement.dto.InfoMovimientoCuentaResDTO;
import com.personclientaccountmovement.dto.InfoMovimientoPageResDTO;
import com.personclientaccountmovement.dto.ObjectIdDTO;
import com.personclientaccountmovement.dto.PageDTO;
import com.personclientaccountmovement.dto.ReportePdfDTO;
import com.personclientaccountmovement.exception.GenericException;
import com.personclientaccountmovement.dto.InfoMovimientoReporteResDTO;
import com.personclientaccountmovement.dto.InfoMovimientoReporteReqDTO;

/**
* DAO InfoMovimiento
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Service
public interface IInfoMovimientoDAO {
	public InfoMovimientoCuentaResDTO saveInfoMovimiento(
			InfoMovimientoReqDTO request) throws GenericException;

	public InfoMovimientoCuentaResDTO updateInfoMovimiento(
			InfoMovimientoReqDTO request) throws GenericException;

	public Boolean deleteInfoMovimiento(ObjectIdDTO request) throws GenericException;

	public List<InfoMovimientoCuentaResDTO> listByInfoMovimiento(
			InfoMovimientoReqDTO request) throws GenericException;

	public List<InfoMovimientoReporteResDTO> listByInfoMovimientoReporte(
			InfoMovimientoReporteReqDTO request) throws GenericException;

	public Page<InfoMovimientoReporteResDTO> pageListByInfoMovimientoReporte(
			PageDTO<InfoMovimientoReporteReqDTO> request) throws GenericException;

	public ReportePdfDTO pageListByInfoMovimientoReportePdf(
			PageDTO<InfoMovimientoReporteReqDTO> request) throws GenericException;

	public Page<InfoMovimientoPageResDTO> pageListByInfoMovimiento(
			PageDTO<BusquedaGlobalDTO> request) throws GenericException;
			
}
