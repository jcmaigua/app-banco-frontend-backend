package com.personclientaccountmovement.repository.postgres.utils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.personclientaccountmovement.cons.PersonClientAccountMovementConfigConstants;
import com.personclientaccountmovement.dto.InfoPersonaReqDTO;
import com.personclientaccountmovement.dto.InfoPersonaResDTO;
import com.personclientaccountmovement.dto.ObjectIdDTO;
import com.personclientaccountmovement.dto.PersonaClienteUnifiedReqDTO;
import com.personclientaccountmovement.enums.EnumStatus;
import com.personclientaccountmovement.exception.GenericException;
import com.personclientaccountmovement.repository.postgres.dao.InfoPersonaDAO;
import com.personclientaccountmovement.repository.postgres.entity.InfoPersona;


/**
* Clase donde se encuentran las validaciones de los resources
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Component
public class InfoPersonaValidators {

	@Autowired
	InfoPersonaDAO infoPersonaDAO;

	@Autowired
	InfoPersonaConverter infoPersonaConverter;

	/**
	 * Métodos para validar InfoPersona
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	public void validarGuardarInfoPersona(InfoPersonaReqDTO request) throws GenericException {
		if (request.getIdentificacion() != null && !request.getIdentificacion().isBlank()) {
			List<InfoPersona> duplicadosIdentificacion = infoPersonaDAO
					.findByIdentificacionAndEstado(request.getIdentificacion(), EnumStatus.ACTIVO.toString());
			if (!duplicadosIdentificacion.isEmpty()) {
				throw new GenericException("La identificación ya existe",
						PersonClientAccountMovementConfigConstants.MISSING_VALUES);
			}
		}

		if (request.getNombre() != null && request.getApellido() != null) {
			List<InfoPersona> listInfoPersonaDatos = infoPersonaDAO.findByNombreAndApellidoAndEstado(
					request.getNombre(), request.getApellido(), EnumStatus.ACTIVO.toString());
			if (!listInfoPersonaDatos.isEmpty()) {
				throw new GenericException("El nombre y apellido del persona ya existe",
						PersonClientAccountMovementConfigConstants.MISSING_VALUES);
			}
		}

		if(request.getNombre() == null || request.getNombre().isBlank()) {
			throw new GenericException("El nombre es obligatorio",
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
		if(request.getApellido() == null || request.getApellido().isBlank()) {
			throw new GenericException("El apellido es obligatorio",
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
		if(request.getIdentificacion() == null || request.getIdentificacion().isBlank()) {
			throw new GenericException("La identificación es obligatoria",
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
		if(request.getDireccion() == null || request.getDireccion().isBlank()) {
			throw new GenericException("La dirección es obligatoria",
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
		if(request.getTelefono() == null || request.getTelefono().isBlank()) {
			throw new GenericException("El teléfono es obligatorio",
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
		if(request.getEdad() == null) {
			throw new GenericException("La edad es obligatoria",
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
		String creacionUser = request.getPersonaRequest() != null && !request.getPersonaRequest().isBlank()
				? request.getPersonaRequest()
				: request.getPersonaCreacion();
		if (creacionUser == null || creacionUser.isBlank()) {
			throw new GenericException(PersonClientConfigConstants.MSG_MISSING_CREATION_USER,
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
	}


	public void validarActualizarPersonaClienteUnifiedReqDTO(PersonaClienteUnifiedReqDTO request) throws GenericException {
		if(request.getIdPersona() == null) {
			throw new GenericException("El id del persona es obligatorio",
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
		if(request.getIdCliente() == null) {
			throw new GenericException("El id del cliente es obligatorio",
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
		validarPersonaClienteUnifiedReqDTO(request);
	}

    public void validarPersonaClienteUnifiedReqDTO(PersonaClienteUnifiedReqDTO request) throws GenericException {
		if(request.getNombre() == null || request.getNombre().isBlank()) {
			throw new GenericException("El nombre es obligatorio",
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
		if(!request.getNombre().matches("\\p{L}+( \\p{L}+)*")) {
			throw new GenericException("El nombre debe contener solo letras",
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
		if(request.getApellido() == null || request.getApellido().isBlank()) {
			throw new GenericException("El apellido es obligatorio",
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
		if(request.getIdentificacion() == null || request.getIdentificacion().isBlank()) {
			throw new GenericException("La identificación es obligatoria",
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
		if(!request.getApellido().matches("\\p{L}+( \\p{L}+)*")) {
			throw new GenericException("El apellido debe contener solo letras",
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
		if(request.getDireccion() == null || request.getDireccion().isBlank()) {
			throw new GenericException("La dirección es obligatoria",
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
		if(request.getTelefono() == null || request.getTelefono().isBlank()) {
			throw new GenericException("El teléfono es obligatorio",
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
		if(request.getEdad() == null || request.getEdad() == 0) {
			throw new GenericException("La edad es obligatoria",
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
		if(request.getGenero() == null || request.getGenero().isBlank()) {
			throw new GenericException("El género es obligatorio",
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
		if(request.getContrasena() == null || request.getContrasena().isBlank()) {
			throw new GenericException("La contraseña es obligatoria",
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
		if(request.getEdad() < 0 || request.getEdad() >= 200) {
			throw new GenericException("La edad debe estar entre 1 y 200",
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
		if(request.getIdentificacion().length() != 10) {
			throw new GenericException("La identificación debe tener 10 caracteres",
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
		if(request.getTelefono().length() != 9) {
			throw new GenericException("El telefono debe tener 9 caracteres",
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}

		if (!request.getIdentificacion().matches("\\d+")) {
			throw new GenericException("La identificación debe contener solo dígitos",
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		} 

		if (!request.getTelefono().matches("\\d+")) {
			throw new GenericException("La teléfono debe contener solo dígitos",
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		} 

		if(request.getDireccion().length() > 300) {
			throw new GenericException("La dirección debe tener menos de 300 caracteres",
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
		if(request.getContrasena().length() > 200) {
			throw new GenericException("La contraseña debe tener menos de 200 caracteres",
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
		if(request.getContrasena().length() < 4) {
			throw new GenericException("La contraseña es muy corta",
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
		if(!request.getGenero().equals("Masculino") && !request.getGenero().equals("Femenino")) {
			throw new GenericException("El género debe ser Femenino o Masculino",
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
	}

	public InfoPersonaReqDTO validarActualizarInfoPersona(InfoPersonaReqDTO requestDTO)
			throws GenericException {
		if (requestDTO.getIdPersona() == null) {
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
		Optional<InfoPersona> optionalInfoPersona= infoPersonaDAO
				.findById(requestDTO.getIdPersona());
		InfoPersona responseEntity = optionalInfoPersona
				.orElse(infoPersonaConverter.mappingInfoPersonaReqDTOtoInfoPersonaEntity(requestDTO));
		return infoPersonaConverter
				.mappingInfoPersonaEntitytoInfoPersonaReqDTO(responseEntity);
	}

	public InfoPersona validarEntidadInfoPersona(ObjectIdDTO requestDTO) throws GenericException {
		if (requestDTO.getObjectId() == null) {
			throw new GenericException(PersonClientConfigConstants.MSG_ID_REQUIRED,
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}

		List<InfoPersona> infoPersonas = infoPersonaDAO.findByIdPersonaAndEstado(requestDTO.getObjectId(), EnumStatus.ACTIVO.toString());
		if (infoPersonas == null || infoPersonas.isEmpty()) {
			throw new GenericException(
					String.format(PersonClientConfigConstants.MSG_ID_NO_EXISTE, +requestDTO.getObjectId()),
					PersonClientAccountMovementConfigConstants.EXISTING_VALUES);
		}
		Optional<InfoPersona> optionalInfoPersona = infoPersonaDAO.findById(requestDTO.getObjectId());
		return optionalInfoPersona.orElse(null);
	}

	public void validarInfoPersona(InfoPersonaReqDTO request) throws GenericException {
		InfoPersonaReqDTO objNull = new InfoPersonaReqDTO();
		if (request.equals(objNull)) {
			throw new GenericException(PersonClientConfigConstants.MSG_PARAMETER_REQUIRED,
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
	}

	public boolean personaPerfilCambia(InfoPersonaResDTO db, PersonaClienteUnifiedReqDTO in) {
		return !Objects.equals(db.getNombre(), in.getNombre())
				|| !Objects.equals(db.getApellido(), in.getApellido())
				|| !Objects.equals(db.getGenero(), in.getGenero())
				|| !Objects.equals(db.getEdad(), in.getEdad())
				|| !Objects.equals(db.getIdentificacion(), in.getIdentificacion())
				|| !Objects.equals(db.getDireccion(), in.getDireccion())
				|| !Objects.equals(db.getTelefono(), in.getTelefono())
				|| (in.getEstado() != null && !in.getEstado().isBlank()
						&& !Objects.equals(db.getEstado(), in.getEstado()));
	}


}
