package com.personclientaccountmovement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* DTO unificado de respuesta para persona y cliente.
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonaClienteUnifiedResDTO {

	private Long idPersona;
	private Long idCliente;
	private String nombre;
	private String apellido;
	private String genero;
	private Integer edad;
	private String identificacion;
	private String direccion;
	private String telefono;
	private String estado;
	private String contrasena;
}
