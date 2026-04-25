package com.personclientaccountmovement.integration.util;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.personclientaccountmovement.cons.PersonClientAccountMovementConfigConstants;
import com.personclientaccountmovement.dto.PageDTO;
import com.personclientaccountmovement.exception.GenericException;

/**
* Utilidades de mapeo y formateo de payloads genéricos.
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/


public final class CommonFormat {

	private static final ObjectMapper MAPPER = new ObjectMapper();

	private CommonFormat() {
	}

	public static <T> T objectMapping(Object payload, Class<T> clazz) throws GenericException {
		try {
			return MAPPER.convertValue(payload, clazz);
		} catch (IllegalArgumentException e) {
			throw new GenericException(e.getMessage(), PersonClientAccountMovementConfigConstants.CODE_ERROR_GENERIC);
		}
	}

	public static <T> PageDTO<T> pageMapping(Object payload, Class<T> rowClass) throws GenericException {
		try {
			JavaType type = MAPPER.getTypeFactory().constructParametricType(PageDTO.class, rowClass);
			return MAPPER.convertValue(payload, type);
		} catch (IllegalArgumentException e) {
			throw new GenericException(e.getMessage(), PersonClientAccountMovementConfigConstants.CODE_ERROR_GENERIC);
		}
	}
}
