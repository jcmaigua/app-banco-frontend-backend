package com.personclientaccountmovement.dto;

import java.util.Date;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* DTO INFO_CUENTA_REQ
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InfoCuentaReqDTO {
 
    private Long idCuenta;
    private String numeroCuenta;
    private Long tipoCuentaId;
    private String tipoCuentaNombre;
    /** Propietario de la cuenta (1:N persona → cuentas). */
    private Long personaId;
    private BigDecimal saldoInicial;
    private BigDecimal saldoActual;
    private String estado;
    private Date fechaCreacion;
    private String personaCreacion;
    private Date fechaModificacion;
    private String personaModificacion;
}
 