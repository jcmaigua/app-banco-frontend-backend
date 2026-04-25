package com.personclientaccountmovement.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* DTO ADMI_TIPO_CUENTA_RES
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdmiTipoCuentaResDTO {
 
    private Long idTipoCuenta;
    private String nombreCuenta;
    private String descripcion;
    private String estado;
    private Date fechaCreacion;
    private String personaCreacion;
    private Date fechaModificacion;
    private String personaModificacion;
}