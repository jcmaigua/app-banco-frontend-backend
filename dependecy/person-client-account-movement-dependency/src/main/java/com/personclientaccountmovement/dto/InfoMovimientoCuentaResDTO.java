package com.personclientaccountmovement.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* DTO INFO_MOVIMIENTO_RES
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InfoMovimientoCuentaResDTO {

    private Long idMovimiento;
    private Long cuentaId;
    private String numeroCuenta;
    private Long tipoCuentaId;
    private String tipoCuentaNombre;
    private Long tipoMovimientoId;
    private String tipoMovimientoNombre;
    private BigDecimal valor;
    private BigDecimal saldoInicial;
    private String movimiento;
    private BigDecimal saldoActual;
}