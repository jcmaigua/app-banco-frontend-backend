package com.personclientaccountmovement.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* DTO unificado de solicitud para cuenta y persona.
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InfoCuentaPersonaUnifiedReqDTO {

	/** Opcional: consulta/actualización por id interno. */
	private Long idCuenta;
	/** Opcional: id de tipo de cuenta si ya se conoce (alternativa a {@link #nombreTipoCuenta}). */
	private Long tipoCuentaId;
	private String numeroCuenta;
	/** Nombre del tipo de cuenta (p. ej. AHORRO), resuelto a {@code tipo_cuenta_id} en persistencia. */
	private String nombreTipoCuenta;
	private Long personaId;
	private String identificacion;
	private String nombre;
	private String apellido;
	private BigDecimal saldoInicial;
	private BigDecimal saldoActual;
	private String estado;
	private String personaRequest;
}
