package com.personclientaccountmovement.dto;

import java.io.Serializable;

import lombok.Data;

/**
* DTO Paginación de una entidad
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Data
public class PageDTO<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer page;
	private Integer size;
	private String order;
	private String orderValue;
	private T tabla;
	private String nombreFiltroCons = "";
	private String valorFiltroCons = "";
}
