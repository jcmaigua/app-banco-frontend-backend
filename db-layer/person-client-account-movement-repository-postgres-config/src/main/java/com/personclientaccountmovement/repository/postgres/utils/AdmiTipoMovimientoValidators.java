package com.personclientaccountmovement.repository.postgres.utils;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.personclientaccountmovement.cons.PersonClientAccountMovementConfigConstants;
import com.personclientaccountmovement.dto.AdmiTipoMovimientoReqDTO;
import com.personclientaccountmovement.dto.ObjectIdDTO;
import com.personclientaccountmovement.enums.EnumStatus;
import com.personclientaccountmovement.exception.GenericException;
import com.personclientaccountmovement.repository.postgres.dao.AdmiTipoMovimientoDAO;
import com.personclientaccountmovement.repository.postgres.entity.AdmiTipoMovimiento;
import com.personclientaccountmovement.dto.InfoMovimientoReqDTO;

/**
* Clase donde se encuentran las validaciones de los resources
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Component
public class AdmiTipoMovimientoValidators {

	@Autowired
	AdmiTipoMovimientoDAO admiTipoMovimientoDAO;

	@Autowired
	AdmiTipoMovimientoConverter admiTipoMovimientoConverter;

	/**
	 * Métodos para validar AdmiTipoMovimiento
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	public AdmiTipoMovimiento validarGuardarAdmiTipoMovimiento(AdmiTipoMovimientoReqDTO request) throws GenericException {
		AdmiTipoMovimiento admiTipoMovimiento = null;
	
		if (request.getPersonaCreacion() == null) {
			throw new GenericException(PersonClientConfigConstants.MSG_MISSING_CREATION_USER,
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
		if (request.getFechaCreacion() == null) {
			throw new GenericException(PersonClientConfigConstants.MSG_MISSING_CREATION_DATE,
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
		return admiTipoMovimiento;
	}

	public AdmiTipoMovimientoReqDTO validarActualizarAdmiTipoMovimiento(AdmiTipoMovimientoReqDTO requestDTO)
			throws GenericException {
		if (requestDTO.getIdTipoMovimiento() == null) {
			throw new GenericException(PersonClientConfigConstants.MSG_ID_REQUIRED,
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}

		if (requestDTO.getPersonaModificacion() == null) {
			throw new GenericException(PersonClientConfigConstants.MSG_MISSING_CREATION_USER,
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
		if (requestDTO.getFechaModificacion() == null) {
			throw new GenericException(PersonClientConfigConstants.MSG_MISSING_CREATION_DATE,
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
		Optional<AdmiTipoMovimiento> optionalAdmiTipoMovimiento= admiTipoMovimientoDAO
				.findById(requestDTO.getIdTipoMovimiento());
		AdmiTipoMovimiento responseEntity = optionalAdmiTipoMovimiento
				.orElse(admiTipoMovimientoConverter.mappingAdmiTipoMovimientoReqDTOtoAdmiTipoMovimientoEntity(requestDTO));
		return admiTipoMovimientoConverter
				.mappingAdmiTipoMovimientoEntitytoAdmiTipoMovimientoReqDTO(responseEntity);
	}

	public AdmiTipoMovimiento validarEntidadAdmiTipoMovimiento(ObjectIdDTO requestDTO) throws GenericException {
		if (requestDTO.getObjectId() == null) {
			throw new GenericException(PersonClientConfigConstants.MSG_ID_REQUIRED,
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
		if (!admiTipoMovimientoDAO.existsById(requestDTO.getObjectId())) {
			throw new GenericException(
					String.format(PersonClientConfigConstants.MSG_ID_NO_EXISTE, +requestDTO.getObjectId()),
					PersonClientAccountMovementConfigConstants.EXISTING_VALUES);
		}
		Optional<AdmiTipoMovimiento> optionalAdmiTipoMovimiento = admiTipoMovimientoDAO.findById(requestDTO.getObjectId());
		return optionalAdmiTipoMovimiento.orElse(null);
	}

	public void validarAdmiTipoMovimiento(AdmiTipoMovimientoReqDTO request) throws GenericException {
		AdmiTipoMovimientoReqDTO objNull = new AdmiTipoMovimientoReqDTO();
		if (request.equals(objNull)) {
			throw new GenericException(PersonClientConfigConstants.MSG_PARAMETER_REQUIRED,
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
	}


	public AdmiTipoMovimiento validarTipoMovimientoById(InfoMovimientoReqDTO requestDTO)
			throws GenericException {
		if (requestDTO.getTipoMovimientoId() == null || requestDTO.getTipoMovimientoId() == 0) {
			throw new GenericException("El nombre del tipo de movimiento es obligatorio",
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
		List<AdmiTipoMovimiento> admiTipoMovimiento = admiTipoMovimientoDAO.findByIdTipoMovimientoAndEstado(requestDTO.getTipoMovimientoId(), EnumStatus.ACTIVO.toString());
		if (admiTipoMovimiento == null || admiTipoMovimiento.isEmpty()) {
			throw new GenericException(
					String.format("No existe el tipo de movimiento " + requestDTO.getTipoMovimientoId() + " en el sistema"),
					PersonClientAccountMovementConfigConstants.EXISTING_VALUES);
		}
		return admiTipoMovimiento.get(0);
	}
}
