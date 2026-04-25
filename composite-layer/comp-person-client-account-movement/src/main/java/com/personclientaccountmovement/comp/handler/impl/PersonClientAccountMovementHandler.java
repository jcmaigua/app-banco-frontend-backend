package com.personclientaccountmovement.comp.handler.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.personclientaccountmovement.comp.cons.CompPersonClientAccountMovementConstants;
import com.personclientaccountmovement.cons.PersonClientAccountMovementConfigConstants;
import com.personclientaccountmovement.comp.bo.IPersonClientBO;
import com.personclientaccountmovement.exception.GenericException;
import com.personclientaccountmovement.integration.interfaces.IGenericProducer;
import com.personclientaccountmovement.integration.model.GenericProducerModel;
import com.personclientaccountmovement.integration.util.CommonUtil;

/**
* Despachador de operaciones de PersonClient.
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Service(CompPersonClientAccountMovementConstants.PERSON_CLIENT_ACCOUNT_MOVEMENT_HANDLER)
@Primary
public class PersonClientAccountMovementHandler implements IGenericProducer{
	
	@Autowired
	private IPersonClientBO iPersonClientBO;

	//Se definen el Mapa que hace referencia a los metodos internos de la clase
	Map<String, String> methodMap = Map.ofEntries(
			//personas
			Map.entry(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_SAVE_INFO_PERSONA_METHOD, PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_SAVE_INFO_PERSONA_METHOD),
			Map.entry(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_UPDATE_INFO_PERSONA_METHOD, PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_UPDATE_INFO_PERSONA_METHOD),
			Map.entry(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_ELIMINADO_INFO_PERSONA_METHOD, PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_ELIMINADO_INFO_PERSONA_METHOD),
			Map.entry(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_LIST_BY_INFO_PERSONA_METHOD, PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_LIST_BY_INFO_PERSONA_METHOD),
			Map.entry(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_PAGE_LIST_BY_INFO_PERSONA_METHOD, PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_PAGE_LIST_BY_INFO_PERSONA_METHOD),
			
			//clientes
			Map.entry(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_SAVE_INFO_CLIENTE_METHOD, PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_SAVE_INFO_CLIENTE_METHOD),
			Map.entry(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_UPDATE_INFO_CLIENTE_METHOD, PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_UPDATE_INFO_CLIENTE_METHOD),
			Map.entry(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_ELIMINADO_INFO_CLIENTE_METHOD, PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_ELIMINADO_INFO_CLIENTE_METHOD),
			Map.entry(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_LIST_BY_INFO_CLIENTE_METHOD, PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_LIST_BY_INFO_CLIENTE_METHOD),
			Map.entry(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_PAGE_LIST_BY_INFO_CLIENTE_METHOD, PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_PAGE_LIST_BY_INFO_CLIENTE_METHOD),
			Map.entry(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_RETRIEVE_INFO_CLIENTE_METHOD, PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_RETRIEVE_INFO_CLIENTE_METHOD),

			//admitypomovimiento
			Map.entry(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_SAVE_ADMI_TIPO_MOVIMIENTO_METHOD, PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_SAVE_ADMI_TIPO_MOVIMIENTO_METHOD),
			Map.entry(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_UPDATE_ADMI_TIPO_MOVIMIENTO_METHOD, PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_UPDATE_ADMI_TIPO_MOVIMIENTO_METHOD),
			Map.entry(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_ELIMINADO_ADMI_TIPO_MOVIMIENTO_METHOD, PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_ELIMINADO_ADMI_TIPO_MOVIMIENTO_METHOD),
			Map.entry(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_LIST_BY_ADMI_TIPO_MOVIMIENTO_METHOD, PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_LIST_BY_ADMI_TIPO_MOVIMIENTO_METHOD),
			Map.entry(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_PAGE_LIST_BY_ADMI_TIPO_MOVIMIENTO_METHOD, PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_PAGE_LIST_BY_ADMI_TIPO_MOVIMIENTO_METHOD),
			
			//admitypocuenta
			Map.entry(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_SAVE_ADMI_TIPO_CUENTA_METHOD, PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_SAVE_ADMI_TIPO_CUENTA_METHOD),
			Map.entry(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_UPDATE_ADMI_TIPO_CUENTA_METHOD, PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_UPDATE_ADMI_TIPO_CUENTA_METHOD),
			Map.entry(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_ELIMINADO_ADMI_TIPO_CUENTA_METHOD, PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_ELIMINADO_ADMI_TIPO_CUENTA_METHOD),
			Map.entry(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_LIST_BY_ADMI_TIPO_CUENTA_METHOD, PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_LIST_BY_ADMI_TIPO_CUENTA_METHOD),
			Map.entry(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_PAGE_LIST_BY_ADMI_TIPO_CUENTA_METHOD, PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_PAGE_LIST_BY_ADMI_TIPO_CUENTA_METHOD),
			
			//infomovimiento
			Map.entry(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_SAVE_INFO_MOVIMIENTO_METHOD, PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_SAVE_INFO_MOVIMIENTO_METHOD),
			Map.entry(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_UPDATE_INFO_MOVIMIENTO_METHOD, PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_UPDATE_INFO_MOVIMIENTO_METHOD),
			Map.entry(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_ELIMINADO_INFO_MOVIMIENTO_METHOD, PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_ELIMINADO_INFO_MOVIMIENTO_METHOD),
			Map.entry(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_LIST_BY_INFO_MOVIMIENTO_METHOD, PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_LIST_BY_INFO_MOVIMIENTO_METHOD),
			Map.entry(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_PAGE_LIST_BY_INFO_MOVIMIENTO_METHOD, PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_PAGE_LIST_BY_INFO_MOVIMIENTO_METHOD),
			Map.entry(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_LIST_BY_INFO_MOVIMIENTO_REPORTE_METHOD, PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_LIST_BY_INFO_MOVIMIENTO_REPORTE_METHOD),
			Map.entry(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_PAGE_LIST_BY_INFO_MOVIMIENTO_REPORTE_METHOD, PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_PAGE_LIST_BY_INFO_MOVIMIENTO_REPORTE_METHOD),
			Map.entry(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_PAGE_LIST_BY_INFO_MOVIMIENTO_REPORTE_PDF_METHOD, PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_PAGE_LIST_BY_INFO_MOVIMIENTO_REPORTE_PDF_METHOD),

			//infocuenta
			Map.entry(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_SAVE_INFO_CUENTA_METHOD, PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_SAVE_INFO_CUENTA_METHOD),
			Map.entry(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_UPDATE_INFO_CUENTA_METHOD, PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_UPDATE_INFO_CUENTA_METHOD),
			Map.entry(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_ELIMINADO_INFO_CUENTA_METHOD, PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_ELIMINADO_INFO_CUENTA_METHOD),
			Map.entry(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_LIST_BY_INFO_CUENTA_METHOD, PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_LIST_BY_INFO_CUENTA_METHOD),
			Map.entry(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_PAGE_LIST_BY_INFO_CUENTA_METHOD, PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_PAGE_LIST_BY_INFO_CUENTA_METHOD),
			Map.entry(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_VALIDAR_INFO_CUENTA_BY_USUARIO_METHOD, PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_VALIDAR_INFO_CUENTA_BY_USUARIO_METHOD)
	);
	
	@Override
	public <P, R> R process(GenericProducerModel<P> request) throws GenericException {
		return CommonUtil.executeMethod(this, methodMap.get(request.getOption()), request);
	}

	//personas
	public <T,R> R saveInfoPersona(GenericProducerModel<T> request) throws GenericException {
		return iPersonClientBO.saveInfoPersona(request.getPayload());
	}

	public <T,R> R updateInfoPersona(GenericProducerModel<T> request) throws GenericException {
		return iPersonClientBO.updateInfoPersona(request.getPayload());
	}

	public <T,R> R deleteInfoPersona(GenericProducerModel<T> request) throws GenericException {
		return iPersonClientBO.deleteInfoPersona(request.getPayload());
	}

	public <T,R> R listByInfoPersona(GenericProducerModel<T> request) throws GenericException {
		return iPersonClientBO.listByInfoPersona(request.getPayload());
	}

	public <T,R> R pageListByInfoPersona(GenericProducerModel<T> request) throws GenericException {
		return iPersonClientBO.pageListByInfoPersona(request.getPayload());
	}

	//clientes
	public <T,R> R saveInfoCliente(GenericProducerModel<T> request) throws GenericException {
		return iPersonClientBO.saveInfoCliente(request.getPayload());
	}

	public <T,R> R updateInfoCliente(GenericProducerModel<T> request) throws GenericException {
		return iPersonClientBO.updateInfoCliente(request.getPayload());
	}

	public <T,R> R deleteInfoCliente(GenericProducerModel<T> request) throws GenericException {
		return iPersonClientBO.deleteInfoCliente(request.getPayload());
	}

	public <T,R> R listByInfoCliente(GenericProducerModel<T> request) throws GenericException {
		return iPersonClientBO.listByInfoCliente(request.getPayload());
	}

	public <T,R> R pageListByInfoCliente(GenericProducerModel<T> request) throws GenericException {
		return iPersonClientBO.pageListByInfoCliente(request.getPayload());
	}

	public <T,R> R retrieveInfoCliente(GenericProducerModel<T> request) throws GenericException {
		return iPersonClientBO.retrieveInfoCliente(request.getPayload());
	}

	//admitypomovimiento
	public <T,R> R saveAdmiTipoMovimiento(GenericProducerModel<T> request) throws GenericException {
		return iPersonClientBO.saveAdmiTipoMovimiento(request.getPayload());
	}

	public <T,R> R updateAdmiTipoMovimiento(GenericProducerModel<T> request) throws GenericException {
		return iPersonClientBO.updateAdmiTipoMovimiento(request.getPayload());
	}

	public <T,R> R deleteAdmiTipoMovimiento(GenericProducerModel<T> request) throws GenericException {
		return iPersonClientBO.deleteAdmiTipoMovimiento(request.getPayload());
	}

	public <T,R> R listByAdmiTipoMovimiento(GenericProducerModel<T> request) throws GenericException {
		return iPersonClientBO.listByAdmiTipoMovimiento(request.getPayload());
	}

	public <T,R> R pageListByAdmiTipoMovimiento(GenericProducerModel<T> request) throws GenericException {
		return iPersonClientBO.pageListByAdmiTipoMovimiento(request.getPayload());
	}

	//admitypocuenta
	public <T,R> R saveAdmiTipoCuenta(GenericProducerModel<T> request) throws GenericException {
		return iPersonClientBO.saveAdmiTipoCuenta(request.getPayload());
	}

	public <T,R> R updateAdmiTipoCuenta(GenericProducerModel<T> request) throws GenericException {
		return iPersonClientBO.updateAdmiTipoCuenta(request.getPayload());
	}

	public <T,R> R deleteAdmiTipoCuenta(GenericProducerModel<T> request) throws GenericException {
		return iPersonClientBO.deleteAdmiTipoCuenta(request.getPayload());
	}

	public <T,R> R listByAdmiTipoCuenta(GenericProducerModel<T> request) throws GenericException {
		return iPersonClientBO.listByAdmiTipoCuenta(request.getPayload());
	}

	public <T,R> R pageListByAdmiTipoCuenta(GenericProducerModel<T> request) throws GenericException {
		return iPersonClientBO.pageListByAdmiTipoCuenta(request.getPayload());
	}

	//infomovimiento
	public <T,R> R saveInfoMovimiento(GenericProducerModel<T> request) throws GenericException {
		return iPersonClientBO.saveInfoMovimiento(request.getPayload());
	}

	public <T,R> R updateInfoMovimiento(GenericProducerModel<T> request) throws GenericException {
		return iPersonClientBO.updateInfoMovimiento(request.getPayload());
	}
	
	public <T,R> R deleteInfoMovimiento(GenericProducerModel<T> request) throws GenericException {
		return iPersonClientBO.deleteInfoMovimiento(request.getPayload());
	}

	public <T,R> R listByInfoMovimiento(GenericProducerModel<T> request) throws GenericException {
		return iPersonClientBO.listByInfoMovimiento(request.getPayload());
	}

	public <T,R> R pageListByInfoMovimiento(GenericProducerModel<T> request) throws GenericException {
		return iPersonClientBO.pageListByInfoMovimiento(request.getPayload());
	}

	public <T,R> R listByInfoMovimientoReporte(GenericProducerModel<T> request) throws GenericException {
		return iPersonClientBO.listByInfoMovimientoReporte(request.getPayload());
	}

	public <T,R> R pageListByInfoMovimientoReporte(GenericProducerModel<T> request) throws GenericException {
		return iPersonClientBO.pageListByInfoMovimientoReporte(request.getPayload());
	}

	public <T,R> R pageListByInfoMovimientoReportePdf(GenericProducerModel<T> request) throws GenericException {
		return iPersonClientBO.pageListByInfoMovimientoReportePdf(request.getPayload());
	}

	//infocuenta
	public <T,R> R saveInfoCuenta(GenericProducerModel<T> request) throws GenericException {
		return iPersonClientBO.saveInfoCuenta(request.getPayload());
	}

	public <T,R> R updateInfoCuenta(GenericProducerModel<T> request) throws GenericException {
		return iPersonClientBO.updateInfoCuenta(request.getPayload());
	}
	
	public <T,R> R deleteInfoCuenta(GenericProducerModel<T> request) throws GenericException {
		return iPersonClientBO.deleteInfoCuenta(request.getPayload());
	}

	public <T,R> R listByInfoCuenta(GenericProducerModel<T> request) throws GenericException {
		return iPersonClientBO.listByInfoCuenta(request.getPayload());
	}
	
	public <T,R> R pageListByInfoCuenta(GenericProducerModel<T> request) throws GenericException {
		return iPersonClientBO.pageListByInfoCuenta(request.getPayload());
	}

	public <T,R> R validarInfoCuentaByPersona(GenericProducerModel<T> request) throws GenericException {
		return iPersonClientBO.validarInfoCuentaByPersona(request.getPayload());
	}

}
