package com.personclientaccountmovement.repository.postgres.utils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.personclientaccountmovement.repository.postgres.dao.InfoClienteDAO;
import com.personclientaccountmovement.repository.postgres.entity.InfoCliente;
import com.personclientaccountmovement.cons.PersonClientAccountMovementConfigConstants;
import com.personclientaccountmovement.dto.InfoClienteReqDTO;
import com.personclientaccountmovement.dto.InfoClienteResDTO;
import com.personclientaccountmovement.dto.ObjectIdDTO;
import com.personclientaccountmovement.dto.PersonaClienteUnifiedReqDTO;
import com.personclientaccountmovement.exception.GenericException;
import com.personclientaccountmovement.enums.EnumStatus;

/**
* Validaciones de negocio sobre InfoCliente.
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Component
public class InfoClienteValidators {

	@Autowired
	private InfoClienteDAO infoClienteDAO;

	@Autowired
	private InfoClienteConverter infoClienteConverter;

	public void validarGuardarInfoCliente(InfoClienteReqDTO request) throws GenericException {
		if (request.getPersonaCreacion() == null) {
			throw new GenericException(PersonClientConfigConstants.MSG_MISSING_CREATION_USER,
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
		if (request.getFechaCreacion() == null) {
			throw new GenericException(PersonClientConfigConstants.MSG_MISSING_CREATION_DATE,
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
	}

	public InfoClienteReqDTO validarActualizarInfoCliente(InfoClienteReqDTO requestDTO) throws GenericException {
		if (requestDTO.getIdCliente() == null) {
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
		Optional<InfoCliente> optionalInfoCliente = infoClienteDAO.findById(requestDTO.getIdCliente());
		InfoCliente responseEntity = optionalInfoCliente
				.orElse(infoClienteConverter.mappingInfoClienteReqDTOtoInfoClienteEntity(requestDTO));
		return infoClienteConverter.mappingInfoClienteEntitytoInfoClienteReqDTO(responseEntity);
	}

	public InfoCliente validarEntidadInfoCliente(ObjectIdDTO requestDTO) throws GenericException {
		if (requestDTO.getObjectId() == null) {
			throw new GenericException(PersonClientConfigConstants.MSG_ID_REQUIRED,
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
		List<InfoCliente> infoClientes = infoClienteDAO.findByIdClienteAndEstado(requestDTO.getObjectId(), EnumStatus.ACTIVO.toString());
		if (infoClientes == null || infoClientes.isEmpty()) {
			throw new GenericException(
					String.format(PersonClientConfigConstants.MSG_ID_NO_EXISTE, +requestDTO.getObjectId()),
					PersonClientAccountMovementConfigConstants.EXISTING_VALUES);
		}
		Optional<InfoCliente> optionalInfoCliente = infoClienteDAO.findById(requestDTO.getObjectId());
		return optionalInfoCliente.orElse(null);
	}


	public void validarInfoCliente(InfoClienteReqDTO request) throws GenericException {
		InfoClienteReqDTO objNull = new InfoClienteReqDTO();
		if (request.equals(objNull)) {
			throw new GenericException(PersonClientConfigConstants.MSG_PARAMETER_REQUIRED,
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
	}

	public void validarInfoClienteUnificado(PersonaClienteUnifiedReqDTO request) throws GenericException {
		if (request == null) {
			throw new GenericException(PersonClientConfigConstants.MSG_PARAMETER_REQUIRED,
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
		PersonaClienteUnifiedReqDTO vacio = PersonaClienteUnifiedReqDTO.builder().build();
		if (request.equals(vacio)) {
			throw new GenericException(PersonClientConfigConstants.MSG_PARAMETER_REQUIRED,
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
	}

	public InfoClienteResDTO resolverClienteObjetivo(List<InfoClienteResDTO> clientes, Long idCliente)
			throws GenericException {
		if (clientes.isEmpty()) {
			throw new GenericException("No hay registros de cliente para este persona",
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
		if (idCliente != null) {
			for (InfoClienteResDTO c : clientes) {
				if (idCliente.equals(c.getIdCliente())) {
					return c;
				}
			}
			throw new GenericException("idCliente no pertenece al persona indicado",
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
		if (clientes.size() > 1) {
			throw new GenericException("Hay varios clientes para el persona; indique idCliente en el payload",
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
		return clientes.get(0);
	}

	public boolean contrasenaCambia(InfoClienteResDTO clienteDb, PersonaClienteUnifiedReqDTO unified) {
		return unified.getContrasena() != null && !unified.getContrasena().isBlank()
					&& !Objects.equals(clienteDb.getContrasena(), unified.getContrasena());
	}
}
