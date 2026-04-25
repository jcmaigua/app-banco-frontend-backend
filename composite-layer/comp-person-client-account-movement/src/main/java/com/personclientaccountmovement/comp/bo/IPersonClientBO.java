package com.personclientaccountmovement.comp.bo;

import com.personclientaccountmovement.exception.GenericException;

/**
* Contrato de negocio compuesto PersonClient.
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
public interface IPersonClientBO {

	// InfoPersona
	public <T, R> R saveInfoPersona(T data) throws GenericException;

	public <T, R> R updateInfoPersona(T data) throws GenericException;

	public <T, R> R deleteInfoPersona(T data) throws GenericException;

	public <T, R> R listByInfoPersona(T data) throws GenericException;

	public <T, R> R pageListByInfoPersona(T data) throws GenericException;

	// InfoCliente
	public <T, R> R saveInfoCliente(T data) throws GenericException;

	public <T, R> R updateInfoCliente(T data) throws GenericException;

	public <T, R> R deleteInfoCliente(T data) throws GenericException;

	public <T, R> R retrieveInfoCliente(T data) throws GenericException;

	public <T, R> R listByInfoCliente(T data) throws GenericException;

	public <T, R> R pageListByInfoCliente(T data) throws GenericException;

	// AdmiTipoMovimiento
	public <T, R> R saveAdmiTipoMovimiento(T data) throws GenericException;

	public <T, R> R updateAdmiTipoMovimiento(T data) throws GenericException;

	public <T, R> R deleteAdmiTipoMovimiento(T data) throws GenericException;

	public <T, R> R listByAdmiTipoMovimiento(T data) throws GenericException;

	public <T, R> R pageListByAdmiTipoMovimiento(T data) throws GenericException;

	// AdmiTipoCuenta
	public <T, R> R saveAdmiTipoCuenta(T data) throws GenericException;

	public <T, R> R updateAdmiTipoCuenta(T data) throws GenericException;

	public <T, R> R deleteAdmiTipoCuenta(T data) throws GenericException;

	public <T, R> R listByAdmiTipoCuenta(T data) throws GenericException;

	public <T, R> R pageListByAdmiTipoCuenta(T data) throws GenericException;

	// InfoMovimiento
	public <T, R> R saveInfoMovimiento(T data) throws GenericException;

	public <T, R> R updateInfoMovimiento(T data) throws GenericException;

	public <T, R> R deleteInfoMovimiento(T data) throws GenericException;

	public <T, R> R listByInfoMovimiento(T data) throws GenericException;

	public <T, R> R pageListByInfoMovimiento(T data) throws GenericException;

	public <T, R> R listByInfoMovimientoReporte(T data) throws GenericException;

	public <T, R> R pageListByInfoMovimientoReporte(T data) throws GenericException;

	public <T, R> R pageListByInfoMovimientoReportePdf(T data) throws GenericException;

	// InfoCuenta
	public <T, R> R saveInfoCuenta(T data) throws GenericException;

	public <T, R> R updateInfoCuenta(T data) throws GenericException;

	public <T, R> R deleteInfoCuenta(T data) throws GenericException;

	public <T, R> R listByInfoCuenta(T data) throws GenericException;

	public <T, R> R pageListByInfoCuenta(T data) throws GenericException;

	public <T, R> R validarInfoCuentaByPersona(T data) throws GenericException;

}
