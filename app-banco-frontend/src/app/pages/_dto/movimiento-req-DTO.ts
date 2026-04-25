/**
 * DTO PersonaClienteUnifiedReqDTO
 * @autor Juan Maigua <maiguarizocarlos@gmail.com>
 * @since 22/05/2026
 * @version 1.0
 */
export interface MovimientoReqDTO {
	idMovimiento : number;
	tipoMovimientoId : number;
	tipoMovimientoNombre : string;
	tipoCuentaNombre : string;
	cuentaId : number;
	numeroCuenta : string;
	valor : number;
}