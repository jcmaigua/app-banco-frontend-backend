package com.personclientaccountmovement.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* DTO INFO_CLIENTE_REQ
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InfoClienteReqDTO {

    private Long idCliente;
    private Long personaId;
    private String contrasena;
    private String estado;
    private Date fechaCreacion;
    private String personaCreacion;
    private Date fechaModificacion;
    private String personaModificacion;
    private InfoPersonaReqDTO infoPersonaReqDTO;
}