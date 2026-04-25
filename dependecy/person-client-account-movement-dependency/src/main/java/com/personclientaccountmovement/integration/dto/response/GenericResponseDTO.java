package com.personclientaccountmovement.integration.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Envoltorio genérico de respuesta en la capa de integración.
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.ALWAYS)
public class GenericResponseDTO<T> {

	@Builder.Default
	private String status = "OK";

	@Builder.Default
	private Integer code = 200;

	@Builder.Default
	private String message = "Transaccion exitosa";

	private T payload;

	public static <T> GenericResponseDTO<T> success(T payload) {
		return GenericResponseDTO.<T>builder().status("OK").code(200).message("Transaccion exitosa").payload(payload)
				.build();
	}

	public static <T> GenericResponseDTO<T> of(String status, Integer code, String message, T payload) {
		return GenericResponseDTO.<T>builder().status(status).code(code).message(message).payload(payload).build();
	}
}
