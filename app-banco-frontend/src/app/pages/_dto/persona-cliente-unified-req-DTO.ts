/**
 * DTO PersonaClienteUnifiedReqDTO
 * @autor Juan Maigua <maiguarizocarlos@gmail.com>
 * @since 22/05/2026
 * @version 1.0
 */
export interface PersonaClienteUnifiedReqDTO {
	idPersona : number;
	idCliente : number;
	nombre : string;
	apellido : string;
	genero : string;
	edad : number;
	identificacion : string;
	direccion : string;
	telefono : string;
	contrasena : string;
}