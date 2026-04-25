package com.personclientaccountmovement.repository.postgres.utils;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.personclientaccountmovement.repository.postgres.entity.InfoCliente;
import com.personclientaccountmovement.repository.postgres.entity.InfoPersona;
import com.personclientaccountmovement.dto.InfoClienteReqDTO;
import com.personclientaccountmovement.dto.InfoClienteResDTO;
import com.personclientaccountmovement.dto.InfoPersonaResDTO;
import com.personclientaccountmovement.dto.PersonaClienteUnifiedReqDTO;
import com.personclientaccountmovement.dto.PersonaClienteUnifiedResDTO;
import com.personclientaccountmovement.enums.EnumStatus;

/**
* Conversión entre entidad InfoCliente y DTOs.
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Service
public class InfoClienteConverter {

	public InfoCliente mappingInfoClienteReqDTOtoInfoClienteEntity(InfoClienteReqDTO requestDTO) {
		InfoCliente infoCliente = new InfoCliente();
		infoCliente.setIdCliente(
				(requestDTO.getIdCliente() != null && requestDTO.getIdCliente() != 0) ? requestDTO.getIdCliente()
						: null);
		infoCliente.setPersonaId(
				(requestDTO.getPersonaId() != null && requestDTO.getPersonaId() != 0) ? requestDTO.getPersonaId()
						: null);

		infoCliente.setContrasena((requestDTO.getContrasena() == null || requestDTO.getContrasena().isEmpty()
				|| requestDTO.getContrasena().trim().isEmpty()) ? null : requestDTO.getContrasena());

		infoCliente.setEstado((requestDTO.getEstado() == null || requestDTO.getEstado().isEmpty()
				|| requestDTO.getEstado().trim().isEmpty()) ? EnumStatus.ACTIVO.toString() : requestDTO.getEstado());

		infoCliente.setPersonaCreacion(
				(requestDTO.getPersonaCreacion() == null || requestDTO.getPersonaCreacion().isEmpty()
						|| requestDTO.getPersonaCreacion().trim().isEmpty()) ? null : requestDTO.getPersonaCreacion());

		infoCliente.setFechaCreacion(requestDTO.getFechaCreacion());

		infoCliente.setPersonaModificacion(
				(requestDTO.getPersonaModificacion() == null || requestDTO.getPersonaModificacion().isEmpty()
						|| requestDTO.getPersonaModificacion().trim().isEmpty()) ? null
						: requestDTO.getPersonaModificacion());

		infoCliente.setFechaModificacion(requestDTO.getFechaModificacion());

		return infoCliente;
	}


	public InfoCliente mappingPersonaClienteUnifiedReqDTOtoInfoClienteEntity(PersonaClienteUnifiedReqDTO requestDTO) {
		InfoCliente infoCliente = new InfoCliente();
		InfoPersona infoPersona = new InfoPersona();

        infoPersona.setNombre((requestDTO.getNombre() == null || requestDTO.getNombre().isEmpty()
				|| requestDTO.getNombre().trim().isEmpty()) ? null : requestDTO.getNombre());

        infoPersona.setApellido((requestDTO.getApellido() == null || requestDTO.getApellido().isEmpty()
				|| requestDTO.getApellido().trim().isEmpty()) ? null : requestDTO.getApellido());

        infoPersona.setIdentificacion((requestDTO.getIdentificacion() == null || requestDTO.getIdentificacion().isEmpty()
				|| requestDTO.getIdentificacion().trim().isEmpty()) ? null : requestDTO.getIdentificacion());
	
        infoPersona.setDireccion((requestDTO.getDireccion() == null || requestDTO.getDireccion().isEmpty()
				|| requestDTO.getDireccion().trim().isEmpty()) ? null : requestDTO.getDireccion());

        infoPersona.setTelefono((requestDTO.getTelefono() == null || requestDTO.getTelefono().isEmpty()
				|| requestDTO.getTelefono().trim().isEmpty()) ? null : requestDTO.getTelefono());

        infoPersona.setEstado((requestDTO.getEstado() == null || requestDTO.getEstado().isEmpty()
				|| requestDTO.getEstado().trim().isEmpty()) ? EnumStatus.ACTIVO.toString() : requestDTO.getEstado());

		infoCliente.setEstado((requestDTO.getEstado() == null || requestDTO.getEstado().isEmpty()
				|| requestDTO.getEstado().trim().isEmpty()) ? EnumStatus.ACTIVO.toString() : requestDTO.getEstado());

        infoCliente.setInfoPersona(infoPersona);

		return infoCliente;
	}

	public InfoClienteReqDTO mappingInfoClienteEntitytoInfoClienteReqDTO(InfoCliente response) {
		InfoClienteReqDTO infoClienteDTO = new InfoClienteReqDTO();
		infoClienteDTO.setIdCliente(
				(response.getIdCliente() != null && response.getIdCliente() != 0) ? response.getIdCliente() : null);

		infoClienteDTO.setPersonaId(response.getPersonaId());
		infoClienteDTO.setContrasena(response.getContrasena());

		infoClienteDTO.setEstado(response.getEstado());
		infoClienteDTO.setPersonaCreacion(response.getPersonaCreacion());
		infoClienteDTO.setFechaCreacion(response.getFechaCreacion());
		infoClienteDTO.setPersonaModificacion(response.getPersonaModificacion());
		infoClienteDTO.setFechaModificacion(response.getFechaModificacion());
		return infoClienteDTO;
	}

	public InfoClienteResDTO mappingInfoClienteEntitytoInfoClienteResDTO(InfoCliente response) {
		InfoClienteResDTO infoClienteDTO = new InfoClienteResDTO();
		infoClienteDTO.setIdCliente(
				(response.getIdCliente() != null && response.getIdCliente() != 0) ? response.getIdCliente() : null);

		infoClienteDTO.setPersonaId(response.getPersonaId());
		infoClienteDTO.setContrasena(response.getContrasena());

		infoClienteDTO.setEstado(response.getEstado());
		infoClienteDTO.setPersonaCreacion(response.getPersonaCreacion());
		infoClienteDTO.setFechaCreacion(response.getFechaCreacion());
		infoClienteDTO.setPersonaModificacion(response.getPersonaModificacion());
		infoClienteDTO.setFechaModificacion(response.getFechaModificacion());
		return infoClienteDTO;
	}

	public PersonaClienteUnifiedResDTO mappingInfoClienteEntitytoPersonaClienteUnifiedResDTO(InfoCliente response) {
		return PersonaClienteUnifiedResDTO.builder()
                                .idPersona(response.getInfoPersona().getIdPersona())
                                .idCliente(response.getIdCliente())
                                .nombre(response.getInfoPersona().getNombre())
                                .apellido(response.getInfoPersona().getApellido())
                                .genero(response.getInfoPersona().getGenero())
                                .edad(response.getInfoPersona().getEdad())
                                .identificacion(response.getInfoPersona().getIdentificacion())
                                .direccion(response.getInfoPersona().getDireccion())
                                .telefono(response.getInfoPersona().getTelefono())
                                .estado(response.getInfoPersona().getEstado())
                                .contrasena(response.getContrasena())
                                .build();
	}


	public InfoClienteReqDTO mappingPersonaClienteUnifiedReqDTOtoInfoClienteReqDTO(InfoPersonaResDTO personaRes,
			PersonaClienteUnifiedReqDTO unified) {
		return InfoClienteReqDTO.builder()
				.personaId(personaRes.getIdPersona())
				.contrasena(unified.getContrasena())
				.estado(EnumStatus.ACTIVO.toString())
				.personaCreacion(unified.getUserRequest())
				.fechaCreacion(new Date())
				.build();
	}

	public InfoClienteReqDTO mappingPersonaClienteUnifiedReqDTOtoInfoClienteReqDTO(InfoClienteResDTO clienteDb,
			PersonaClienteUnifiedReqDTO unified) {
		return InfoClienteReqDTO.builder()
				.idCliente(clienteDb.getIdCliente())
				.personaId(clienteDb.getPersonaId())
				.contrasena(unified.getContrasena())
				.estado(clienteDb.getEstado())
				.fechaCreacion(clienteDb.getFechaCreacion())
				.personaCreacion(clienteDb.getPersonaCreacion())
				.fechaModificacion(new Date())
				.personaModificacion(unified.getUserRequest())
				.build();
	}

}
