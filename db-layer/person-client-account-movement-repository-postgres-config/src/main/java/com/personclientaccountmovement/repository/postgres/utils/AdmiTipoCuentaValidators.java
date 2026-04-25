package com.personclientaccountmovement.repository.postgres.utils;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.personclientaccountmovement.cons.PersonClientAccountMovementConfigConstants;
import com.personclientaccountmovement.dto.AdmiTipoCuentaReqDTO;
import com.personclientaccountmovement.dto.ObjectIdDTO;
import com.personclientaccountmovement.enums.EnumStatus;
import com.personclientaccountmovement.exception.GenericException;
import com.personclientaccountmovement.repository.postgres.dao.AdmiTipoCuentaDAO;
import com.personclientaccountmovement.repository.postgres.entity.AdmiTipoCuenta;

/**
* Clase donde se encuentran las validaciones de los resources
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Component
public class AdmiTipoCuentaValidators {

	@Autowired
	AdmiTipoCuentaDAO admiTipoCuentaDAO;

	@Autowired
	AdmiTipoCuentaConverter admiTipoCuentaConverter;

	/**
	 * Métodos para validar AdmiTipoCuenta
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	public AdmiTipoCuenta validarGuardarAdmiTipoCuenta(AdmiTipoCuentaReqDTO request) throws GenericException {
		AdmiTipoCuenta admiTipoCuenta = null;
	
		if (request.getPersonaCreacion() == null) {
			throw new GenericException(PersonClientConfigConstants.MSG_MISSING_CREATION_USER,
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
		if (request.getFechaCreacion() == null) {
			throw new GenericException(PersonClientConfigConstants.MSG_MISSING_CREATION_DATE,
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
		return admiTipoCuenta;
	}

	public AdmiTipoCuentaReqDTO validarActualizarAdmiTipoCuenta(AdmiTipoCuentaReqDTO requestDTO)
			throws GenericException {
		if (requestDTO.getIdTipoCuenta() == null) {
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
		Optional<AdmiTipoCuenta> optionalAdmiTipoCuenta= admiTipoCuentaDAO
				.findById(requestDTO.getIdTipoCuenta());
		AdmiTipoCuenta responseEntity = optionalAdmiTipoCuenta
				.orElse(admiTipoCuentaConverter.mappingAdmiTipoCuentaReqDTOtoAdmiTipoCuentaEntity(requestDTO));
		return admiTipoCuentaConverter
				.mappingAdmiTipoCuentaEntitytoAdmiTipoCuentaReqDTO(responseEntity);
	}

	public AdmiTipoCuenta validarEntidadAdmiTipoCuenta(ObjectIdDTO requestDTO) throws GenericException {
		if (requestDTO.getObjectId() == null) {
			throw new GenericException(PersonClientConfigConstants.MSG_ID_REQUIRED,
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
		if (!admiTipoCuentaDAO.existsById(requestDTO.getObjectId())) {
			throw new GenericException(
					String.format(PersonClientConfigConstants.MSG_ID_NO_EXISTE, +requestDTO.getObjectId()),
					PersonClientAccountMovementConfigConstants.EXISTING_VALUES);
		}
		Optional<AdmiTipoCuenta> optionalAdmiTipoCuenta = admiTipoCuentaDAO.findById(requestDTO.getObjectId());
		return optionalAdmiTipoCuenta.orElse(null);
	}

	public void validarAdmiTipoCuenta(AdmiTipoCuentaReqDTO request) throws GenericException {
		AdmiTipoCuentaReqDTO objNull = new AdmiTipoCuentaReqDTO();
		if (request.equals(objNull)) {
			throw new GenericException(PersonClientConfigConstants.MSG_PARAMETER_REQUIRED,
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
	}


}
