package com.personclientaccountmovement.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* DTO unificado de respuesta para cuenta y persona.
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InfoCuentaPersonaUnifiedResDTO {

	private Long idCuenta;
	private Long tipoCuentaId;
	private String numeroCuenta;
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
