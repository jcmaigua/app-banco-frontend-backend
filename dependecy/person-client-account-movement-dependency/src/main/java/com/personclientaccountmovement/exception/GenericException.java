package com.personclientaccountmovement.exception;

import com.personclientaccountmovement.cons.PersonClientAccountMovementConfigConstants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
* Clase utilizada para las excepciones genéricas
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@EqualsAndHashCode(callSuper = true)
@Data
public class GenericException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	private final Integer codeError;
	private final String statusError;
	private final String messageError;

	public GenericException() {
		this.messageError = "";
		this.statusError = PersonClientAccountMovementConfigConstants.STATUS_ERROR;
		this.codeError = PersonClientAccountMovementConfigConstants.CODE_ERROR_GENERIC;
	}
	
	public GenericException(String message) {
		super(message);
		this.codeError = null;
		this.statusError = "";
		this.messageError = message;
	}

	public GenericException(String message, Integer code) {
		super(message);
		this.codeError = code;
		this.statusError = PersonClientAccountMovementConfigConstants.STATUS_ERROR;
		this.messageError = message;
	}

	/**
	 * Constructor alineado con errores de infraestructura (p. ej. productor de mensajes no encontrado).
	 */
	public GenericException(Class<?> sourceClass, String methodName, Integer codeError, String statusError,
			String message) {
		super(message);
		this.messageError = message;
		this.codeError = codeError != null ? codeError : PersonClientAccountMovementConfigConstants.CODE_ERROR_GENERIC;
		this.statusError = (statusError != null && !statusError.isEmpty()) ? statusError
				: PersonClientAccountMovementConfigConstants.STATUS_ERROR;
	}
        
        public GenericException(Throwable throwable) {
		super(throwable);
		this.messageError = throwable.getLocalizedMessage();
		Logger log = LogManager.getLogger(this.getClass());
		log.error("Causa => {}", throwable.getLocalizedMessage());
		int numLengthTrace = throwable.getStackTrace().length > 4 ? 4 : 2;
		this.codeError = null;
		this.statusError = "";
		for (int i = 0; i < numLengthTrace; i++) {
			log.error("Flujo de error {} => {}", i, throwable.getStackTrace()[i]);
		}
	}

}
