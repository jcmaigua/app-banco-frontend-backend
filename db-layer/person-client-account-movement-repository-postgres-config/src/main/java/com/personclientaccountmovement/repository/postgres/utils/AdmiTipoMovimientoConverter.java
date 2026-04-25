package com.personclientaccountmovement.repository.postgres.utils;

import org.springframework.stereotype.Service;

import com.personclientaccountmovement.dto.AdmiTipoMovimientoReqDTO;
import com.personclientaccountmovement.dto.AdmiTipoMovimientoResDTO;
import com.personclientaccountmovement.enums.EnumStatus;
import com.personclientaccountmovement.repository.postgres.entity.AdmiTipoMovimiento;

/**
* Conversión entre AdmiTipoMovimiento y DTOs.
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Service
public class AdmiTipoMovimientoConverter {

        public AdmiTipoMovimiento mappingAdmiTipoMovimientoReqDTOtoAdmiTipoMovimientoEntity(
                        AdmiTipoMovimientoReqDTO requestDTO) {
                AdmiTipoMovimiento admiTipoMovimiento = new AdmiTipoMovimiento();
                admiTipoMovimiento.setIdTipoMovimiento((requestDTO.getIdTipoMovimiento() != null
                                && requestDTO.getIdTipoMovimiento() != 0)
                                                ? requestDTO.getIdTipoMovimiento()
                                                : null);

                admiTipoMovimiento.setNombreMovimiento((requestDTO.getNombreMovimiento() == null || requestDTO.getNombreMovimiento().isEmpty()
                                || requestDTO.getNombreMovimiento().trim().isEmpty()) ? null : requestDTO.getNombreMovimiento());

                admiTipoMovimiento.setDescripcion((requestDTO.getDescripcion() == null || requestDTO.getDescripcion().isEmpty()
                                || requestDTO.getDescripcion().trim().isEmpty()) ? null : requestDTO.getDescripcion());

                admiTipoMovimiento.setEstado((requestDTO.getEstado() == null || requestDTO.getEstado().isEmpty()
                                || requestDTO.getEstado().trim().isEmpty()) ?  EnumStatus.ACTIVO.toString(): 
                                requestDTO.getEstado());

                admiTipoMovimiento.setPersonaCreacion(
                                (requestDTO.getPersonaCreacion() == null || requestDTO.getPersonaCreacion().isEmpty()
                                                || requestDTO.getPersonaCreacion().trim().isEmpty()) ? null
                                                                : requestDTO.getPersonaCreacion());
                                                                
                admiTipoMovimiento.setFechaCreacion(requestDTO.getFechaCreacion());

                admiTipoMovimiento.setPersonaModificacion(
                                (requestDTO.getPersonaModificacion() == null || requestDTO.getPersonaModificacion().isEmpty()
                                                || requestDTO.getPersonaModificacion().trim().isEmpty()) ? null
                                                                : requestDTO.getPersonaModificacion());

                admiTipoMovimiento.setFechaModificacion(requestDTO.getFechaModificacion());

                return admiTipoMovimiento;
        }

        public AdmiTipoMovimientoReqDTO mappingAdmiTipoMovimientoEntitytoAdmiTipoMovimientoReqDTO(
                        AdmiTipoMovimiento response) {
                AdmiTipoMovimientoReqDTO admiTipoMovimientoDTO = new AdmiTipoMovimientoReqDTO();
                admiTipoMovimientoDTO
                                .setIdTipoMovimiento((response.getIdTipoMovimiento() != null
                                                && response.getIdTipoMovimiento() != 0) ? response.getIdTipoMovimiento()
                                                                : null);

                admiTipoMovimientoDTO.setDescripcion(response.getDescripcion());
                admiTipoMovimientoDTO.setNombreMovimiento(response.getNombreMovimiento());

                admiTipoMovimientoDTO.setEstado(response.getEstado());
                admiTipoMovimientoDTO.setPersonaCreacion(response.getPersonaCreacion());
                admiTipoMovimientoDTO.setFechaCreacion(response.getFechaCreacion());
                admiTipoMovimientoDTO.setPersonaModificacion(response.getPersonaModificacion());
                admiTipoMovimientoDTO.setFechaModificacion(response.getFechaModificacion());
                return admiTipoMovimientoDTO;
        }

        public AdmiTipoMovimientoResDTO mappingAdmiTipoMovimientoEntitytoAdmiTipoMovimientoResDTO(
                        AdmiTipoMovimiento response) {
                AdmiTipoMovimientoResDTO admiTipoMovimientoDTO = new AdmiTipoMovimientoResDTO();
                admiTipoMovimientoDTO
                                .setIdTipoMovimiento((response.getIdTipoMovimiento() != null
                                                && response.getIdTipoMovimiento() != 0) ? response.getIdTipoMovimiento()
                                                                : null);

                admiTipoMovimientoDTO.setDescripcion(response.getDescripcion());
                admiTipoMovimientoDTO.setNombreMovimiento(response.getNombreMovimiento());

                admiTipoMovimientoDTO.setEstado(response.getEstado());
                admiTipoMovimientoDTO.setPersonaCreacion(response.getPersonaCreacion());
                admiTipoMovimientoDTO.setFechaCreacion(response.getFechaCreacion());
                admiTipoMovimientoDTO.setPersonaModificacion(response.getPersonaModificacion());
                admiTipoMovimientoDTO.setFechaModificacion(response.getFechaModificacion());
                return admiTipoMovimientoDTO;
        }
}
