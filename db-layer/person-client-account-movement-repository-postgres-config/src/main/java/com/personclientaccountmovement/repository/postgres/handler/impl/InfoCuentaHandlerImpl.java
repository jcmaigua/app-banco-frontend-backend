package com.personclientaccountmovement.repository.postgres.handler.impl;

import java.util.ArrayList;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.personclientaccountmovement.dto.BusquedaGlobalDTO;
import com.personclientaccountmovement.dto.InfoCuentaPersonaUnifiedReqDTO;
import com.personclientaccountmovement.dto.InfoCuentaPersonaUnifiedResDTO;
import com.personclientaccountmovement.dto.InfoCuentaReqDTO;
import com.personclientaccountmovement.dto.InfoCuentaResDTO;
import com.personclientaccountmovement.dto.ObjectIdDTO;
import com.personclientaccountmovement.dto.PageDTO;
import com.personclientaccountmovement.dto.PersonaClienteUnifiedReqDTO;
import com.personclientaccountmovement.enums.EnumStatus;
import com.personclientaccountmovement.exception.GenericException;
import com.personclientaccountmovement.support.ExceptionLogSupport;
import com.personclientaccountmovement.repository.postgres.dao.InfoCuentaDAO;
import com.personclientaccountmovement.repository.postgres.entity.AdmiTipoCuenta;
import com.personclientaccountmovement.repository.postgres.entity.InfoCuenta;
import com.personclientaccountmovement.repository.postgres.handler.IInfoCuentaHandler;
import com.personclientaccountmovement.repository.postgres.utils.AdmiTipoCuentaValidators;
import com.personclientaccountmovement.repository.postgres.utils.InfoCuentaConverter;
import com.personclientaccountmovement.repository.postgres.utils.InfoCuentaValidators;
import com.personclientaccountmovement.repository.postgres.utils.InfoPersonaValidators;
import com.personclientaccountmovement.repository.postgres.utils.ResourceValidators;
import com.personclientaccountmovement.repository.postgres.dao.InfoMovimientoDAO;
import com.personclientaccountmovement.repository.postgres.entity.InfoMovimiento;
import com.personclientaccountmovement.repository.postgres.entity.InfoPersona;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;

