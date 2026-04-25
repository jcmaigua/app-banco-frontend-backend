package com.personclientaccountmovement.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* DTO para persona cliente unidos
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonaClienteUnifiedReqDTO {

	private Long idPersona;
	private Long idCliente;
	private String nombre;
	private String apellido;
	private String genero;
	private Integer edad;
	private String identificacion;
	private String direccion;
	private String telefono;
	private String contrasena;
	private String estado;
	private String userRequest;
}
