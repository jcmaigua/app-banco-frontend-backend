package com.personclientaccountmovement.integration.dispatcher;

import com.personclientaccountmovement.exception.GenericException;
import com.personclientaccountmovement.integration.dto.request.GenericRequestDTO;

/**
* Manejador simple de resultado asociado a un método registrado.
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@FunctionalInterface
public interface SimpleResultHandler {

	Object handle(GenericRequestDTO<?> request) throws GenericException;
}
