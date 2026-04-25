package com.personclientaccountmovement.integration.communication;

import com.personclientaccountmovement.exception.GenericException;
import com.personclientaccountmovement.integration.dto.request.InternalRequestDTO;
import com.personclientaccountmovement.integration.dto.response.GenericResponseDTO;

/**
* Contrato de productor de mensajes genéricos.
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
public interface IGenericMessageProducer<Q, S> {

	S sendMessage(Q request) throws GenericException;
}
