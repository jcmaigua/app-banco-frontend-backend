package com.personclientaccountmovement.integration.interfaces;

import com.personclientaccountmovement.exception.GenericException;
import com.personclientaccountmovement.integration.model.GenericProducerModel;

/**
* Contrato del productor orquestador de integración.
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
public interface IGenericProducer {

	<P, R> R process(GenericProducerModel<P> request) throws GenericException;
}
