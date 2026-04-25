package com.personclientaccountmovement.repository.postgres.utils;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.time.LocalDate;
import java.util.List;

import com.personclientaccountmovement.exception.GenericException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personclientaccountmovement.dto.InfoMovimientoReqDTO;
import com.personclientaccountmovement.dto.InfoMovimientoResDTO;
import com.personclientaccountmovement.repository.postgres.entity.InfoMovimiento;
import com.personclientaccountmovement.cons.PersonClientAccountMovementConfigConstants;
import com.personclientaccountmovement.dto.InfoMovimientoCuentaResDTO;
import com.personclientaccountmovement.dto.InfoMovimientoPageResDTO;
import com.personclientaccountmovement.repository.postgres.entity.InfoCuenta;
import com.personclientaccountmovement.repository.postgres.entity.AdmiTipoMovimiento;
import com.personclientaccountmovement.dto.InfoMovimientoReporteResDTO;
import com.personclientaccountmovement.repository.postgres.dao.InfoMovimientoDAO;
import com.personclientaccountmovement.repository.postgres.entity.AdmiTipoCuenta;
import com.personclientaccountmovement.enums.EnumStatus;

/**
* Conversión entre InfoMovimiento y DTOs.
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Service
public class InfoMovimientoConverter {

        @Autowired
	private InfoMovimientoDAO infoMovimientoDAO;

        public InfoMovimiento mappingInfoMovimientoReqDTOtoInfoMovimientoEntity(
                        InfoMovimientoReqDTO requestDTO) {
                InfoMovimiento infoMovimiento = new InfoMovimiento();
                infoMovimiento.setIdMovimiento((requestDTO.getIdMovimiento() != null
                                && requestDTO.getIdMovimiento() != 0)
                                                ? requestDTO.getIdMovimiento()
                                                : null);

                infoMovimiento.setCuentaId((requestDTO.getCuentaId() != null
                                && requestDTO.getCuentaId() != 0)
                                                ? requestDTO.getCuentaId()
                                                : null);

                infoMovimiento.setTipoMovimientoId((requestDTO.getTipoMovimientoId() != null
                                && requestDTO.getTipoMovimientoId() != 0)
                                                ? requestDTO.getTipoMovimientoId()
                                                : null);

                
                infoMovimiento.setValor(
                                (requestDTO.getValor() != null 
                                        && requestDTO.getValor().compareTo(BigDecimal.ZERO) != 0)
                                        ? requestDTO.getValor()
                                        : BigDecimal.ZERO
                                );
                
                infoMovimiento.setSaldo(
                                (requestDTO.getSaldo() != null 
                                        && requestDTO.getSaldo().compareTo(BigDecimal.ZERO) != 0)
                                        ? requestDTO.getSaldo()
                                        : BigDecimal.ZERO
                                );

                infoMovimiento.setPersonaCreacion(
                                (requestDTO.getPersonaCreacion() == null || requestDTO.getPersonaCreacion().isEmpty()
                                                || requestDTO.getPersonaCreacion().trim().isEmpty()) ? null
                                                                : requestDTO.getPersonaCreacion());
                                                                
                infoMovimiento.setFechaCreacion(requestDTO.getFechaCreacion());

                return infoMovimiento;
        }


        public InfoMovimiento mappingInfoMovimientoReqDTOtoInfoMovimientoEntityList(
                        InfoMovimientoReqDTO requestDTO) {
                InfoMovimiento infoMovimiento = new InfoMovimiento();
                InfoCuenta infoCuenta = new InfoCuenta();

                AdmiTipoCuenta admiTipoCuenta = new AdmiTipoCuenta();
                admiTipoCuenta.setNombreCuenta((requestDTO.getTipoCuentaNombre() == null || requestDTO.getTipoCuentaNombre().isEmpty()
                                || requestDTO.getTipoCuentaNombre().trim().isEmpty()) ? null : requestDTO.getTipoCuentaNombre());
                       
                infoCuenta.setNumeroCuenta((requestDTO.getNumeroCuenta() == null || requestDTO.getNumeroCuenta().isEmpty()
                                || requestDTO.getNumeroCuenta().trim().isEmpty()) ? null : requestDTO.getNumeroCuenta());
                infoCuenta.setEstado(EnumStatus.ACTIVO.toString());
                infoMovimiento.setInfoCuenta(infoCuenta);
                infoCuenta.setAdmiTipoCuenta(admiTipoCuenta);

                AdmiTipoMovimiento admiTipoMovimiento = new AdmiTipoMovimiento();
                admiTipoMovimiento.setNombreMovimiento((requestDTO.getTipoMovimientoNombre() == null || requestDTO.getTipoMovimientoNombre().isEmpty()
                                || requestDTO.getTipoMovimientoNombre().trim().isEmpty()) ? null : requestDTO.getTipoMovimientoNombre());
                infoMovimiento.setAdmiTipoMovimiento(admiTipoMovimiento);

                infoMovimiento.setIdMovimiento((requestDTO.getIdMovimiento() != null
                                && requestDTO.getIdMovimiento() != 0)
                                                ? requestDTO.getIdMovimiento()
                                                : null);

                infoMovimiento.setCuentaId((requestDTO.getCuentaId() != null
                                && requestDTO.getCuentaId() != 0)
                                                ? requestDTO.getCuentaId()
                                                : null);

                infoMovimiento.setTipoMovimientoId((requestDTO.getTipoMovimientoId() != null
                                && requestDTO.getTipoMovimientoId() != 0)
                                                ? requestDTO.getTipoMovimientoId()
                                                : null);

                return infoMovimiento;
        }

        public InfoMovimientoReqDTO mappingInfoMovimientoEntitytoInfoMovimientoReqDTO(
                        InfoMovimiento response) {
                InfoMovimientoReqDTO infoMovimientoDTO = new InfoMovimientoReqDTO();
                infoMovimientoDTO
                                .setIdMovimiento((response.getIdMovimiento() != null
                                                && response.getIdMovimiento() != 0) ? response.getIdMovimiento()
                                                                : null);

                infoMovimientoDTO
                                .setCuentaId((response.getCuentaId() != null
                                                && response.getCuentaId() != 0) ? response.getCuentaId()
                                                                : null);

                infoMovimientoDTO
                                .setTipoMovimientoId((response.getTipoMovimientoId() != null
                                                && response.getTipoMovimientoId() != 0) ? response.getTipoMovimientoId()
                                                                : null);
                infoMovimientoDTO.setValor(
                        (response.getValor() != null 
                                && response.getValor().compareTo(BigDecimal.ZERO) != 0)
                                ? response.getValor()
                                : BigDecimal.ZERO
                        );

                infoMovimientoDTO.setSaldo(
                        (response.getSaldo() != null 
                                && response.getSaldo().compareTo(BigDecimal.ZERO) != 0)
                                ? response.getSaldo()
                                : BigDecimal.ZERO
                        );

                infoMovimientoDTO.setPersonaCreacion(response.getPersonaCreacion());
                infoMovimientoDTO.setFechaCreacion(response.getFechaCreacion());
                return infoMovimientoDTO;
        }

        public InfoMovimientoResDTO mappingInfoMovimientoEntitytoInfoMovimientoResDTO(
                        InfoMovimiento response) {
                InfoMovimientoResDTO infoMovimientoDTO = new InfoMovimientoResDTO();
                infoMovimientoDTO
                                .setIdMovimiento((response.getIdMovimiento() != null
                                                && response.getIdMovimiento() != 0) ? response.getIdMovimiento()
                                                                : null);

                infoMovimientoDTO
                                .setCuentaId((response.getCuentaId() != null
                                                && response.getCuentaId() != 0) ? response.getCuentaId()
                                                                : null);

                infoMovimientoDTO
                                .setTipoMovimientoId((response.getTipoMovimientoId() != null
                                                && response.getTipoMovimientoId() != 0) ? response.getTipoMovimientoId()
                                                                : null);
                infoMovimientoDTO.setValor(
                        (response.getValor() != null 
                                && response.getValor().compareTo(BigDecimal.ZERO) != 0)
                                ? response.getValor()
                                : BigDecimal.ZERO
                        );

                infoMovimientoDTO.setSaldo(
                        (response.getSaldo() != null 
                                && response.getSaldo().compareTo(BigDecimal.ZERO) != 0)
                                ? response.getSaldo()
                                : BigDecimal.ZERO
                        );

                infoMovimientoDTO.setPersonaCreacion(response.getPersonaCreacion());
                infoMovimientoDTO.setFechaCreacion(response.getFechaCreacion());
                return infoMovimientoDTO;
        }

        public InfoMovimientoCuentaResDTO mappingInfoMovimientoEntitytoInfoMovimientoCuentaResDTO(
                        InfoMovimiento response) {
                InfoMovimientoCuentaResDTO infoMovimientoDTO = new InfoMovimientoCuentaResDTO();
                infoMovimientoDTO
                                .setIdMovimiento((response.getIdMovimiento() != null
                                                && response.getIdMovimiento() != 0) ? response.getIdMovimiento()
                                                                : null);

                infoMovimientoDTO
                                .setCuentaId((response.getCuentaId() != null
                                                && response.getCuentaId() != 0) ? response.getCuentaId()
                                                                : null);

                infoMovimientoDTO.setNumeroCuenta(response.getInfoCuenta().getNumeroCuenta());
                infoMovimientoDTO.setTipoCuentaId(response.getInfoCuenta().getAdmiTipoCuenta().getIdTipoCuenta());
                infoMovimientoDTO.setTipoCuentaNombre(response.getInfoCuenta().getAdmiTipoCuenta().getNombreCuenta());
                infoMovimientoDTO
                                .setTipoMovimientoId((response.getTipoMovimientoId() != null
                                                && response.getTipoMovimientoId() != 0) ? response.getTipoMovimientoId()
                                                                : null);
                infoMovimientoDTO.setTipoMovimientoNombre(response.getAdmiTipoMovimiento().getNombreMovimiento());
                infoMovimientoDTO.setValor(
                        (response.getValor() != null 
                                && response.getValor().compareTo(BigDecimal.ZERO) != 0)
                                ? response.getValor()
                                : BigDecimal.ZERO
                        );

                infoMovimientoDTO.setSaldoActual(
                        (response.getSaldo() != null 
                                && response.getSaldo().compareTo(BigDecimal.ZERO) != 0)
                                ? response.getSaldo()
                                : BigDecimal.ZERO
                        );
                infoMovimientoDTO.setSaldoInicial(response.getSaldo().subtract(response.getValor()));
                infoMovimientoDTO.setMovimiento(response.getAdmiTipoMovimiento().getNombreMovimiento() + " de " + response.getValor());
                return infoMovimientoDTO;
        }

        public InfoMovimientoPageResDTO mappingInfoMovimientoEntitytoInfoMovimientoPageResDTO(
                        InfoMovimiento response) {
                InfoMovimientoPageResDTO infoMovimientoDTO = new InfoMovimientoPageResDTO();
                infoMovimientoDTO
                                .setIdMovimiento((response.getIdMovimiento() != null
                                                && response.getIdMovimiento() != 0) ? response.getIdMovimiento()
                                                                : null);

                infoMovimientoDTO
                                .setCuentaId((response.getCuentaId() != null
                                                && response.getCuentaId() != 0) ? response.getCuentaId()
                                                                : null);

                infoMovimientoDTO.setNombre(response.getInfoCuenta().getInfoPersona().getNombre());
                infoMovimientoDTO.setApellido(response.getInfoCuenta().getInfoPersona().getApellido());
                infoMovimientoDTO.setIdentificacion(response.getInfoCuenta().getInfoPersona().getIdentificacion());
                infoMovimientoDTO.setNumeroCuenta(response.getInfoCuenta().getNumeroCuenta());
                infoMovimientoDTO.setTipoCuentaId(response.getInfoCuenta().getAdmiTipoCuenta().getIdTipoCuenta());
                infoMovimientoDTO.setTipoCuentaNombre(response.getInfoCuenta().getAdmiTipoCuenta().getNombreCuenta());
                infoMovimientoDTO
                                .setTipoMovimientoId((response.getTipoMovimientoId() != null
                                                && response.getTipoMovimientoId() != 0) ? response.getTipoMovimientoId()
                                                                : null);
                infoMovimientoDTO.setTipoMovimientoNombre(response.getAdmiTipoMovimiento().getNombreMovimiento());
                infoMovimientoDTO.setValor(
                        (response.getValor() != null 
                                && response.getValor().compareTo(BigDecimal.ZERO) != 0)
                                ? response.getValor()
                                : BigDecimal.ZERO
                        );

                infoMovimientoDTO.setSaldoActual(
                        (response.getSaldo() != null 
                                && response.getSaldo().compareTo(BigDecimal.ZERO) != 0)
                                ? response.getSaldo()
                                : BigDecimal.ZERO
                        );
                infoMovimientoDTO.setSaldoInicial(response.getSaldo().subtract(response.getValor()));
                infoMovimientoDTO.setMovimiento(response.getAdmiTipoMovimiento().getNombreMovimiento() + " de " + response.getValor());
                infoMovimientoDTO.setEstado(response.getEstado());
                List<InfoMovimiento> listInfoMovimiento = infoMovimientoDAO.findLastMovimientoByCuentaId(response.getInfoCuenta().getIdCuenta());

		if(listInfoMovimiento != null && listInfoMovimiento.size() > 0) {
			if(listInfoMovimiento.get(0).getIdMovimiento() == response.getIdMovimiento()) {
                                infoMovimientoDTO.setEditable(true);
			} else {
                                infoMovimientoDTO.setEditable(false);
                        }
		} else {
                        infoMovimientoDTO.setEditable(true);
                }
                
                return infoMovimientoDTO;
        }

        public InfoMovimientoCuentaResDTO mappingInfoMovimientoEntityInfoCuentatoInfoMovimientoCuentaResDTO(
                        InfoMovimiento response, InfoCuenta infoCuenta, AdmiTipoMovimiento admiTipoMovimiento) {
                InfoMovimientoCuentaResDTO infoMovimientoDTO = new InfoMovimientoCuentaResDTO();
                infoMovimientoDTO
                                .setIdMovimiento((response.getIdMovimiento() != null
                                                && response.getIdMovimiento() != 0) ? response.getIdMovimiento()
                                                                : null);

                infoMovimientoDTO
                                .setCuentaId((response.getCuentaId() != null
                                                && response.getCuentaId() != 0) ? response.getCuentaId()
                                                                : null);

                infoMovimientoDTO.setNumeroCuenta(infoCuenta.getNumeroCuenta());
                infoMovimientoDTO.setTipoCuentaId(infoCuenta.getAdmiTipoCuenta().getIdTipoCuenta());
                infoMovimientoDTO.setTipoCuentaNombre(infoCuenta.getAdmiTipoCuenta().getNombreCuenta());
                infoMovimientoDTO
                                .setTipoMovimientoId((response.getTipoMovimientoId() != null
                                                && response.getTipoMovimientoId() != 0) ? response.getTipoMovimientoId()
                                                                : null);
                infoMovimientoDTO.setTipoMovimientoNombre(admiTipoMovimiento.getNombreMovimiento());
                infoMovimientoDTO.setValor(
                        (response.getValor() != null 
                                && response.getValor().compareTo(BigDecimal.ZERO) != 0)
                                ? response.getValor()
                                : BigDecimal.ZERO
                        );

                infoMovimientoDTO.setSaldoActual(
                        (response.getSaldo() != null 
                                && response.getSaldo().compareTo(BigDecimal.ZERO) != 0)
                                ? response.getSaldo()
                                : BigDecimal.ZERO
                        );
                infoMovimientoDTO.setSaldoInicial(response.getSaldo().subtract(response.getValor()));
                infoMovimientoDTO.setMovimiento(admiTipoMovimiento.getNombreMovimiento() + " de " + response.getValor());
                return infoMovimientoDTO;
        }

        public InfoMovimientoReporteResDTO mappingInfoMovimientoEntitytoInfoMovimientoReporteResDTO(
                        InfoMovimiento response) {
                InfoMovimientoReporteResDTO infoMovimientoDTO = new InfoMovimientoReporteResDTO();
                String fecha = response.getFechaCreacion()
                                .toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate()
                                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                infoMovimientoDTO.setFecha(fecha);
                infoMovimientoDTO.setPersonaId(response.getInfoCuenta().getPersonaId());
                infoMovimientoDTO.setNumeroCuenta(response.getInfoCuenta().getNumeroCuenta());
                infoMovimientoDTO.setTipoCuentaNombre(response.getInfoCuenta().getAdmiTipoCuenta().getNombreCuenta());
                infoMovimientoDTO.setIdentificacion(response.getInfoCuenta().getInfoPersona().getIdentificacion());
                infoMovimientoDTO.setNombre(response.getInfoCuenta().getInfoPersona().getNombre());
                infoMovimientoDTO.setApellido(response.getInfoCuenta().getInfoPersona().getApellido());
                infoMovimientoDTO.setEstado(response.getEstado());
                infoMovimientoDTO.setMovimiento(response.getValor());
                infoMovimientoDTO.setSaldoInicial(response.getSaldo().subtract(response.getValor()));
                infoMovimientoDTO.setSaldoDisponible(response.getSaldo());
                return infoMovimientoDTO;
        }

        public Date fechaInicio(String fechaIso) throws GenericException {
                ZoneId zone = ZoneId.of(MsClientesCuentasConstants.TIMEZONE_DATE);
                LocalDate desde;
                try {
                        desde = LocalDate.parse(fechaIso);
                } catch (DateTimeParseException e) {
                        throw new GenericException("Formato de fecha inválido. Use yyyy-MM-dd", 10);
                }
                return Date.from(desde.atStartOfDay(zone).toInstant());
        }

        public Date fechaFin(String fechaIso) throws GenericException {
                ZoneId zone = ZoneId.of(MsClientesCuentasConstants.TIMEZONE_DATE);
                LocalDate hasta;
                try {
                        hasta = LocalDate.parse(fechaIso);
                } catch (DateTimeParseException e) {
                        throw new GenericException("Formato de fecha inválido. Use yyyy-MM-dd", 10);
                }
                return Date.from(hasta.atTime(23, 59, 59, 999_000_000).atZone(zone).toInstant());
        }
}
