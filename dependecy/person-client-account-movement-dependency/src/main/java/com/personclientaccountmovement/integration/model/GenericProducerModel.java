package com.personclientaccountmovement.integration.model;

import com.personclientaccountmovement.integration.enums.EnumRequestType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Modelo de datos para invocar al productor genérico.
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenericProducerModel<P> {

	private String userRequest;
	private String option;
	private P payload;
	private EnumRequestType type;
	private String transactionId;
	private String service;
	private String origin;

	@Builder.Default
	private int requestTimeoutSeconds = 0;
}
