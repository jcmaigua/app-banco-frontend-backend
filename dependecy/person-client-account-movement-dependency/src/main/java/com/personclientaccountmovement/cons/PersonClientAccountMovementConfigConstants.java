package com.personclientaccountmovement.cons;
/**
* Constantes de configuración compartidas del dominio.
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
public class PersonClientAccountMovementConfigConstants {

	private PersonClientAccountMovementConfigConstants() {
	}
	
	// Métodos centralizados de InfoPersona
	public static final String CORE_CONFIG_MGMT_SAVE_INFO_PERSONA_METHOD = "saveInfoPersona";
	public static final String CORE_CONFIG_MGMT_UPDATE_INFO_PERSONA_METHOD = "updateInfoPersona";
	public static final String CORE_CONFIG_MGMT_ELIMINADO_INFO_PERSONA_METHOD = "deleteInfoPersona";
	public static final String CORE_CONFIG_MGMT_LIST_BY_INFO_PERSONA_METHOD = "listByInfoPersona";
	public static final String CORE_CONFIG_MGMT_PAGE_LIST_BY_INFO_PERSONA_METHOD = "pageListByInfoPersona";
		
	// Métodos centralizados de AdmiTipoMovimiento
	public static final String CORE_CONFIG_MGMT_SAVE_ADMI_TIPO_MOVIMIENTO_METHOD = "saveAdmiTipoMovimiento";
	public static final String CORE_CONFIG_MGMT_UPDATE_ADMI_TIPO_MOVIMIENTO_METHOD = "updateAdmiTipoMovimiento";
	public static final String CORE_CONFIG_MGMT_ELIMINADO_ADMI_TIPO_MOVIMIENTO_METHOD = "deleteAdmiTipoMovimiento";
	public static final String CORE_CONFIG_MGMT_LIST_BY_ADMI_TIPO_MOVIMIENTO_METHOD = "listByAdmiTipoMovimiento";
	public static final String CORE_CONFIG_MGMT_PAGE_LIST_BY_ADMI_TIPO_MOVIMIENTO_METHOD = "pageListByAdmiTipoMovimiento";

	// Métodos centralizados de AdmiTipoCuenta
	public static final String CORE_CONFIG_MGMT_SAVE_ADMI_TIPO_CUENTA_METHOD = "saveAdmiTipoCuenta";
	public static final String CORE_CONFIG_MGMT_UPDATE_ADMI_TIPO_CUENTA_METHOD = "updateAdmiTipoCuenta";
	public static final String CORE_CONFIG_MGMT_ELIMINADO_ADMI_TIPO_CUENTA_METHOD = "deleteAdmiTipoCuenta";
	public static final String CORE_CONFIG_MGMT_LIST_BY_ADMI_TIPO_CUENTA_METHOD = "listByAdmiTipoCuenta";
	public static final String CORE_CONFIG_MGMT_PAGE_LIST_BY_ADMI_TIPO_CUENTA_METHOD = "pageListByAdmiTipoCuenta";

	// Métodos centralizados de InfoMovimiento
	public static final String CORE_CONFIG_MGMT_SAVE_INFO_MOVIMIENTO_METHOD = "saveInfoMovimiento";
	public static final String CORE_CONFIG_MGMT_UPDATE_INFO_MOVIMIENTO_METHOD = "updateInfoMovimiento";
	public static final String CORE_CONFIG_MGMT_ELIMINADO_INFO_MOVIMIENTO_METHOD = "deleteInfoMovimiento";
	public static final String CORE_CONFIG_MGMT_LIST_BY_INFO_MOVIMIENTO_METHOD = "listByInfoMovimiento";
	public static final String CORE_CONFIG_MGMT_PAGE_LIST_BY_INFO_MOVIMIENTO_METHOD = "pageListByInfoMovimiento";

	// Métodos centralizados de InfoMovimientoReporte
	public static final String CORE_CONFIG_MGMT_PAGE_LIST_BY_INFO_MOVIMIENTO_REPORTE_METHOD = "pageListByInfoMovimientoReporte";
	public static final String CORE_CONFIG_MGMT_PAGE_LIST_BY_INFO_MOVIMIENTO_REPORTE_PDF_METHOD = "pageListByInfoMovimientoReportePdf";
	public static final String CORE_CONFIG_MGMT_LIST_BY_INFO_MOVIMIENTO_REPORTE_METHOD = "listByInfoMovimientoReporte";
	// Métodos centralizados de InfoCuenta
	public static final String CORE_CONFIG_MGMT_SAVE_INFO_CUENTA_METHOD = "saveInfoCuenta";
	public static final String CORE_CONFIG_MGMT_UPDATE_INFO_CUENTA_METHOD = "updateInfoCuenta";
	public static final String CORE_CONFIG_MGMT_ELIMINADO_INFO_CUENTA_METHOD = "deleteInfoCuenta";
	public static final String CORE_CONFIG_MGMT_LIST_BY_INFO_CUENTA_METHOD = "listByInfoCuenta";
	public static final String CORE_CONFIG_MGMT_PAGE_LIST_BY_INFO_CUENTA_METHOD = "pageListByInfoCuenta";
	public static final String CORE_CONFIG_MGMT_RETRIEVE_INFO_CLIENTE_METHOD = "retrieveInfoCliente";
	public static final String CORE_CONFIG_MGMT_RETRIEVE_INFO_PERSONA_METHOD = "retrieveInfoPersona";
	public static final String CORE_CONFIG_MGMT_VALIDAR_INFO_CUENTA_BY_USUARIO_METHOD = "validarInfoCuentaByPersona";
	// Métodos centralizados de InfoCliente
	public static final String CORE_CONFIG_MGMT_SAVE_INFO_CLIENTE_METHOD = "saveInfoCliente";
	public static final String CORE_CONFIG_MGMT_UPDATE_INFO_CLIENTE_METHOD = "updateInfoCliente";
	public static final String CORE_CONFIG_MGMT_ELIMINADO_INFO_CLIENTE_METHOD = "deleteInfoCliente";
	public static final String CORE_CONFIG_MGMT_LIST_BY_INFO_CLIENTE_METHOD = "listByInfoCliente";
	public static final String CORE_CONFIG_MGMT_PAGE_LIST_BY_INFO_CLIENTE_METHOD = "pageListByInfoCliente";

	// Identificadores de log
	public static final String GROUP_LOG = "log";
	public static final String TOPIC_LOG_ASYN = "logAsynchro";
	public static final String OP_ERROR_LOG = "errorLog";
	public static final Integer MISSING_VALUES = 10;
	public static final Integer INFORMATIVE_VALUES = 11;
	public static final Integer EXISTING_VALUES = 12;
	public static final Integer ERROR_PARSE_VALUES = 13;

	public static final int CODE_OK = 0;
	public static final String TIMEZONE_DATE = "America/Guayaquil";
	public static final String STATUS_OK = "OK";
	public static final String MESSAGE_OK = "Transacción realizada correctamente";
	public static final String STATUS_ERROR = "ERROR";

	public static final int CODE_ERROR_GENERIC = 1;

	//Mensajes de error
	public static final String SALDO_NO_DISPONIBLE = "Saldo no disponible";
	public static final String CUPO_DIARIO_EXCEDIDO = "Cupo diario Excedido";
}
