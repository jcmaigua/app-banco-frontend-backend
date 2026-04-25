/**
 * DTO CuentaPersonaUnifiedResDTO
 * @autor Juan Maigua <maiguarizocarlos@gmail.com>
 * @since 22/05/2026
 * @version 1.0
 */
export interface CuentaPersonaUnifiedResDTO {
	idCuenta : number;
	tipoCuentaId : number;
	numeroCuenta : string;
	nombreTipoCuenta : string;
	personaId: number;
	identificacion : string;
	nombre : string;
	apellido : string;
	saldoInicial : number;
	saldoActual : number;
	estado : string;
}
