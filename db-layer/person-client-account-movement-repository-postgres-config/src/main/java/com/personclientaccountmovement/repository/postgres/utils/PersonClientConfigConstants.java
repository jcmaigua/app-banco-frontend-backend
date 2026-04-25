package com.personclientaccountmovement.repository.postgres.utils;

/**
* Mensajes y límites usados por validaciones del microservicio clientes (person-client DB).
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial 
* @since 15/04/2026
*/
public abstract class PersonClientConfigConstants {
	private PersonClientConfigConstants() {
	}

	public static final Integer MAXIMO_DATA_LIST = 5000;

	public static final String MSG_MISSING_CREATION_USER = "El valor 'CreationUser' es obligatorio.";
	public static final String MSG_MISSING_CREATION_DATE = "La fecha de creación es obligatoria.";
	public static final String MSG_ID_REQUIRED = "El ID es obligatorio.";
	public static final String MSG_ID_NO_EXISTE = "El ID %d no existe.";
	public static final String MSG_PARAMETER_REQUIRED = "Se deben declarar variables para filtrar la lista de detalles de parámetros.";
	public static final String MSG_DATOS_LISTA_MAXIMO_SUPERADO = "El número de datos que responde la lista supera los %d datos permitidos. Por favor, utilice el proceso de filtrado.";
	public static final String MSG_PAGE_REQUERIDO = "El valor 'page' es requerido.";
	public static final String MSG_PAGE_POSITIVO = "El valor 'page' debe ser positivo o comenzar desde 0.";
	public static final String MSG_SIZE_REQUERIDO = "El valor 'size' es requerido.";
	public static final String MSG_SIZE_POSITIVO = "El valor 'size' debe ser positivo o comenzar desde 0.";
	public static final String MSG_TABLA_REQUERIDO = "El valor 'tabla' es requerido.";
	public static final String MSG_ORDER_ASC_DESC = "El valor 'order' debe ser 'ASC' o 'DESC'.";
}
