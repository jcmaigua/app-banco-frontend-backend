package com.personclientaccountmovement.integration.dispatcher;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.personclientaccountmovement.cons.PersonClientAccountMovementConfigConstants;
import com.personclientaccountmovement.exception.GenericException;
import com.personclientaccountmovement.integration.dto.request.GenericRequestDTO;

/**
* Despachador central de solicitudes genéricas hacia manejadores.
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Component("AnythingDispatcher")
public class AnythingDispatcher implements ICompleteResultDispatcher {

	private final Map<String, SimpleResultHandler> handlers = new ConcurrentHashMap<>();

	public void registerSimpleResultHandler(Class<?> requestDtoClass, SimpleResultHandler handler, String methodKey) {
		handlers.put(methodKey, handler);
	}

	/**
	 * Operación dispatch.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public Object dispatch(String methodName, GenericRequestDTO<?> request) throws GenericException {
		SimpleResultHandler handler = handlers.get(methodName);
		if (handler == null) {
			throw new GenericException("No handler registered for method: " + methodName,
					PersonClientAccountMovementConfigConstants.CODE_ERROR_GENERIC);
		}
		return handler.handle(request);
	}
}
