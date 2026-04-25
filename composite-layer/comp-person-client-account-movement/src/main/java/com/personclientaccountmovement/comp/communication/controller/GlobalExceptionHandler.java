package com.personclientaccountmovement.comp.communication.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.personclientaccountmovement.comp.cons.CompPersonClientAccountMovementConstants;
import com.personclientaccountmovement.cons.PersonClientAccountMovementConfigConstants;
import com.personclientaccountmovement.exception.GenericException;
import com.personclientaccountmovement.integration.dto.response.GenericResponseDTO;

/**
* Manejo centralizado de excepciones REST.
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger log = LogManager.getLogger(GlobalExceptionHandler.class);

	/**
	 * Traduce {@link GenericException} a respuesta HTTP y cuerpo {@link GenericResponseDTO}.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<GenericResponseDTO<Object>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		String msg = ex.getBindingResult().getFieldErrors().stream()
				.map(err -> err.getField() + ": " + err.getDefaultMessage())
				.findFirst()
				.orElse("Solicitud inválida");
		log.warn("[composite REST] Validación de entrada: {}", msg);
		GenericResponseDTO<Object> body = GenericResponseDTO.of(PersonClientAccountMovementConfigConstants.STATUS_ERROR,
				PersonClientAccountMovementConfigConstants.MISSING_VALUES, msg, null);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
	}

	@ExceptionHandler(GenericException.class)
	public ResponseEntity<GenericResponseDTO<Object>> handleGenericException(GenericException ex) {
		log.info("Metodo controller handleGenericException iniciado");
		HttpStatus status = resolveHttpStatus(ex);
		log.warn("[composite REST] GenericException -> HTTP {} code={} status={} message={}", status.value(),
				ex.getCodeError(), ex.getStatusError(), ex.getMessageError(), ex);
		String st = ex.getStatusError() != null && !ex.getStatusError().isBlank() ? ex.getStatusError()
				: PersonClientAccountMovementConfigConstants.STATUS_ERROR;
		GenericResponseDTO<Object> body = GenericResponseDTO.of(st, ex.getCodeError(), ex.getMessageError(), null);
		return ResponseEntity.status(status).body(body);
	}

	/**
	 * Maneja errores no previstos y responde con HTTP 500 y mensaje genérico.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<GenericResponseDTO<Object>> handleUnexpected(Exception ex) {
		log.info("Metodo controller handleUnexpected iniciado");
		log.error("[composite REST] Error no controlado", ex);
		String msg = ex.getMessage() != null ? ex.getMessage() : ex.toString();
		GenericResponseDTO<Object> body = GenericResponseDTO.of(PersonClientAccountMovementConfigConstants.STATUS_ERROR, null, msg, null);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
	}

	/**
	 * Resuelve el código HTTP a partir del contenido de la excepción de negocio.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	private HttpStatus resolveHttpStatus(GenericException ex) {
		log.info("Metodo controller resolveHttpStatus iniciado");
		String msg = ex.getMessageError();
		if (PersonClientAccountMovementConfigConstants.SALDO_NO_DISPONIBLE.equals(msg)
				|| PersonClientAccountMovementConfigConstants.CUPO_DIARIO_EXCEDIDO.equals(msg)) {
			return HttpStatus.BAD_REQUEST;
		}
		if (ex.getCodeError() != null && ex.getCodeError() >= 10) {
			return HttpStatus.BAD_REQUEST;
		}
		return HttpStatus.INTERNAL_SERVER_ERROR;
	}
}
