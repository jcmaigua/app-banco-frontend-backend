package com.personclientaccountmovement.support;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.personclientaccountmovement.exception.GenericException;

/**
* Soporte para registro de excepciones con contexto de módulo.
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
public final class ExceptionLogSupport {

	private static final Logger log = LogManager.getLogger(ExceptionLogSupport.class);

	private ExceptionLogSupport() {
	}

	/**
	 * Registro WARN con identificador de microservicio/capa (el caller debe hacer {@code throw e;}).
	 */
	public static void logWarn(String microserviceId, GenericException e) {
		log.warn("[{}] GenericException code={} status={} message={}", microserviceId, e.getCodeError(),
				e.getStatusError(), e.getMessageError(), e);
	}
}
