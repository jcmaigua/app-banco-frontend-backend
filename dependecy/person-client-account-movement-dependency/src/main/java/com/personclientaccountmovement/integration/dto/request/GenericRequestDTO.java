package com.personclientaccountmovement.integration.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Envoltorio genérico de solicitud en la capa de integración.
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenericRequestDTO<T> {

	@NotBlank(message = "userRequest es obligatorio")
	private String userRequest;

	@NotNull(message = "payload es obligatorio")
	@Valid
	private T payload;
}
