package com.personclientaccountmovement.dto;

import java.util.Date;
import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* DTO INFO_MOVIMIENTO_REQ
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InfoMovimientoReqDTO {
 
    private Long idMovimiento;
    private Long tipoMovimientoId;
    private String tipoMovimientoNombre;
    private String tipoCuentaNombre;
    private Long cuentaId;

    @NotBlank(message = "numeroCuenta es obligatorio")
    private String numeroCuenta;

    @NotNull(message = "valor es obligatorio")
    private BigDecimal valor;
    private BigDecimal saldo;
    private Date fechaCreacion;
    private String personaCreacion;
    private String estado;
}