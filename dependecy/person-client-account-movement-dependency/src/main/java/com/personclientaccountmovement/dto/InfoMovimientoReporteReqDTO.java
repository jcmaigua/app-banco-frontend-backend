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
public class InfoMovimientoReporteReqDTO {
    private Long idCliente;
    private Long idPersona;
    private String identificacion;
    private String fechaDesde;
    private String fechaHasta;

}