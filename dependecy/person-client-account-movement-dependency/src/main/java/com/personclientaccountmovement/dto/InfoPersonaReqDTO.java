package com.personclientaccountmovement.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* DTO INFO_PERSONA_REQ
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InfoPersonaReqDTO {

    private Long idPersona;
    private String nombre;
    private String apellido;
    private String genero;
    private Integer edad;
    private String identificacion;
    private String direccion;
    private String telefono;
    private String estado;
    private String personaRequest;
    private Date fechaCreacion;
    private String personaCreacion;
    private Date fechaModificacion;
    private String personaModificacion;
}