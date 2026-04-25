package com.personclientaccountmovement.comp.communication.support;

import com.personclientaccountmovement.integration.dto.response.GenericResponseDTO;

/**
* Utilidad para extraer el cuerpo útil de respuestas genéricas.
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
public final class GenericResponseUnwrapUtil {

	private GenericResponseUnwrapUtil() {
	}

	public static Object unwrap(Object node) {
		Object cur = node;
		while (cur instanceof GenericResponseDTO<?> gr && gr.getPayload() != null) {
			cur = gr.getPayload();
		}
		return cur;
	}

}
