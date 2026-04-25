package com.personclientaccountmovement.integration.dispatcher;

import com.personclientaccountmovement.exception.GenericException;
import com.personclientaccountmovement.integration.dto.request.GenericRequestDTO;

/**
* Contrato para despacho de resultados completos.
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
public interface ICompleteResultDispatcher {

	Object dispatch(String methodName, GenericRequestDTO<?> request) throws GenericException;
}
