package com.personclientaccountmovement.repository.postgres.handler.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.personclientaccountmovement.cons.PersonClientAccountMovementConfigConstants;
import com.personclientaccountmovement.dto.PersonaClienteUnifiedReqDTO;
import com.personclientaccountmovement.dto.PersonaClienteUnifiedResDTO;
import com.personclientaccountmovement.repository.postgres.dao.InfoClienteDAO;
import com.personclientaccountmovement.repository.postgres.entity.AdmiTipoCuenta;
import com.personclientaccountmovement.repository.postgres.entity.InfoCliente;
import com.personclientaccountmovement.repository.postgres.entity.InfoCuenta;
import com.personclientaccountmovement.repository.postgres.entity.InfoPersona;
import com.personclientaccountmovement.repository.postgres.handler.IInfoClienteHandler;
import com.personclientaccountmovement.repository.postgres.utils.InfoClienteConverter;
import com.personclientaccountmovement.repository.postgres.utils.InfoClienteValidators;
import com.personclientaccountmovement.repository.postgres.utils.ResourceValidators;
import com.personclientaccountmovement.dto.BusquedaGlobalDTO;
import com.personclientaccountmovement.dto.InfoClienteReqDTO;
import com.personclientaccountmovement.dto.InfoClienteResDTO;
import com.personclientaccountmovement.dto.ObjectIdDTO;
import com.personclientaccountmovement.dto.PageDTO;
import com.personclientaccountmovement.enums.EnumStatus;
import com.personclientaccountmovement.exception.GenericException;
import com.personclientaccountmovement.support.ExceptionLogSupport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
/**
* Implementación de persistencia y reglas de InfoCliente.
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Service
@Transactional(rollbackFor = { GenericException.class })
public class InfoClienteHandlerImpl implements IInfoClienteHandler {

	@Autowired
	private ResourceValidators resourceValidators;

	@Autowired
	private InfoClienteValidators infoClienteValidators;

	@Autowired
	private InfoClienteDAO infoClienteDAO;

	@Autowired
	private InfoClienteConverter infoClienteConverter;

	Logger log = LogManager.getLogger(this.getClass());

	/**
	 * Persiste el registro indicado en la solicitud.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public InfoClienteResDTO saveInfoCliente(InfoClienteReqDTO requestDTO) throws GenericException {
		log.info("Metodo repository saveInfoCliente iniciado");
		InfoClienteResDTO response = new InfoClienteResDTO();
		InfoCliente responseEntity;
		try {
			requestDTO.setEstado(EnumStatus.ACTIVO.toString());
			requestDTO.setFechaCreacion(new Date());

			infoClienteValidators.validarGuardarInfoCliente(requestDTO);

			InfoCliente request = infoClienteConverter.mappingInfoClienteReqDTOtoInfoClienteEntity(requestDTO);
			responseEntity = infoClienteDAO.save(request);

			response = infoClienteConverter.mappingInfoClienteEntitytoInfoClienteResDTO(responseEntity);
		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("db-person-repository", e);
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
	public InfoClienteResDTO updateInfoCliente(InfoClienteReqDTO requestDTO) throws GenericException {
		InfoClienteResDTO response = new InfoClienteResDTO();
		InfoClienteReqDTO requestActualDTO = new InfoClienteReqDTO();
		InfoCliente responseEntity;
		try {
			requestDTO.setFechaModificacion(new Date());
			requestActualDTO = infoClienteValidators.validarActualizarInfoCliente(requestDTO);
			requestActualDTO.setContrasena(requestDTO.getContrasena());
			requestActualDTO.setPersonaModificacion(requestDTO.getPersonaModificacion());
			InfoCliente request = infoClienteConverter.mappingInfoClienteReqDTOtoInfoClienteEntity(requestActualDTO);
			responseEntity = infoClienteDAO.save(request);
			response = infoClienteConverter.mappingInfoClienteEntitytoInfoClienteResDTO(responseEntity);
		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("db-person-repository", e);
			throw e;
		}
		return response;
	}

	/**
	 * Realiza baja lógica u operación de borrado según reglas de negocio.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public Boolean deleteInfoCliente(ObjectIdDTO requestDTO) throws GenericException {
		log.info("Metodo repository deleteInfoCliente iniciado");
		Boolean response = false;
		try {
			InfoCliente request = infoClienteValidators.validarEntidadInfoCliente(requestDTO);
			request.setEstado(EnumStatus.ELIMINADO.toString());
			request.setFechaModificacion(new Date());
			infoClienteDAO.saveAndFlush(request);
			response = true;
		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("db-person-repository", e);
			throw e;
		}
		return response;
	}

	/**
	 * Realiza baja lógica u operación de borrado según reglas de negocio.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public Boolean deleteInfoClienteByPersonaId(ObjectIdDTO request) throws GenericException {
		log.info("Metodo repository deleteInfoClienteByPersonaId iniciado");
		Boolean response = false;
		try {
			resourceValidators.validarObjectIdDTO(request);
			List<InfoCliente> listRequest = infoClienteDAO.findByPersonaIdAndEstado(request.getObjectId(), EnumStatus.ACTIVO.toString());
			for (InfoCliente item : listRequest) {
				item.setEstado(EnumStatus.ELIMINADO.toString());
				item.setFechaModificacion(new Date());
				infoClienteDAO.saveAndFlush(item);
			}
			response = true;
		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("db-person-repository", e);
			throw e;
		}
		return response;
	}

	/**
	 * Consulta listados filtrados según criterios de la solicitud.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public List<PersonaClienteUnifiedResDTO> listByInfoCliente(PersonaClienteUnifiedReqDTO requestDto) throws GenericException {
		log.info("Metodo repository listByInfoCliente iniciado");
		List<PersonaClienteUnifiedResDTO> response = new ArrayList<>();
		List<InfoCliente> responseEntity;
		try {
			infoClienteValidators.validarInfoClienteUnificado(requestDto);

			ExampleMatcher ignoringExampleMatcher = ExampleMatcher.matching()
					.withStringMatcher(StringMatcher.CONTAINING).withIgnoreCase();

			Example<InfoCliente> listFiltros = Example.of(
					infoClienteConverter.mappingPersonaClienteUnifiedReqDTOtoInfoClienteEntity(requestDto),
					ignoringExampleMatcher);
			responseEntity = infoClienteDAO.findAll(listFiltros, Sort.by(Direction.ASC, InfoCliente.IDCLIENTEVALUE));
			response = responseEntity.stream()
					.map(item -> infoClienteConverter.mappingInfoClienteEntitytoPersonaClienteUnifiedResDTO(item))
					.collect(Collectors.toList());
		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("db-person-repository", e);
			throw e;
		}
		return response;
	}

	/**
	 * Operación retrieveInfoCliente.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public PersonaClienteUnifiedResDTO retrieveInfoCliente(ObjectIdDTO request) throws GenericException {
		log.info("Metodo repository retrieveInfoCliente iniciado");
		PersonaClienteUnifiedResDTO response = new PersonaClienteUnifiedResDTO();
		try {
			InfoCliente requestEntity = infoClienteValidators.validarEntidadInfoCliente(request);

			if (requestEntity == null) {
				throw new GenericException("El cliente con el id " + request.getObjectId() + " no existe", PersonClientAccountMovementConfigConstants.MISSING_VALUES);
			}
			
			response = infoClienteConverter.mappingInfoClienteEntitytoPersonaClienteUnifiedResDTO(requestEntity);
		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("db-person-repository", e);
			throw e;
		}
		return response;
	}

	/**
	 * Consulta listados filtrados según criterios de la solicitud.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public List<InfoClienteResDTO> listByInfoClienteOrigin(InfoClienteReqDTO requestDto) throws GenericException {
		log.info("Metodo repository listByInfoClienteOrigin iniciado");
		List<InfoClienteResDTO> response = new ArrayList<>();
		List<InfoCliente> responseEntity;
		try {
			infoClienteValidators.validarInfoCliente(requestDto);

			ExampleMatcher ignoringExampleMatcher = ExampleMatcher.matching()
					.withStringMatcher(StringMatcher.CONTAINING).withIgnoreCase();

			Example<InfoCliente> listFiltros = Example.of(
					infoClienteConverter.mappingInfoClienteReqDTOtoInfoClienteEntity(requestDto),
					ignoringExampleMatcher);
			responseEntity = infoClienteDAO.findAll(listFiltros, Sort.by(Direction.ASC, InfoCliente.IDCLIENTEVALUE));
			response = responseEntity.stream()
					.map(item -> infoClienteConverter.mappingInfoClienteEntitytoInfoClienteResDTO(item))
					.collect(Collectors.toList());
		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("db-person-repository", e);
			throw e;
		}
		return response;
	}

	/**
	 * Consulta paginada con filtros y orden aplicados.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public Page<PersonaClienteUnifiedResDTO> pageListByInfoCliente(PageDTO<BusquedaGlobalDTO> requestDto)
			throws GenericException {
		log.info("Metodo repository pageListByInfoCliente iniciado");
		Pageable pageable;
		try {
			resourceValidators.validarPagina(requestDto);
			if ((requestDto.getOrder() != null) && (requestDto.getOrderValue() != null)) {
				pageable = PageRequest.of(requestDto.getPage(), requestDto.getSize(),
						Sort.by(Sort.Direction.valueOf(requestDto.getOrder()), requestDto.getOrderValue()));
			} else {
				pageable = PageRequest.of(requestDto.getPage(), requestDto.getSize(), Direction.ASC,
						InfoCliente.IDCLIENTEVALUE);
			}

			String valorBusqueda = requestDto.getTabla().getValor(); 

			Specification<InfoCliente> spec = (root, query, cb) -> {
				Predicate clienteActiva = cb.equal(root.get("estado"), "ACTIVO");
				Join<InfoCliente, InfoPersona> personaJoin = root.join("infoPersona", JoinType.LEFT);
				Predicate personaActiva = cb.equal(personaJoin.get("estado"), "ACTIVO");
				Predicate requisitosObligatorios = cb.and(clienteActiva, personaActiva);

				if (valorBusqueda == null || valorBusqueda.trim().isEmpty()) {
					return null;
				}

				String patron = "%" + valorBusqueda.toLowerCase() + "%";

				Predicate busquedaGlobalOr = cb.or(
					cb.like(cb.lower(root.get("estado")), patron),
					cb.like(cb.lower(root.get("contrasena")), patron),
					cb.like(cb.lower(personaJoin.get("nombre")), patron),
					cb.like(cb.lower(personaJoin.get("apellido")), patron),
					cb.like(cb.lower(personaJoin.get("identificacion")), patron),
					cb.like(cb.lower(personaJoin.get("genero")), patron),
					cb.like(cb.lower(personaJoin.get("telefono")), patron),
					cb.like(cb.lower(personaJoin.get("direccion")), patron)
				);
				return cb.and(busquedaGlobalOr);
			};
			Page<InfoCliente> responsePageEntity = infoClienteDAO.findAll(spec, pageable);
			return responsePageEntity.map(infoClienteConverter::mappingInfoClienteEntitytoPersonaClienteUnifiedResDTO);
		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("db-person-repository", e);
			throw e;
		}
	}
}
