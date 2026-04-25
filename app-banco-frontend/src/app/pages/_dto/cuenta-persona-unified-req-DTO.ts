/**
 * DTO CuentaPersonaUnifiedReqDTO
 * @autor Juan Maigua <maiguarizocarlos@gmail.com>
 * @since 22/05/2026
 * @version 1.0
 */
export interface CuentaPersonaUnifiedReqDTO {
	idCuenta : number;
	tipoCuentaId : number;
	personaId : number;
	nombre : string;
	apellido : string;
	identificacion : string;
	saldoInicial : number;
	numeroCuenta : string;
	nombreTipoCuenta : string;
}