/**
* Impl handler InfoCuenta
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Service
@Transactional(rollbackFor = { GenericException.class })
public class InfoCuentaHandlerImpl implements IInfoCuentaHandler {

	Logger log = LogManager.getLogger(this.getClass());

	@Autowired
	private ResourceValidators resourceValidators;

	@Autowired
	private InfoCuentaValidators infoCuentaValidators;

	@Autowired
	private InfoPersonaValidators infoPersonaValidators;

	@Autowired
	private AdmiTipoCuentaValidators admiTipoCuentaValidators;

	@Autowired
	private InfoCuentaDAO infoCuentaDAO;

	@Autowired
	private InfoMovimientoDAO infoMovimientoDAO;

	@Autowired
	private InfoCuentaConverter infoCuentaConverter;

	/**
	 * Método que guarda un InfoCuenta
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public InfoCuentaPersonaUnifiedResDTO saveInfoCuenta(InfoCuentaPersonaUnifiedReqDTO requestDTO) throws GenericException {
		log.info("Metodo repository saveInfoCuenta iniciado");
		InfoCuentaPersonaUnifiedResDTO response = new InfoCuentaPersonaUnifiedResDTO();
		InfoCuenta responseEntity = new InfoCuenta();
		try {
			InfoPersona infoPersona = infoPersonaValidators.validarEntidadInfoPersona(ObjectIdDTO.builder()
				.objectId(requestDTO.getPersonaId()).build());
			AdmiTipoCuenta admiTipoCuenta = admiTipoCuentaValidators.validarEntidadAdmiTipoCuenta(ObjectIdDTO.builder()
				.objectId(requestDTO.getTipoCuentaId()).build());
			responseEntity = infoCuentaValidators.validarGuardarInfoCuenta(requestDTO);
			if (responseEntity == null) {
				InfoCuenta request = infoCuentaConverter
					.mappingInfoCuentaReqDTOtoInfoCuentaEntity(requestDTO);
				request.setEstado(EnumStatus.ACTIVO.toString());
				request.setPersonaCreacion(requestDTO.getPersonaRequest());
				request.setFechaCreacion(new Date());
				responseEntity = infoCuentaDAO.save(request);
			}
			response = infoCuentaConverter
					.mappingInfoCuentaEntitytoInfoCuentaPersonaUnifiedResDTO(responseEntity, infoPersona, admiTipoCuenta);
		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("db-account-movement-repository", e);
			throw e;
		}
		return response;
	}

	/**
	 * Actualiza el registro indicado en la solicitud.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public void updateInfoCuentaByMovimiento(InfoCuenta request) {
		log.info("Metodo repository updateInfoCuentaByMovimiento iniciado");
		infoCuentaDAO.save(request);
	}

	/**
	 * Método que actualiza un InfoCuenta
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public InfoCuentaPersonaUnifiedResDTO updateInfoCuenta(InfoCuentaPersonaUnifiedReqDTO requestDTO) throws GenericException {
		log.info("Metodo repository updateInfoCuenta iniciado");
		InfoCuentaPersonaUnifiedResDTO response = new InfoCuentaPersonaUnifiedResDTO();
		InfoCuenta responseEntity = new InfoCuenta();
		try {
			
			InfoCuenta infoCuenta = infoCuentaValidators.validarActualizarInfoCuenta(requestDTO);
			InfoPersona infoPersona = infoPersonaValidators.validarEntidadInfoPersona(ObjectIdDTO.builder()
				.objectId(requestDTO.getPersonaId()).build());
			AdmiTipoCuenta admiTipoCuenta = admiTipoCuentaValidators.validarEntidadAdmiTipoCuenta(ObjectIdDTO.builder()
				.objectId(requestDTO.getTipoCuentaId()).build());
			infoCuenta.setFechaModificacion(new Date());
			infoCuenta.setPersonaModificacion(requestDTO.getPersonaRequest());
			BigDecimal saldoActual = infoCuenta.getSaldoActual();
			List<InfoMovimiento> listInfoMovimiento = infoMovimientoDAO.findByCuentaIdAndEstado(requestDTO.getIdCuenta(), EnumStatus.ACTIVO.toString());
			if(listInfoMovimiento != null && listInfoMovimiento.size() > 0) {
				if(infoCuenta.getSaldoInicial().compareTo(requestDTO.getSaldoInicial()) != 0) {
				    throw new GenericException("La cuenta tiene movimientos asociados. No se puede actualizar el saldo inicial", 10);
				} else {
					requestDTO.setSaldoActual(saldoActual);
				}
			} else {
				requestDTO.setSaldoActual(requestDTO.getSaldoInicial());
			}
			InfoCuenta request = infoCuentaConverter
					.mappingInfoCuentaReqDTOInfoCuentatoInfoCuentaEntity(infoCuenta, requestDTO);
			responseEntity = infoCuentaDAO.save(request);
			response = infoCuentaConverter
					.mappingInfoCuentaEntitytoInfoCuentaPersonaUnifiedResDTO(responseEntity, infoPersona, admiTipoCuenta);

		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("db-account-movement-repository", e);
			throw e;
		}
		return response;
	}

	/**
	 * Método que elimina un InfoCuenta
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public Boolean deleteInfoCuenta(ObjectIdDTO requestDTO) throws GenericException {
		log.info("Metodo repository deleteInfoCuenta iniciado");
		Boolean response = false;
		try {
			InfoCuenta request = infoCuentaValidators.validarCuentaTieneSaldo(requestDTO);
			request.setEstado(EnumStatus.ELIMINADO.toString());
			request.setFechaModificacion(new Date());
			infoCuentaDAO.saveAndFlush(request);
			response = true;
		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("db-account-movement-repository", e);
			throw e;
		}
		return response;
	}

	/**
	 * Método que retorna la lista de InfoCuentas con filtros
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public List<InfoCuentaPersonaUnifiedResDTO> listByInfoCuenta(InfoCuentaPersonaUnifiedReqDTO requestDto)
			throws GenericException {
				log.info("Metodo repository listByInfoCuenta iniciado");
		List<InfoCuentaPersonaUnifiedResDTO> response = new ArrayList<>();
		List<InfoCuenta> responseEntity = new ArrayList<>();
		try {
			infoCuentaValidators.validarInfoCuenta(requestDto);

			ExampleMatcher ignoringExampleMatcher = ExampleMatcher.matching()
					.withStringMatcher(StringMatcher.CONTAINING).withIgnoreCase();

			Example<InfoCuenta> listFiltros = Example.of(infoCuentaConverter
					.mappingInfoCuentaReqDTOtoInfoCuentaEntityList(requestDto),
					ignoringExampleMatcher);
			responseEntity = infoCuentaDAO.findAll(listFiltros,
					Sort.by(Direction.ASC, InfoCuenta.IDCUENTAVALUE));
			response = responseEntity.stream().map(
					item -> infoCuentaConverter.mappingInfoCuentaEntitytoInfoCuentaPersonaUnifiedResDTOList(item))
					.collect(Collectors.toList());
		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("db-account-movement-repository", e);
			throw e;
		}
		return response;
	}

	/**
	 * Método que retorna la paginación de una lista de InfoCuentas con
	 * filtros
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public Page<InfoCuentaPersonaUnifiedResDTO> pageListByInfoCuenta1(PageDTO<InfoCuentaPersonaUnifiedReqDTO> requestDto)
			throws GenericException {
				log.info("Metodo repository pageListByInfoCuenta iniciado");
		Page<InfoCuentaPersonaUnifiedResDTO> response = null;
		Pageable pageable;
		try {
			resourceValidators.validarPagina(requestDto);

			ExampleMatcher ignoringExampleMatcher = ExampleMatcher.matching()
					.withStringMatcher(StringMatcher.CONTAINING).withIgnoreCase();

			Example<InfoCuenta> example = Example.of(infoCuentaConverter
					.mappingInfoCuentaReqDTOtoInfoCuentaEntityList(requestDto.getTabla()),
					ignoringExampleMatcher);

			if ((requestDto.getOrder() != null) && (requestDto.getOrderValue() != null)) {
				pageable = PageRequest.of(requestDto.getPage(), requestDto.getSize(),
						Sort.by(Sort.Direction.valueOf(requestDto.getOrder()), requestDto.getOrderValue()));
			} else {
				pageable = PageRequest.of(requestDto.getPage(), requestDto.getSize(),
						Sort.Direction.ASC, InfoCuenta.IDCUENTAVALUE);
			}

			Page<InfoCuenta> responsePageEntity = infoCuentaDAO.findAll(example, pageable);
			response = responsePageEntity
					.map(infoCuentaConverter::mappingInfoCuentaEntitytoInfoCuentaPersonaUnifiedResDTOList);
		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("db-account-movement-repository", e);
			throw e;
		}
		return response;
	}

	/**
	 * Método que retorna la paginación de una lista de InfoCuentas con
	 * filtros
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public Page<InfoCuentaPersonaUnifiedResDTO> pageListByInfoCuenta(PageDTO<BusquedaGlobalDTO> requestDto)
			throws GenericException {
		log.info("Metodo repository pageListByInfoCuenta iniciado");
		Pageable pageable;
		try {
			resourceValidators.validarPagina(requestDto);

			if ((requestDto.getOrder() != null) && (requestDto.getOrderValue() != null)) {
				pageable = PageRequest.of(requestDto.getPage(), requestDto.getSize(),
						Sort.by(Sort.Direction.valueOf(requestDto.getOrder()), requestDto.getOrderValue()));
			} else {
				pageable = PageRequest.of(requestDto.getPage(), requestDto.getSize(),
						Sort.Direction.ASC, InfoCuenta.IDCUENTAVALUE);
			}

			String valorBusqueda = requestDto.getTabla().getValor(); 

			Specification<InfoCuenta> spec = (root, query, cb) -> {
				Predicate cuentaActiva = cb.equal(root.get("estado"), "ACTIVO");
				Join<InfoCuenta, InfoPersona> personaJoin = root.join("infoPersona", JoinType.LEFT);
				Join<InfoCuenta, AdmiTipoCuenta> tipoCuentaJoin = root.join("admiTipoCuenta", JoinType.LEFT);
				Predicate personaActiva = cb.equal(personaJoin.get("estado"), "ACTIVO");
				Predicate requisitosObligatorios = cb.and(cuentaActiva, personaActiva);

				if (valorBusqueda == null || valorBusqueda.trim().isEmpty()) {
					return null;
				}

				String patron = "%" + valorBusqueda.toLowerCase() + "%";

				Predicate busquedaGlobalOr = cb.or(
					cb.like(cb.lower(root.get("numeroCuenta")), patron),
					cb.like(cb.lower(root.get("estado")), patron),
					cb.like(cb.lower(personaJoin.get("nombre")), patron),
					cb.like(cb.lower(personaJoin.get("apellido")), patron),
					cb.like(cb.lower(personaJoin.get("identificacion")), patron),
					cb.like(cb.lower(tipoCuentaJoin.get("nombreCuenta")), patron)
					
				);
				return cb.and(busquedaGlobalOr);
			};
			Page<InfoCuenta> responsePageEntity = infoCuentaDAO.findAll(spec, pageable);
			return responsePageEntity.map(infoCuentaConverter::mappingInfoCuentaEntitytoInfoCuentaPersonaUnifiedResDTOList);
		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("db-account-movement-repository", e);
			throw new GenericException(e.getMessage());
		}
	}

	/**
	 * Valida si persona tiene cuentas asociadas.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public Boolean validarInfoCuentaByPersona(ObjectIdDTO request) throws GenericException {
		log.info("Metodo repository validarInfoCuentaByPersona iniciado");
		infoCuentaValidators.validarInfoCuentaByPersona(request);
		return true;
	}
}
