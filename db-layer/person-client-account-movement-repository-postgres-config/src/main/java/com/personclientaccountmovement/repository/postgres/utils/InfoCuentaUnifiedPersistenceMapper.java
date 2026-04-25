package com.personclientaccountmovement.repository.postgres.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.personclientaccountmovement.repository.postgres.dao.AdmiTipoCuentaDAO;
import com.personclientaccountmovement.repository.postgres.entity.AdmiTipoCuenta;
import com.personclientaccountmovement.cons.PersonClientAccountMovementConfigConstants;
import com.personclientaccountmovement.dto.InfoCuentaReqDTO;
import com.personclientaccountmovement.dto.InfoCuentaPersonaUnifiedReqDTO;
import com.personclientaccountmovement.enums.EnumStatus;
import com.personclientaccountmovement.exception.GenericException;

/**
* Mapeo unificado de cuenta para persistencia.
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Component
public class InfoCuentaUnifiedPersistenceMapper {

	@Autowired
	private AdmiTipoCuentaDAO admiTipoCuentaDAO;

	public InfoCuentaReqDTO toPersistRequest(InfoCuentaPersonaUnifiedReqDTO u) throws GenericException {
		Long tipoId = requireTipoCuentaId(u);
		InfoCuentaReqDTO req = InfoCuentaReqDTO.builder()
				.idCuenta(u.getIdCuenta())
				.numeroCuenta(u.getNumeroCuenta())
				.tipoCuentaNombre(u.getNombreTipoCuenta())
				.tipoCuentaId(tipoId)
				.personaId(u.getPersonaId())
				.saldoInicial(u.getSaldoInicial())
				.saldoActual(u.getSaldoActual())
				.estado(u.getEstado())
				.build();
		if (u.getPersonaRequest() != null && !u.getPersonaRequest().isBlank()) {
			req.setPersonaCreacion(u.getPersonaRequest());
			req.setPersonaModificacion(u.getPersonaRequest());
		}
		return req;
	}

	public InfoCuentaReqDTO toFilterRequest(InfoCuentaPersonaUnifiedReqDTO u) throws GenericException {
		Long tipoId = null;
		if (u.getTipoCuentaId() != null && u.getTipoCuentaId() != 0) {
			tipoId = u.getTipoCuentaId();
		} else if (u.getNombreTipoCuenta() != null && !u.getNombreTipoCuenta().isBlank()) {
			tipoId = lookupNombreTipo(u.getNombreTipoCuenta());
		}
		return InfoCuentaReqDTO.builder()
				.idCuenta(u.getIdCuenta())
				.numeroCuenta(u.getNumeroCuenta())
				.tipoCuentaId(tipoId)
				.personaId(u.getPersonaId())
				.saldoInicial(u.getSaldoInicial())
				.saldoActual(u.getSaldoActual())
				.estado(u.getEstado())
				.build();
	}

	private Long requireTipoCuentaId(InfoCuentaPersonaUnifiedReqDTO u) throws GenericException {
		if (u.getTipoCuentaId() != null && u.getTipoCuentaId() != 0) {
			return u.getTipoCuentaId();
		}
		if (u.getNombreTipoCuenta() == null || u.getNombreTipoCuenta().isBlank()) {
			throw new GenericException("Debe indicar nombreTipoCuenta",
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
		return lookupNombreTipo(u.getNombreTipoCuenta());
	}

	private Long lookupNombreTipo(String nombreTipoCuenta) throws GenericException {
		return admiTipoCuentaDAO
				.findByNombreCuentaIgnoreCaseAndEstado(nombreTipoCuenta.trim(), EnumStatus.ACTIVO.toString())
				.map(AdmiTipoCuenta::getIdTipoCuenta)
				.orElseThrow(() -> new GenericException(
						"Tipo de cuenta no encontrado: " + nombreTipoCuenta,
						PersonClientAccountMovementConfigConstants.MISSING_VALUES));
	}
}
