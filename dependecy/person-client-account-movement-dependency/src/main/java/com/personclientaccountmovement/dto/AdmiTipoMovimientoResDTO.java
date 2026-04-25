package com.personclientaccountmovement.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* DTO ADMI_TIPO_MOVIMIENTO_RES
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdmiTipoMovimientoResDTO {
 
    private Long idTipoMovimiento;
    private String nombreMovimiento;
    private String descripcion;
    private String estado;
    private Date fechaCreacion;
    private String personaCreacion;
    private Date fechaModificacion;
    private String personaModificacion;
}