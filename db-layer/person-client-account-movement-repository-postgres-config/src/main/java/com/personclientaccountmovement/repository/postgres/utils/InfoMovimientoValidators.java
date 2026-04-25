package com.personclientaccountmovement.repository.postgres.utils;

import java.util.List;
import java.util.Optional;
import java.util.Date;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.personclientaccountmovement.cons.PersonClientAccountMovementConfigConstants;
import com.personclientaccountmovement.dto.InfoMovimientoReqDTO;
import com.personclientaccountmovement.dto.ObjectIdDTO;
import com.personclientaccountmovement.enums.EnumStatus;
import com.personclientaccountmovement.exception.GenericException;
import com.personclientaccountmovement.repository.postgres.dao.InfoMovimientoDAO;
import com.personclientaccountmovement.repository.postgres.entity.InfoMovimiento;
import com.personclientaccountmovement.repository.postgres.dao.InfoCuentaDAO;
import com.personclientaccountmovement.repository.postgres.entity.InfoCuenta;
import com.personclientaccountmovement.dto.InfoMovimientoReporteReqDTO;

/**
* Clase donde se encuentran las validaciones de los resources
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Component
public class InfoMovimientoValidators {

	@Autowired
	InfoMovimientoDAO infoMovimientoDAO;

	@Autowired
	InfoCuentaDAO infoCuentaDAO;

	@Autowired
	InfoMovimientoConverter infoMovimientoConverter;

	private static final BigDecimal SALDO_INICIAL_MAXIMO = new BigDecimal("100000");



	/**
	 * Métodos para validar InfoMovimiento
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	public InfoMovimiento validarGuardarInfoMovimiento(InfoMovimientoReqDTO request) throws GenericException {
		InfoMovimiento infoMovimiento = null;
		if(request.getValor() == null){
			throw new GenericException("El valor del movimiento es requerido",
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);

		}
		if (request.getPersonaCreacion() == null) {
			throw new GenericException(PersonClientConfigConstants.MSG_MISSING_CREATION_USER,
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
		if (request.getFechaCreacion() == null) {
			throw new GenericException(PersonClientConfigConstants.MSG_MISSING_CREATION_DATE,
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
		return infoMovimiento;
	}

	public InfoMovimiento validarActualizarInfoMovimiento(InfoMovimientoReqDTO requestDTO)
			throws GenericException {
		if(requestDTO.getValor() == null){
			throw new GenericException("El valor del movimiento es requerido",
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);

		}
		if (requestDTO.getIdMovimiento() == null) {
			throw new GenericException(PersonClientConfigConstants.MSG_ID_REQUIRED,
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}

		List<InfoMovimiento> movimientoPorIdYCuenta = infoMovimientoDAO.findByIdMovimientoAndCuentaIdAndEstado
												(requestDTO.getIdMovimiento(), requestDTO.getCuentaId(), EnumStatus.ACTIVO.toString());
		if(movimientoPorIdYCuenta == null || movimientoPorIdYCuenta.isEmpty() || movimientoPorIdYCuenta.size() == 0 ) {
			throw new GenericException("El id de movimiento no pertenece a la cuenta", PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}

		List<InfoMovimiento> listInfoMovimiento = infoMovimientoDAO.findLastMovimientoByCuentaId(requestDTO.getCuentaId());

		if(listInfoMovimiento != null && listInfoMovimiento.size() > 0) {
			if(listInfoMovimiento.get(0).getIdMovimiento() != requestDTO.getIdMovimiento()) {
				throw new GenericException("Solo se puede modificar el ultimo movimiento realizado", 
				PersonClientAccountMovementConfigConstants.MISSING_VALUES);
			}
		}

		List<InfoMovimiento> movimientoPorId = infoMovimientoDAO.findByIdMovimientoAndEstado(requestDTO.getIdMovimiento(), EnumStatus.ACTIVO.toString());
		if(movimientoPorId != null && movimientoPorId.size() > 0) {
			return movimientoPorId.get(0);
		} else {
			throw new GenericException("El movimiento no existe", PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
	}

	public InfoMovimiento validarEntidadInfoMovimiento(ObjectIdDTO requestDTO) throws GenericException {
		if (requestDTO.getObjectId() == null) {
			throw new GenericException(PersonClientConfigConstants.MSG_ID_REQUIRED,
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
		if (!infoMovimientoDAO.existsById(requestDTO.getObjectId())) {
			throw new GenericException(
					String.format(PersonClientConfigConstants.MSG_ID_NO_EXISTE, +requestDTO.getObjectId()),
					PersonClientAccountMovementConfigConstants.EXISTING_VALUES);
		}

		Optional<InfoMovimiento> optionalInfoMovimiento = infoMovimientoDAO.findById(requestDTO.getObjectId());
		return optionalInfoMovimiento.orElse(null);
	}

	public void validarInfoMovimiento(InfoMovimientoReqDTO request) throws GenericException {
		InfoMovimientoReqDTO objNull = new InfoMovimientoReqDTO();
		if (request.equals(objNull)) {
			throw new GenericException(PersonClientConfigConstants.MSG_PARAMETER_REQUIRED,
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
	}

	public void validarSaldoInsuficiente(BigDecimal saldoActual, BigDecimal valor) throws GenericException {
		if(saldoActual.compareTo(valor) < 0) {
					throw new GenericException(PersonClientAccountMovementConfigConstants.SALDO_NO_DISPONIBLE, PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
	}

	public void validarMaximoRetiroDiario(Long idCuenta, BigDecimal maximo, BigDecimal valor) throws GenericException {
		LocalDate hoy = LocalDate.now();

		LocalDateTime inicioDia = hoy.atStartOfDay();
		LocalDateTime finDia = hoy.plusDays(1).atStartOfDay();
		BigDecimal disponible = infoMovimientoDAO.findCantidadRetiroByCuentaId(idCuenta, inicioDia, finDia);
		valor = valor.abs();
		if (valor.compareTo(maximo) > 0) {
			throw new GenericException(
				PersonClientAccountMovementConfigConstants.CUPO_DIARIO_EXCEDIDO,
				PersonClientAccountMovementConfigConstants.MISSING_VALUES
			);
		}

		if (disponible != null) {
			disponible = disponible.abs();

			
			BigDecimal restante = maximo.subtract(disponible);
			if (disponible.compareTo(maximo) >= 0) {
				throw new GenericException(
					PersonClientAccountMovementConfigConstants.CUPO_DIARIO_EXCEDIDO,
					PersonClientAccountMovementConfigConstants.MISSING_VALUES
				);
			}
			if (valor.compareTo(restante) > 0) {
				throw new GenericException(
					PersonClientAccountMovementConfigConstants.CUPO_DIARIO_EXCEDIDO,
					PersonClientAccountMovementConfigConstants.MISSING_VALUES
				);
			}
		}
	}

	public void validarMaximoRetiroDiarioActualiza(Long idCuenta, BigDecimal maximo, BigDecimal valor) throws GenericException {
		LocalDate hoy = LocalDate.now();

		LocalDateTime inicioDia = hoy.atStartOfDay();
		LocalDateTime finDia = hoy.plusDays(1).atStartOfDay();
		BigDecimal disponible = infoMovimientoDAO.findCantidadRetiroByCuentaId(idCuenta, inicioDia, finDia);
		valor = valor.abs();
		if (valor.compareTo(maximo) > 0) {
			throw new GenericException(
				PersonClientAccountMovementConfigConstants.CUPO_DIARIO_EXCEDIDO,
				PersonClientAccountMovementConfigConstants.MISSING_VALUES
			);
		}

		if (disponible != null) {
			disponible = disponible.abs();
			List<InfoMovimiento> listInfoMovimiento = infoMovimientoDAO.findLastMovimientoByCuentaId(idCuenta);
			disponible = disponible.subtract(listInfoMovimiento.get(0).getValor().abs());
			BigDecimal restante = maximo.subtract(disponible);
			if (disponible.compareTo(maximo) >= 0) {
				throw new GenericException(
					PersonClientAccountMovementConfigConstants.CUPO_DIARIO_EXCEDIDO,
					PersonClientAccountMovementConfigConstants.MISSING_VALUES
				);
			}
			if (valor.compareTo(restante) > 0) {
				throw new GenericException(
					PersonClientAccountMovementConfigConstants.CUPO_DIARIO_EXCEDIDO,
					PersonClientAccountMovementConfigConstants.MISSING_VALUES
				);
			}
		}
	}

	public void validarAgregarSaldo(BigDecimal valor) throws GenericException {
		if (valor.compareTo(BigDecimal.ZERO) <= 0) {
			throw new GenericException("El valor del deposito debe ser mayor a 0", 
									PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
		validarSaldo(valor);
	}

	public BigDecimal validarQuitarSaldo(BigDecimal valor) throws GenericException {
		if(valor.compareTo(BigDecimal.ZERO) == 0) {
			throw new GenericException("El valor del retiro no puede ser igual a 0", PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
		validarSaldo(valor);
		return valor.abs().negate();
	}

	public void validarSaldo(BigDecimal valor) throws GenericException {
		if (valor.scale() > 2) {
			throw new GenericException("El valor del saldo inicial es incorrecto, debe tener maximo 2 decimales (0.00)",
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
		if (valor.compareTo(SALDO_INICIAL_MAXIMO) > 0) {
			throw new GenericException("El valor de la transaccion puede ser mayor a 100000",
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
	}

	public InfoCuenta validarEntidadInfoCuentaByNumeroCuenta(InfoMovimientoReqDTO requestDTO) throws GenericException {
		if (requestDTO.getNumeroCuenta() == null || requestDTO.getNumeroCuenta().isEmpty()) {
			throw new GenericException("El numero de cuenta es obligatorio",
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
		List<InfoCuenta> infoCuentas = infoCuentaDAO.findByNumeroCuentaAndEstado(requestDTO.getNumeroCuenta(), EnumStatus.ACTIVO.toString());
		if (infoCuentas == null || infoCuentas.isEmpty()) {
			throw new GenericException(
					String.format("No existe la cuenta " + requestDTO.getNumeroCuenta() + " en el sistema"),
					PersonClientAccountMovementConfigConstants.EXISTING_VALUES);
		}
		return infoCuentas.get(0);
	}

	public InfoCuenta validarEntidadInfoMovimiento(InfoMovimiento requestDTO) throws GenericException {
		if (requestDTO.getCuentaId() == null || requestDTO.getCuentaId() == 0) {
			throw new GenericException("El numero de cuenta es obligatorio",
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
		List<InfoCuenta> infoCuentas = infoCuentaDAO.findByIdCuentaAndEstado(requestDTO.getCuentaId(), EnumStatus.ACTIVO.toString());
		if (infoCuentas == null || infoCuentas.isEmpty()) {
			throw new GenericException(
					String.format("No existe la cuenta " + requestDTO.getCuentaId() + " en el sistema"),
					PersonClientAccountMovementConfigConstants.EXISTING_VALUES);
		}
		return infoCuentas.get(0);
	}

	public void validarInfoMovimientoReporte(InfoMovimientoReporteReqDTO requestDTO) throws GenericException {
		String fechaDesdeStr = requestDTO.getFechaDesde();
		String fechaHastaStr = requestDTO.getFechaHasta();

		if ((fechaDesdeStr != null && fechaHastaStr == null) ||
			(fechaDesdeStr == null && fechaHastaStr != null)) {
			throw new GenericException(
					"Debe ingresar ambas fechas para realizar el filtro por rango",
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
		
		if (fechaDesdeStr != null && !fechaDesdeStr.isEmpty() && fechaHastaStr != null && !fechaHastaStr.isEmpty()) {
			LocalDate desde = parseFecha(fechaDesdeStr);
			LocalDate hasta = parseFecha(fechaHastaStr);

			if (desde.isAfter(hasta)) {
				throw new GenericException(
						"La fecha desde no puede ser mayor a la fecha hasta",
						PersonClientAccountMovementConfigConstants.MISSING_VALUES);
			}
		}
	}

	private LocalDate parseFecha(String fecha) throws GenericException {
		try {
			return LocalDate.parse(fecha);
		} catch (Exception e) {
			throw new GenericException("Formato de fecha inválido, debe ser yyyy-MM-dd",
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
	}

}
