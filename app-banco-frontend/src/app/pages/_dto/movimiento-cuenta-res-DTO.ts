/**
 * DTO PersonaClienteUnifiedResDTO
 * @autor Juan Maigua <maiguarizocarlos@gmail.com>
 * @since 22/05/2026
 * @version 1.0
 */
export interface MovimientoCuentaResDTO {
	idMovimiento : number;
	tipoMovimientoId : number;
	tipoMovimientoNombre : string;
	tipoCuentaNombre : string;
	movimiento : string;
	cuentaId : number;
	numeroCuenta : string;
	valor : number;
	tipoCuentaId : number;
	saldoInicial : number;
	saldoActual : number;
}
