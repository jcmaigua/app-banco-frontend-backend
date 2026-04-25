package com.personclientaccountmovement.dto;

import java.util.Date;
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
public class InfoMovimientoResDTO {
 
    private Long idMovimiento;
    private Long tipoMovimientoId;
    private String tipoMovimientoNombre;
    private Long cuentaId;
    private String numeroCuenta;
    private BigDecimal valor;
    private BigDecimal saldo;
    private Date fechaCreacion;
    private String personaCreacion;
}