package com.personclientaccountmovement.repository.postgres.utils;

import org.springframework.stereotype.Service;

import com.personclientaccountmovement.dto.AdmiTipoCuentaReqDTO;
import com.personclientaccountmovement.dto.AdmiTipoCuentaResDTO;
import com.personclientaccountmovement.enums.EnumStatus;
import com.personclientaccountmovement.repository.postgres.entity.AdmiTipoCuenta;

/**
* Conversión entre AdmiTipoCuenta y DTOs.
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Service
public class AdmiTipoCuentaConverter {

        public AdmiTipoCuenta mappingAdmiTipoCuentaReqDTOtoAdmiTipoCuentaEntity(
                        AdmiTipoCuentaReqDTO requestDTO) {
                AdmiTipoCuenta admiTipoCuenta = new AdmiTipoCuenta();
                admiTipoCuenta.setIdTipoCuenta((requestDTO.getIdTipoCuenta() != null
                                && requestDTO.getIdTipoCuenta() != 0)
                                                ? requestDTO.getIdTipoCuenta()
                                                : null);

                admiTipoCuenta.setNombreCuenta((requestDTO.getNombreCuenta() == null || requestDTO.getNombreCuenta().isEmpty()
                                || requestDTO.getNombreCuenta().trim().isEmpty()) ? null : requestDTO.getNombreCuenta());

                admiTipoCuenta.setDescripcion((requestDTO.getDescripcion() == null || requestDTO.getDescripcion().isEmpty()
                                || requestDTO.getDescripcion().trim().isEmpty()) ? null : requestDTO.getDescripcion());

                admiTipoCuenta.setEstado((requestDTO.getEstado() == null || requestDTO.getEstado().isEmpty()
                                || requestDTO.getEstado().trim().isEmpty()) ?  EnumStatus.ACTIVO.toString(): 
                                requestDTO.getEstado());

                admiTipoCuenta.setPersonaCreacion(
                                (requestDTO.getPersonaCreacion() == null || requestDTO.getPersonaCreacion().isEmpty()
                                                || requestDTO.getPersonaCreacion().trim().isEmpty()) ? null
                                                                : requestDTO.getPersonaCreacion());
                                                                
                admiTipoCuenta.setFechaCreacion(requestDTO.getFechaCreacion());

                admiTipoCuenta.setPersonaModificacion(
                                (requestDTO.getPersonaModificacion() == null || requestDTO.getPersonaModificacion().isEmpty()
                                                || requestDTO.getPersonaModificacion().trim().isEmpty()) ? null
                                                                : requestDTO.getPersonaModificacion());

                admiTipoCuenta.setFechaModificacion(requestDTO.getFechaModificacion());

                return admiTipoCuenta;
        }

        public AdmiTipoCuentaReqDTO mappingAdmiTipoCuentaEntitytoAdmiTipoCuentaReqDTO(
                        AdmiTipoCuenta response) {
                AdmiTipoCuentaReqDTO admiTipoCuentaDTO = new AdmiTipoCuentaReqDTO();
                admiTipoCuentaDTO
                                .setIdTipoCuenta((response.getIdTipoCuenta() != null
                                                && response.getIdTipoCuenta() != 0) ? response.getIdTipoCuenta()
                                                                : null);

                admiTipoCuentaDTO.setDescripcion(response.getDescripcion());
                admiTipoCuentaDTO.setNombreCuenta(response.getNombreCuenta());

                admiTipoCuentaDTO.setEstado(response.getEstado());
                admiTipoCuentaDTO.setPersonaCreacion(response.getPersonaCreacion());
                admiTipoCuentaDTO.setFechaCreacion(response.getFechaCreacion());
                admiTipoCuentaDTO.setPersonaModificacion(response.getPersonaModificacion());
                admiTipoCuentaDTO.setFechaModificacion(response.getFechaModificacion());
                return admiTipoCuentaDTO;
        }

        public AdmiTipoCuentaResDTO mappingAdmiTipoCuentaEntitytoAdmiTipoCuentaResDTO(
                        AdmiTipoCuenta response) {
                AdmiTipoCuentaResDTO admiTipoCuentaDTO = new AdmiTipoCuentaResDTO();
                admiTipoCuentaDTO
                                .setIdTipoCuenta((response.getIdTipoCuenta() != null
                                                && response.getIdTipoCuenta() != 0) ? response.getIdTipoCuenta()
                                                                : null);

                admiTipoCuentaDTO.setDescripcion(response.getDescripcion());
                admiTipoCuentaDTO.setNombreCuenta(response.getNombreCuenta());

                admiTipoCuentaDTO.setEstado(response.getEstado());
                admiTipoCuentaDTO.setPersonaCreacion(response.getPersonaCreacion());
                admiTipoCuentaDTO.setFechaCreacion(response.getFechaCreacion());
                admiTipoCuentaDTO.setPersonaModificacion(response.getPersonaModificacion());
                admiTipoCuentaDTO.setFechaModificacion(response.getFechaModificacion());
                return admiTipoCuentaDTO;
        }
}
