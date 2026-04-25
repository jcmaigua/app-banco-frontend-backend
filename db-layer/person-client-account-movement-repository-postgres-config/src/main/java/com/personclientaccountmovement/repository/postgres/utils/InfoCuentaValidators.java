package com.personclientaccountmovement.repository.postgres.utils;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.personclientaccountmovement.cons.PersonClientAccountMovementConfigConstants;
import com.personclientaccountmovement.dto.InfoCuentaPersonaUnifiedReqDTO;
import com.personclientaccountmovement.dto.InfoCuentaReqDTO;
import com.personclientaccountmovement.dto.ObjectIdDTO;
import com.personclientaccountmovement.dto.PersonaClienteUnifiedReqDTO;
import com.personclientaccountmovement.enums.EnumStatus;
import com.personclientaccountmovement.exception.GenericException;
import com.personclientaccountmovement.repository.postgres.dao.InfoCuentaDAO;
import com.personclientaccountmovement.repository.postgres.dao.InfoPersonaDAO;
import com.personclientaccountmovement.repository.postgres.entity.InfoCuenta;

/**
* Clase donde se encuentran las validaciones de los resources
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Component
public class InfoCuentaValidators {

	private static final BigDecimal SALDO_INICIAL_MAXIMO = new BigDecimal("100000");

	@Autowired
	InfoCuentaDAO infoCuentaDAO;

	@Autowired
	InfoPersonaDAO infoPersonaDAO;

	@Autowired
	InfoCuentaConverter infoCuentaConverter;

	/**
	 * Métodos para validar InfoCuenta
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	public InfoCuenta validarGuardarInfoCuenta(InfoCuentaPersonaUnifiedReqDTO request) throws GenericException {
		InfoCuenta infoCuenta = null;
		validarInfoCuentaReqDTO(request);
		List<InfoCuenta> listInfoCuentaNumCuenta = infoCuentaDAO.findByNumeroCuentaAndEstado(request.getNumeroCuenta(), EnumStatus.ACTIVO.toString());
		if (listInfoCuentaNumCuenta.size() > 0) {
			throw new GenericException("El numero de cuenta ya existe",
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
		if (request.getPersonaRequest() == null) {
			throw new GenericException(PersonClientConfigConstants.MSG_MISSING_CREATION_USER,
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
		return infoCuenta;
	}

	public InfoCuenta validarActualizarInfoCuenta(InfoCuentaPersonaUnifiedReqDTO requestDTO)
			throws GenericException {
		if (requestDTO.getIdCuenta() == null) {
			throw new GenericException(PersonClientConfigConstants.MSG_ID_REQUIRED,
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
		if (requestDTO.getPersonaRequest() == null) {
			throw new GenericException(PersonClientConfigConstants.MSG_MISSING_CREATION_USER,
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}

		validarInfoCuentaReqDTO(requestDTO);

		List<InfoCuenta> infoCuentas = infoCuentaDAO.findByIdCuentaAndEstado(requestDTO.getIdCuenta(), EnumStatus.ACTIVO.toString());
		if (infoCuentas == null || infoCuentas.isEmpty()) {
			throw new GenericException(
					String.format(PersonClientConfigConstants.MSG_ID_NO_EXISTE, +requestDTO.getIdCuenta()),
					PersonClientAccountMovementConfigConstants.EXISTING_VALUES);
		}
		return infoCuentas.get(0);
	}

	public void validarInfoCuentaReqDTO(InfoCuentaPersonaUnifiedReqDTO request) throws GenericException {

		if(request.getPersonaId() == null) {
			throw new GenericException("El persona id es obligatorio",
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}

		if(request.getNumeroCuenta() == null || request.getNumeroCuenta().isBlank()) {
			throw new GenericException("El numero de cuenta es obligatorio",
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}

        if(request.getNumeroCuenta().length() < 6 || request.getNumeroCuenta().length() > 6) {
			throw new GenericException("El numero de cuenta debe tener 6 digitos",
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}

		if(request.getTipoCuentaId() == null || request.getTipoCuentaId() == 0) {
			throw new GenericException("El nombre del tipo de cuenta es obligatorio",
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}

		if(request.getSaldoInicial() == null) {
			throw new GenericException("El saldo inicial es obligatorio",
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}

		if (request.getSaldoInicial().compareTo(BigDecimal.ZERO) < 0) {
			throw new GenericException("El saldo inicial debe ser positivo",
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}

		if (request.getSaldoInicial().scale() > 2) {
			throw new GenericException("El valor del saldo inicial es incorrecto, debe tener maximo 2 decimales (0.00)",
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}

		if (request.getSaldoInicial().compareTo(SALDO_INICIAL_MAXIMO) > 0) {
			throw new GenericException("El saldo inicial no puede ser mayor a 100000",
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
	}

	public InfoCuenta validarEntidadInfoCuenta(ObjectIdDTO requestDTO) throws GenericException {
		if (requestDTO.getObjectId() == null) {
			throw new GenericException(PersonClientConfigConstants.MSG_ID_REQUIRED,
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
		List<InfoCuenta> infoCuentas = infoCuentaDAO.findByIdCuentaAndEstado(requestDTO.getObjectId(), EnumStatus.ACTIVO.toString());
		if (infoCuentas == null || infoCuentas.isEmpty()) {
			throw new GenericException(
					String.format(PersonClientConfigConstants.MSG_ID_NO_EXISTE, +requestDTO.getObjectId()),
					PersonClientAccountMovementConfigConstants.EXISTING_VALUES);
		}
		return infoCuentas.get(0);
	}

	public void validarInfoCuenta(InfoCuentaPersonaUnifiedReqDTO request) throws GenericException {
		InfoCuentaPersonaUnifiedReqDTO objNull = new InfoCuentaPersonaUnifiedReqDTO();
		if (request.equals(objNull)) {
			throw new GenericException(PersonClientConfigConstants.MSG_PARAMETER_REQUIRED,
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
	}

	public void validarInfoCuentaByPersona(ObjectIdDTO request) throws GenericException {
		List<InfoCuenta> infoCuentas = infoCuentaDAO.findByPersonaIdAndEstado(request.getObjectId(), EnumStatus.ACTIVO.toString());
		if (infoCuentas != null && infoCuentas.size() > 0) {
			throw new GenericException("El persona tiene cuentas asociadas. Primero debe eliminar las cuentas asociadas antes de eliminar el persona.", PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
	}

    public InfoCuenta validarCuentaTieneSaldo(ObjectIdDTO requestDTO) throws GenericException {
		InfoCuenta infoCuenta = null;
		if (requestDTO.getObjectId() == null) {
			throw new GenericException(PersonClientConfigConstants.MSG_ID_REQUIRED,
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
		List<InfoCuenta> listInfoCuenta = infoCuentaDAO.findByIdCuentaAndEstado(requestDTO.getObjectId(), EnumStatus.ACTIVO.toString());
		if (listInfoCuenta != null && listInfoCuenta.size() > 0) {
			infoCuenta = listInfoCuenta.get(0);
			if(infoCuenta.getSaldoActual().compareTo(BigDecimal.ZERO) != 0) {
				throw new GenericException("La cuenta tiene " + infoCuenta.getSaldoActual() + " de saldo, relice el retiro para poder eliminarla", PersonClientAccountMovementConfigConstants.MISSING_VALUES);
			}
		} else{
			throw new GenericException("La cuenta no existe", PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
		return infoCuenta;
	}

}
