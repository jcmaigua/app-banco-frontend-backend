package com.personclientaccountmovement.integration.producer;

import org.springframework.stereotype.Service;

import com.personclientaccountmovement.cons.PersonClientAccountMovementConfigConstants;
import com.personclientaccountmovement.exception.GenericException;
import com.personclientaccountmovement.integration.communication.IGenericMessageProducer;
import com.personclientaccountmovement.integration.dto.request.InternalRequestDTO;
import com.personclientaccountmovement.integration.dto.response.GenericResponseDTO;

/**
* Implementación de productor de mensajes HTTP REST.
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Service("restMessageProducer")
public class RestMessageProducer implements IGenericMessageProducer<InternalRequestDTO<?>, GenericResponseDTO<?>> {

	@Override
	public GenericResponseDTO<?> sendMessage(InternalRequestDTO<?> request) throws GenericException {
		throw new GenericException("REST delivery channel is not implemented in person-client-account-movement-dependency.",
				PersonClientAccountMovementConfigConstants.CODE_ERROR_GENERIC);
	}
}
