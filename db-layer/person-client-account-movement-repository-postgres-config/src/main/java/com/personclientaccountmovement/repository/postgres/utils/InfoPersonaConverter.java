package com.personclientaccountmovement.repository.postgres.utils;

import java.util.Date;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.personclientaccountmovement.repository.postgres.entity.InfoPersona;
import com.personclientaccountmovement.dto.InfoPersonaReqDTO;
import com.personclientaccountmovement.dto.InfoPersonaResDTO;
import com.personclientaccountmovement.dto.PersonaClienteUnifiedReqDTO;
import com.personclientaccountmovement.dto.PersonaClienteUnifiedResDTO;
import com.personclientaccountmovement.enums.EnumStatus;

/**
* Conversión entre entidad InfoPersona y DTOs.
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Service
public class InfoPersonaConverter {

        public InfoPersona mappingInfoPersonaReqDTOtoInfoPersonaEntity(
                        InfoPersonaReqDTO requestDTO) {
                InfoPersona infoPersona = new InfoPersona();
                infoPersona.setIdPersona((requestDTO.getIdPersona() != null
                                && requestDTO.getIdPersona() != 0)
                                                ? requestDTO.getIdPersona()
                                                : null);
                infoPersona.setEdad((requestDTO.getEdad() != null
                                && requestDTO.getEdad() != 0)
                                                ? requestDTO.getEdad()
                                                : null);

                infoPersona.setApellido((requestDTO.getApellido() == null || requestDTO.getApellido().isEmpty()
                                || requestDTO.getApellido().trim().isEmpty()) ? null : requestDTO.getApellido());
                infoPersona.setDireccion((requestDTO.getDireccion() == null || requestDTO.getDireccion().isEmpty()
                                || requestDTO.getDireccion().trim().isEmpty()) ? null : requestDTO.getDireccion());
                infoPersona.setGenero((requestDTO.getGenero() == null || requestDTO.getGenero().isEmpty()
                                || requestDTO.getGenero().trim().isEmpty()) ? null : requestDTO.getGenero());
                infoPersona.setIdentificacion((requestDTO.getIdentificacion() == null || requestDTO.getIdentificacion().isEmpty()
                                || requestDTO.getIdentificacion().trim().isEmpty()) ? null : requestDTO.getIdentificacion());
                infoPersona.setNombre((requestDTO.getNombre() == null || requestDTO.getNombre().isEmpty()
                                || requestDTO.getNombre().trim().isEmpty()) ? null : requestDTO.getNombre());
                infoPersona.setTelefono((requestDTO.getTelefono() == null || requestDTO.getTelefono().isEmpty()
                                || requestDTO.getTelefono().trim().isEmpty()) ? null : requestDTO.getTelefono());

                            
                infoPersona.setEstado((requestDTO.getEstado() == null || requestDTO.getEstado().isEmpty()
                                || requestDTO.getEstado().trim().isEmpty()) ?  EnumStatus.ACTIVO.toString(): 
                                requestDTO.getEstado());

                infoPersona.setPersonaCreacion(
                                (requestDTO.getPersonaCreacion() == null || requestDTO.getPersonaCreacion().isEmpty()
                                                || requestDTO.getPersonaCreacion().trim().isEmpty()) ? null
                                                                : requestDTO.getPersonaCreacion());
                                                                
                infoPersona.setFechaCreacion(requestDTO.getFechaCreacion());

                infoPersona.setPersonaModificacion(
                                (requestDTO.getPersonaModificacion() == null || requestDTO.getPersonaModificacion().isEmpty()
                                                || requestDTO.getPersonaModificacion().trim().isEmpty()) ? null
                                                                : requestDTO.getPersonaModificacion());

                infoPersona.setFechaModificacion(requestDTO.getFechaModificacion());

                return infoPersona;
        }

        public InfoPersonaReqDTO mappingInfoPersonaEntitytoInfoPersonaReqDTO(
                        InfoPersona response) {
                InfoPersonaReqDTO infoPersonaDTO = new InfoPersonaReqDTO();
                infoPersonaDTO
                                .setIdPersona((response.getIdPersona() != null
                                                && response.getIdPersona() != 0) ? response.getIdPersona()
                                                                : null);

                infoPersonaDTO.setApellido(response.getApellido());
                infoPersonaDTO.setDireccion(response.getDireccion());
                infoPersonaDTO.setEdad(response.getEdad());
                infoPersonaDTO.setGenero(response.getGenero());
                infoPersonaDTO.setIdentificacion(response.getIdentificacion());
                infoPersonaDTO.setNombre(response.getNombre());
                infoPersonaDTO.setTelefono(response.getTelefono());

                infoPersonaDTO.setEstado(response.getEstado());
                infoPersonaDTO.setPersonaCreacion(response.getPersonaCreacion());
                infoPersonaDTO.setFechaCreacion(response.getFechaCreacion());
                infoPersonaDTO.setPersonaModificacion(response.getPersonaModificacion());
                infoPersonaDTO.setFechaModificacion(response.getFechaModificacion());
                return infoPersonaDTO;
        }

        public InfoPersonaResDTO mappingInfoPersonaEntitytoInfoPersonaResDTO(
                        InfoPersona response) {
                InfoPersonaResDTO infoPersonaDTO = new InfoPersonaResDTO();
                infoPersonaDTO
                                .setIdPersona((response.getIdPersona() != null
                                                && response.getIdPersona() != 0) ? response.getIdPersona()
                                                                : null);

                infoPersonaDTO.setApellido(response.getApellido());
                infoPersonaDTO.setDireccion(response.getDireccion());
                infoPersonaDTO.setEdad(response.getEdad());
                infoPersonaDTO.setGenero(response.getGenero());
                infoPersonaDTO.setIdentificacion(response.getIdentificacion());
                infoPersonaDTO.setNombre(response.getNombre());
                infoPersonaDTO.setTelefono(response.getTelefono());

                infoPersonaDTO.setEstado(response.getEstado());
                infoPersonaDTO.setPersonaCreacion(response.getPersonaCreacion());
                infoPersonaDTO.setFechaCreacion(response.getFechaCreacion());
                infoPersonaDTO.setPersonaModificacion(response.getPersonaModificacion());
                infoPersonaDTO.setFechaModificacion(response.getFechaModificacion());
                return infoPersonaDTO;
        }


        public InfoPersonaReqDTO toInfoPersonaReqForUnifiedAlta(PersonaClienteUnifiedReqDTO unified) {
                String actor = unified.getUserRequest() != null && !unified.getUserRequest().isBlank()
                                ? unified.getUserRequest()
                                : null;
                InfoPersonaReqDTO personaReq = InfoPersonaReqDTO.builder()
                                .nombre(unified.getNombre())
                                .apellido(unified.getApellido())
                                .genero(unified.getGenero())
                                .edad(unified.getEdad())
                                .identificacion(unified.getIdentificacion())
                                .direccion(unified.getDireccion())
                                .telefono(unified.getTelefono())
                                .build();
                return personaReq;
        }

/**
* Actualización unificada: mezcla payload plano sobre el persona ya persistido.
*/
        public InfoPersonaReqDTO toInfoPersonaReqForUnifiedUpdate(InfoPersonaResDTO db,
                        PersonaClienteUnifiedReqDTO in) {
                String estado = in.getEstado() != null && !in.getEstado().isBlank() ? in.getEstado() : db.getEstado();
                return InfoPersonaReqDTO.builder()
                                .idPersona(db.getIdPersona())
                                .nombre(Objects.requireNonNullElse(in.getNombre(), db.getNombre()))
                                .apellido(Objects.requireNonNullElse(in.getApellido(), db.getApellido()))
                                .genero(Objects.requireNonNullElse(in.getGenero(), db.getGenero()))
                                .edad(Objects.requireNonNullElse(in.getEdad(), db.getEdad()))
                                .identificacion(Objects.requireNonNullElse(in.getIdentificacion(), db.getIdentificacion()))
                                .direccion(Objects.requireNonNullElse(in.getDireccion(), db.getDireccion()))
                                .telefono(Objects.requireNonNullElse(in.getTelefono(), db.getTelefono()))
                                .estado(estado)
                                .fechaCreacion(db.getFechaCreacion())
                                .personaCreacion(db.getPersonaCreacion())
                                .fechaModificacion(new Date())
                                .personaModificacion(in.getUserRequest())
                                .build();
        }

       
        public PersonaClienteUnifiedResDTO toPersonaClienteUnifiedRes(InfoPersonaResDTO personaRes, Long idCliente,
                        String contrasena) {
                return PersonaClienteUnifiedResDTO.builder()
                                .idPersona(personaRes.getIdPersona())
                                .idCliente(idCliente)
                                .nombre(personaRes.getNombre())
                                .apellido(personaRes.getApellido())
                                .genero(personaRes.getGenero())
                                .edad(personaRes.getEdad())
                                .identificacion(personaRes.getIdentificacion())
                                .direccion(personaRes.getDireccion())
                                .telefono(personaRes.getTelefono())
                                .estado(personaRes.getEstado())
                                .contrasena(contrasena)
                                .build();
        }

        
}
