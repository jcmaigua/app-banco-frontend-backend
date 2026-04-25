package com.personclientaccountmovement.repository.postgres.utils;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.personclientaccountmovement.cons.PersonClientAccountMovementConfigConstants;
import com.personclientaccountmovement.dto.PageDTO;
import com.personclientaccountmovement.dto.AdmiConfigurationParameterCabReqDTO;
import com.personclientaccountmovement.dto.ObjectIdDTO;
import com.personclientaccountmovement.exception.GenericException;

/**
* Validaciones comunes de paginación, límites de listas y fechas para el repositorio de clientes.
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial 
* @since 15/04/2026
*/
@Component
public class ResourceValidators {

	/**
	* Verifica que el objeto de identificador no sea el vacío por defecto.
	* 
	* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	* @version 1.0 - Version Inicial 
	* @since 15/04/2026
	*/
	public void validarObjectIdDTO(ObjectIdDTO requestDTO) throws GenericException {
		ObjectIdDTO objNull = new ObjectIdDTO();
		if (requestDTO.equals(objNull)) {
			throw new GenericException(PersonClientConfigConstants.MSG_PARAMETER_REQUIRED,
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
	}

	/**
	 * Impide respuestas de lista que superen el máximo configurado.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial 
	 * @since 15/04/2026
	 */
	public void validarMaximoDataList(long count) throws GenericException {
		if (count > PersonClientConfigConstants.MAXIMO_DATA_LIST) {
			throw new GenericException(
					String.format(PersonClientConfigConstants.MSG_DATOS_LISTA_MAXIMO_SUPERADO,
							PersonClientConfigConstants.MAXIMO_DATA_LIST),
					PersonClientAccountMovementConfigConstants.ERROR_PARSE_VALUES);
		}
	}

	/**
	 * Valida índice de página, tamaño, tabla de filtros y orden opcional (ASC/DESC).
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial 
	 * @since 15/04/2026
	 */
	public void validarPagina(PageDTO<?> request) throws GenericException {
		if (request.getPage() == null) {
			throw new GenericException(PersonClientConfigConstants.MSG_PAGE_REQUERIDO,
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
		if (request.getPage() < 0) {
			throw new GenericException(PersonClientConfigConstants.MSG_PAGE_POSITIVO,
					PersonClientAccountMovementConfigConstants.INFORMATIVE_VALUES);
		}
		if (request.getSize() == null) {
			throw new GenericException(PersonClientConfigConstants.MSG_SIZE_REQUERIDO,
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
		if (request.getSize() < 0) {
			throw new GenericException(PersonClientConfigConstants.MSG_SIZE_POSITIVO,
					PersonClientAccountMovementConfigConstants.INFORMATIVE_VALUES);
		}
		if (request.getTabla() == null) {
			throw new GenericException(PersonClientConfigConstants.MSG_TABLA_REQUERIDO,
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
		if (request.getOrder() != null && !(request.getOrder().equals("ASC") || request.getOrder().equals("DESC"))) {
			throw new GenericException(PersonClientConfigConstants.MSG_ORDER_ASC_DESC,
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
	}

	/**
	 * Normaliza la fecha al inicio del día (00:00:00.000) en la zona local del calendario.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial 
	 * @since 15/04/2026
	 */
	public Date setTimeToStartOfDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * Normaliza la fecha al fin del día (23:59:59.999) en la zona local del calendario.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial 
	 * @since 15/04/2026
	 */
	public Date setTimeToEndOfDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}

	/**
	 * Resta 30 días a la fecha dada y fija inicio de día.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial 
	 * @since 15/04/2026
	 */
	public Date getDateMinus30Days(Date currentDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);
		calendar.add(Calendar.DAY_OF_MONTH, -30);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	public void validarConfigurationParameterCab(AdmiConfigurationParameterCabReqDTO request) throws GenericException {
		AdmiConfigurationParameterCabReqDTO objNull = new AdmiConfigurationParameterCabReqDTO();
		if (request.equals(objNull)) {
			throw new GenericException(PersonClientConfigConstants.MSG_PARAMETER_REQUIRED,
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
	}
}
