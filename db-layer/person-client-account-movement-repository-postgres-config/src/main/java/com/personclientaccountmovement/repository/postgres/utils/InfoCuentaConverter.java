package com.personclientaccountmovement.repository.postgres.utils;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.personclientaccountmovement.dto.InfoCuentaPersonaUnifiedReqDTO;
import com.personclientaccountmovement.dto.InfoCuentaPersonaUnifiedResDTO;
import com.personclientaccountmovement.dto.InfoCuentaReqDTO;
import com.personclientaccountmovement.dto.InfoCuentaResDTO;
import com.personclientaccountmovement.dto.PersonaClienteUnifiedReqDTO;
import com.personclientaccountmovement.enums.EnumStatus;
import com.personclientaccountmovement.repository.postgres.entity.AdmiTipoCuenta;
import com.personclientaccountmovement.repository.postgres.entity.InfoCuenta;
import com.personclientaccountmovement.repository.postgres.entity.InfoPersona;

/**
* Conversión entre InfoCuenta y DTOs.
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Service
public class InfoCuentaConverter {

        public InfoCuenta mappingInfoCuentaReqDTOtoInfoCuentaEntity(
                        InfoCuentaPersonaUnifiedReqDTO requestDTO) {
                InfoCuenta infoCuenta = new InfoCuenta();
                infoCuenta.setIdCuenta((requestDTO.getIdCuenta() != null
                                && requestDTO.getIdCuenta() != 0) ? requestDTO.getIdCuenta(): null);

                infoCuenta.setPersonaId((requestDTO.getPersonaId() != null
                                && requestDTO.getPersonaId() != 0)
                                                ? requestDTO.getPersonaId()
                                                : null);

                infoCuenta.setTipoCuentaId((requestDTO.getTipoCuentaId() != null
                                && requestDTO.getTipoCuentaId() != 0)
                                                ? requestDTO.getTipoCuentaId()
                                                : null);

                infoCuenta.setNumeroCuenta((requestDTO.getNumeroCuenta() == null || requestDTO.getNumeroCuenta().isEmpty()
                                || requestDTO.getNumeroCuenta().trim().isEmpty()) ? null : requestDTO.getNumeroCuenta());

                
                infoCuenta.setSaldoActual(
                                (requestDTO.getSaldoInicial() != null 
                                        && requestDTO.getSaldoInicial().compareTo(BigDecimal.ZERO) != 0)
                                        ? requestDTO.getSaldoInicial()
                                        : BigDecimal.ZERO
                                );
                
                infoCuenta.setSaldoInicial(
                                (requestDTO.getSaldoInicial() != null 
                                        && requestDTO.getSaldoInicial().compareTo(BigDecimal.ZERO) != 0)
                                        ? requestDTO.getSaldoInicial()
                                        : BigDecimal.ZERO
                                );

                return infoCuenta;
        }


        public InfoCuenta mappingInfoCuentaReqDTOtoInfoCuentaEntityList(
                        InfoCuentaPersonaUnifiedReqDTO requestDTO) {
                InfoCuenta infoCuenta = new InfoCuenta();
                infoCuenta.setIdCuenta((requestDTO.getIdCuenta() != null
                                && requestDTO.getIdCuenta() != 0) ? requestDTO.getIdCuenta(): null);

                infoCuenta.setPersonaId((requestDTO.getPersonaId() != null
                                && requestDTO.getPersonaId() != 0)
                                                ? requestDTO.getPersonaId()
                                                : null);

                infoCuenta.setTipoCuentaId((requestDTO.getTipoCuentaId() != null
                                && requestDTO.getTipoCuentaId() != 0)
                                                ? requestDTO.getTipoCuentaId()
                                                : null);

                infoCuenta.setNumeroCuenta((requestDTO.getNumeroCuenta() == null || requestDTO.getNumeroCuenta().isEmpty()
                                || requestDTO.getNumeroCuenta().trim().isEmpty()) ? null : requestDTO.getNumeroCuenta());


                infoCuenta.setEstado((requestDTO.getEstado() == null || requestDTO.getEstado().isEmpty()
                                || requestDTO.getEstado().trim().isEmpty()) ?  EnumStatus.ACTIVO.toString(): 
                                requestDTO.getEstado());

                return infoCuenta;
        }

        public InfoCuentaReqDTO mappingInfoCuentaEntitytoInfoCuentaReqDTO(
                        InfoCuenta response) {
                InfoCuentaReqDTO infoCuentaDTO = new InfoCuentaReqDTO();
                infoCuentaDTO
                                .setIdCuenta((response.getIdCuenta() != null
                                                && response.getIdCuenta() != 0) ? response.getIdCuenta()
                                                                : null);

                infoCuentaDTO.setNumeroCuenta(response.getNumeroCuenta());

                infoCuentaDTO
                                .setPersonaId((response.getPersonaId() != null
                                                && response.getPersonaId() != 0) ? response.getPersonaId()
                                                                : null);

                infoCuentaDTO
                                .setTipoCuentaId((response.getTipoCuentaId() != null
                                                && response.getTipoCuentaId() != 0) ? response.getTipoCuentaId()
                                                                : null);
                infoCuentaDTO.setSaldoActual(
                        (response.getSaldoActual() != null 
                                && response.getSaldoActual().compareTo(BigDecimal.ZERO) != 0)
                                ? response.getSaldoActual()
                                : BigDecimal.ZERO
                        );

                infoCuentaDTO.setSaldoInicial(
                        (response.getSaldoInicial() != null 
                                && response.getSaldoInicial().compareTo(BigDecimal.ZERO) != 0)
                                ? response.getSaldoInicial()
                                : BigDecimal.ZERO
                        );

                infoCuentaDTO.setEstado(response.getEstado());
                infoCuentaDTO.setPersonaCreacion(response.getPersonaCreacion());
                infoCuentaDTO.setFechaCreacion(response.getFechaCreacion());
                infoCuentaDTO.setPersonaModificacion(response.getPersonaModificacion());
                infoCuentaDTO.setFechaModificacion(response.getFechaModificacion());
                return infoCuentaDTO;
        }

        public InfoCuentaResDTO mappingInfoCuentaEntitytoInfoCuentaResDTO(
                        InfoCuenta response) {
                InfoCuentaResDTO infoCuentaDTO = new InfoCuentaResDTO();
                infoCuentaDTO
                                .setIdCuenta((response.getIdCuenta() != null
                                                && response.getIdCuenta() != 0) ? response.getIdCuenta()
                                                                : null);

                infoCuentaDTO.setNumeroCuenta(response.getNumeroCuenta());

                infoCuentaDTO
                                .setPersonaId((response.getPersonaId() != null
                                                && response.getPersonaId() != 0) ? response.getPersonaId()
                                                                : null);

                infoCuentaDTO
                                .setTipoCuentaId((response.getTipoCuentaId() != null
                                                && response.getTipoCuentaId() != 0) ? response.getTipoCuentaId()
                                                                : null);
                if (response.getAdmiTipoCuenta() != null) {
                        infoCuentaDTO.setTipoCuentaNombre(response.getAdmiTipoCuenta().getNombreCuenta());
                }
                infoCuentaDTO.setSaldoActual(
                        (response.getSaldoActual() != null 
                                && response.getSaldoActual().compareTo(BigDecimal.ZERO) != 0)
                                ? response.getSaldoActual()
                                : BigDecimal.ZERO
                        );

                infoCuentaDTO.setSaldoInicial(
                        (response.getSaldoInicial() != null 
                                && response.getSaldoInicial().compareTo(BigDecimal.ZERO) != 0)
                                ? response.getSaldoInicial()
                                : BigDecimal.ZERO
                        );

                infoCuentaDTO.setEstado(response.getEstado());
                infoCuentaDTO.setPersonaCreacion(response.getPersonaCreacion());
                infoCuentaDTO.setFechaCreacion(response.getFechaCreacion());
                infoCuentaDTO.setPersonaModificacion(response.getPersonaModificacion());
                infoCuentaDTO.setFechaModificacion(response.getFechaModificacion());
                return infoCuentaDTO;
        }

        public InfoCuentaPersonaUnifiedResDTO mappingInfoCuentaEntitytoInfoCuentaPersonaUnifiedResDTO(
                        InfoCuenta response, InfoPersona infoPersona, AdmiTipoCuenta admiTipoCuenta) {
                InfoCuentaPersonaUnifiedResDTO infoCuentaPersonaUnifiedResDTO = new InfoCuentaPersonaUnifiedResDTO();
              
                infoCuentaPersonaUnifiedResDTO
                                .setIdCuenta((response.getIdCuenta() != null
                                                && response.getIdCuenta() != 0) ? response.getIdCuenta()
                                                                : null);

                infoCuentaPersonaUnifiedResDTO.setNumeroCuenta(response.getNumeroCuenta());

                infoCuentaPersonaUnifiedResDTO
                                .setPersonaId((response.getPersonaId() != null
                                                && response.getPersonaId() != 0) ? response.getPersonaId()
                                                                : null);

                

                infoCuentaPersonaUnifiedResDTO
                                .setTipoCuentaId((response.getTipoCuentaId() != null
                                                && response.getTipoCuentaId() != 0) ? response.getTipoCuentaId()
                                                                : null);

                infoCuentaPersonaUnifiedResDTO.setSaldoActual(
                        (response.getSaldoActual() != null 
                                && response.getSaldoActual().compareTo(BigDecimal.ZERO) != 0)
                                ? response.getSaldoActual()
                                : BigDecimal.ZERO
                        );

                infoCuentaPersonaUnifiedResDTO.setSaldoInicial(
                        (response.getSaldoInicial() != null 
                                && response.getSaldoInicial().compareTo(BigDecimal.ZERO) != 0)
                                ? response.getSaldoInicial()
                                : BigDecimal.ZERO
                        );

                infoCuentaPersonaUnifiedResDTO.setEstado(response.getEstado());

                infoCuentaPersonaUnifiedResDTO
                                .setNombreTipoCuenta(admiTipoCuenta.getNombreCuenta());
                
                infoCuentaPersonaUnifiedResDTO
                                .setIdentificacion(infoPersona.getIdentificacion());

                infoCuentaPersonaUnifiedResDTO
                                .setNombre(infoPersona.getNombre());

                infoCuentaPersonaUnifiedResDTO
                                .setApellido(infoPersona.getApellido());

                return infoCuentaPersonaUnifiedResDTO;
        }

        public InfoCuentaPersonaUnifiedResDTO mappingInfoCuentaEntitytoInfoCuentaPersonaUnifiedResDTOList(
                        InfoCuenta response) {
                InfoCuentaPersonaUnifiedResDTO infoCuentaPersonaUnifiedResDTO = new InfoCuentaPersonaUnifiedResDTO();
            
                infoCuentaPersonaUnifiedResDTO
                                .setIdCuenta((response.getIdCuenta() != null
                                                && response.getIdCuenta() != 0) ? response.getIdCuenta()
                                                                : null);

                infoCuentaPersonaUnifiedResDTO.setNumeroCuenta(response.getNumeroCuenta());

                infoCuentaPersonaUnifiedResDTO
                                .setPersonaId((response.getPersonaId() != null
                                                && response.getPersonaId() != 0) ? response.getPersonaId()
                                                                : null);

                infoCuentaPersonaUnifiedResDTO
                                .setNombre(response.getInfoPersona().getNombre());

                infoCuentaPersonaUnifiedResDTO
                                .setApellido(response.getInfoPersona().getApellido());

                infoCuentaPersonaUnifiedResDTO
                                .setIdentificacion(response.getInfoPersona().getIdentificacion());

                infoCuentaPersonaUnifiedResDTO
                                .setTipoCuentaId((response.getTipoCuentaId() != null
                                                && response.getTipoCuentaId() != 0) ? response.getTipoCuentaId()
                                                                : null);

                infoCuentaPersonaUnifiedResDTO
                                .setNombreTipoCuenta(response.getAdmiTipoCuenta().getNombreCuenta());

                infoCuentaPersonaUnifiedResDTO.setSaldoActual(
                        (response.getSaldoActual() != null 
                                && response.getSaldoActual().compareTo(BigDecimal.ZERO) != 0)
                                ? response.getSaldoActual()
                                : BigDecimal.ZERO
                        );

                infoCuentaPersonaUnifiedResDTO.setSaldoInicial(
                        (response.getSaldoInicial() != null 
                                && response.getSaldoInicial().compareTo(BigDecimal.ZERO) != 0)
                                ? response.getSaldoInicial()
                                : BigDecimal.ZERO
                        );

                infoCuentaPersonaUnifiedResDTO.setEstado(response.getEstado());

                return infoCuentaPersonaUnifiedResDTO;
        }

        public InfoCuenta mappingInfoCuentaReqDTOInfoCuentatoInfoCuentaEntity(InfoCuenta infoCuenta, InfoCuentaPersonaUnifiedReqDTO requestDTO) {
                
                infoCuenta.setTipoCuentaId((requestDTO.getTipoCuentaId() != null
                                && requestDTO.getTipoCuentaId() != 0)
                                                ? requestDTO.getTipoCuentaId()
                                                : null);
                infoCuenta.setPersonaId((requestDTO.getPersonaId() != null
                                && requestDTO.getPersonaId() != 0)
                                                ? requestDTO.getPersonaId()
                                                : null);
                infoCuenta.setNumeroCuenta((requestDTO.getNumeroCuenta() == null || requestDTO.getNumeroCuenta().isEmpty()
                                || requestDTO.getNumeroCuenta().trim().isEmpty()) ? null : requestDTO.getNumeroCuenta());
                infoCuenta.setSaldoInicial((requestDTO.getSaldoInicial() != null 
                                && requestDTO.getSaldoInicial().compareTo(BigDecimal.ZERO) != 0)
                                ? requestDTO.getSaldoInicial()
                                : BigDecimal.ZERO);
                infoCuenta.setSaldoActual((requestDTO.getSaldoActual() != null 
                                && requestDTO.getSaldoActual().compareTo(BigDecimal.ZERO) != 0)
                                ? requestDTO.getSaldoActual()
                                : BigDecimal.ZERO);
                return infoCuenta;
        }
        

}
