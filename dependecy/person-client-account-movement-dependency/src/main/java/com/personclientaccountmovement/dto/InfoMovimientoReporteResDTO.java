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
public class InfoMovimientoReporteResDTO {
 
    private String fecha;
    private Long personaId;
    private String nombre;
    private String apellido;
    private String identificacion;
    private String numeroCuenta;
    private String tipoCuentaNombre;
    private BigDecimal saldoInicial;
    private String estado;
    private BigDecimal movimiento;
    private BigDecimal saldoDisponible;
}