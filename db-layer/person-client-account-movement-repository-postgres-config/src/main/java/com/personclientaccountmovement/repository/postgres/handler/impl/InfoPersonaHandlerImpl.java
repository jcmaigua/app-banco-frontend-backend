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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.personclientaccountmovement.repository.postgres.dao.InfoPersonaDAO;
import com.personclientaccountmovement.repository.postgres.entity.InfoPersona;
import com.personclientaccountmovement.repository.postgres.handler.IInfoClienteHandler;
import com.personclientaccountmovement.repository.postgres.handler.IInfoPersonaHandler;
import com.personclientaccountmovement.repository.postgres.utils.InfoClienteConverter;
import com.personclientaccountmovement.repository.postgres.utils.InfoClienteValidators;
import com.personclientaccountmovement.repository.postgres.utils.InfoPersonaConverter;
import com.personclientaccountmovement.repository.postgres.utils.InfoPersonaValidators;
import com.personclientaccountmovement.repository.postgres.utils.ResourceValidators;
import com.personclientaccountmovement.cons.PersonClientAccountMovementConfigConstants;
import com.personclientaccountmovement.dto.InfoClienteReqDTO;
import com.personclientaccountmovement.dto.InfoClienteResDTO;
import com.personclientaccountmovement.dto.InfoPersonaReqDTO;
import com.personclientaccountmovement.dto.InfoPersonaResDTO;
import com.personclientaccountmovement.dto.ObjectIdDTO;
import com.personclientaccountmovement.dto.PageDTO;
import com.personclientaccountmovement.dto.PersonaClienteUnifiedReqDTO;
import com.personclientaccountmovement.dto.PersonaClienteUnifiedResDTO;
import com.personclientaccountmovement.enums.EnumStatus;
import com.personclientaccountmovement.exception.GenericException;
import com.personclientaccountmovement.support.ExceptionLogSupport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
* Implementación de persistencia y reglas de InfoPersona.
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Service
@Transactional(rollbackFor = { GenericException.class })
public class InfoPersonaHandlerImpl implements IInfoPersonaHandler {

	@Autowired
	private ResourceValidators resourceValidators;

	@Autowired
	private InfoPersonaValidators infoPersonaValidators;

	@Autowired
	private InfoClienteValidators infoClienteValidators;

	@Autowired
	private InfoPersonaDAO infoPersonaDAO;

	@Autowired
	private InfoPersonaConverter infoPersonaConverter;

	@Autowired
	private InfoClienteConverter infoClienteConverter;

	@Autowired
	private IInfoClienteHandler infoClienteHandler;

	Logger log = LogManager.getLogger(this.getClass());

	/**
	 * Persiste el registro indicado en la solicitud.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public PersonaClienteUnifiedResDTO saveInfoPersona(PersonaClienteUnifiedReqDTO personaClienteUnifiedReqDTO) throws GenericException {
		log.info("Metodo repository saveInfoPersona iniciado");
		PersonaClienteUnifiedResDTO response = new PersonaClienteUnifiedResDTO();
		try {
			infoPersonaValidators.validarPersonaClienteUnifiedReqDTO(personaClienteUnifiedReqDTO);
			InfoPersonaReqDTO personaReq = infoPersonaConverter.toInfoPersonaReqForUnifiedAlta(personaClienteUnifiedReqDTO);
			personaReq.setPersonaCreacion(personaClienteUnifiedReqDTO.getUserRequest());
			personaReq.setFechaCreacion(new Date());
			personaReq.setEstado(EnumStatus.ACTIVO.toString());
			infoPersonaValidators.validarGuardarInfoPersona(personaReq);

			InfoPersona personaEntity = infoPersonaConverter.mappingInfoPersonaReqDTOtoInfoPersonaEntity(personaReq);
			InfoPersona personaGuardado = infoPersonaDAO.save(personaEntity);
			InfoPersonaResDTO personaRes = infoPersonaConverter.mappingInfoPersonaEntitytoInfoPersonaResDTO(personaGuardado);
			InfoClienteReqDTO clienteReq = infoClienteConverter.mappingPersonaClienteUnifiedReqDTOtoInfoClienteReqDTO(personaRes, personaClienteUnifiedReqDTO);
			InfoClienteResDTO clienteRes = infoClienteHandler.saveInfoCliente(clienteReq);
			response = infoPersonaConverter.toPersonaClienteUnifiedRes(personaRes, clienteRes.getIdCliente(),
					clienteRes.getContrasena());
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
	public PersonaClienteUnifiedResDTO updateInfoPersona(PersonaClienteUnifiedReqDTO personaClienteUnifiedReqDTO) throws GenericException {
		log.info("Metodo repository updateInfoPersona iniciado");
		try {
			infoPersonaValidators.validarActualizarPersonaClienteUnifiedReqDTO(personaClienteUnifiedReqDTO);
			Date ahora = new Date();
			String user = personaClienteUnifiedReqDTO.getUserRequest() != null && !personaClienteUnifiedReqDTO.getUserRequest().isBlank()
					? personaClienteUnifiedReqDTO.getUserRequest()
					: null;

			InfoPersona personaEnt = infoPersonaDAO.findById(personaClienteUnifiedReqDTO.getIdPersona()).orElse(null);
			if (personaEnt == null) {
				throw new GenericException("Persona no encontrado", PersonClientAccountMovementConfigConstants.MISSING_VALUES);
			}
			InfoPersonaResDTO personaDb = infoPersonaConverter.mappingInfoPersonaEntitytoInfoPersonaResDTO(personaEnt);

			InfoPersonaResDTO personaRes = personaDb;
			if (infoPersonaValidators.personaPerfilCambia(personaDb, personaClienteUnifiedReqDTO)) {
				InfoPersonaReqDTO uu = infoPersonaConverter.toInfoPersonaReqForUnifiedUpdate(personaDb, personaClienteUnifiedReqDTO);
				uu.setPersonaModificacion(personaClienteUnifiedReqDTO.getUserRequest());
				uu.setFechaModificacion(new Date());
				infoPersonaValidators.validarActualizarInfoPersona(uu);
				InfoPersona request = infoPersonaConverter.mappingInfoPersonaReqDTOtoInfoPersonaEntity(uu);
				InfoPersona saved = infoPersonaDAO.save(request);
				personaRes = infoPersonaConverter.mappingInfoPersonaEntitytoInfoPersonaResDTO(saved);
			}

			List<InfoClienteResDTO> clientes = infoClienteHandler.listByInfoClienteOrigin(
					InfoClienteReqDTO.builder().personaId(personaClienteUnifiedReqDTO.getIdPersona()).build());
			InfoClienteResDTO clienteDb = infoClienteValidators.resolverClienteObjetivo(clientes, personaClienteUnifiedReqDTO.getIdCliente());

			InfoClienteResDTO clienteRes = clienteDb;
			if (infoClienteValidators.contrasenaCambia(clienteDb, personaClienteUnifiedReqDTO)) {
				InfoClienteReqDTO upd = infoClienteConverter.mappingPersonaClienteUnifiedReqDTOtoInfoClienteReqDTO(
						clienteDb, personaClienteUnifiedReqDTO);
				clienteRes = infoClienteHandler.updateInfoCliente(upd);
			}
			return infoPersonaConverter.toPersonaClienteUnifiedRes(personaRes, clienteRes.getIdCliente(),
					clienteRes.getContrasena());
		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("db-person-repository", e);
			throw e;
		}
	}

	/**
	 * Realiza baja lógica u operación de borrado según reglas de negocio.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public Boolean deleteInfoPersona(ObjectIdDTO requestDTO) throws GenericException {
		log.info("Metodo repository deleteInfoPersona iniciado");
		Boolean response = false;
		try {
			InfoPersona request = infoPersonaValidators.validarEntidadInfoPersona(requestDTO);
			request.setEstado(EnumStatus.ELIMINADO.toString());
			request.setFechaModificacion(new Date());
			infoPersonaDAO.saveAndFlush(request);
			infoClienteHandler.deleteInfoClienteByPersonaId(requestDTO);
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
	public List<InfoPersonaResDTO> listByInfoPersona(InfoPersonaReqDTO requestDto) throws GenericException {
		log.info("Metodo repository listByInfoPersona iniciado");
		List<InfoPersonaResDTO> response = new ArrayList<>();
		List<InfoPersona> responseEntity;
		try {
			infoPersonaValidators.validarInfoPersona(requestDto);

			ExampleMatcher ignoringExampleMatcher = ExampleMatcher.matching()
					.withStringMatcher(StringMatcher.CONTAINING).withIgnoreCase();

			Example<InfoPersona> listFiltros = Example.of(
					infoPersonaConverter.mappingInfoPersonaReqDTOtoInfoPersonaEntity(requestDto),
					ignoringExampleMatcher);
			responseEntity = infoPersonaDAO.findAll(listFiltros, Sort.by(Direction.ASC, InfoPersona.IDUSUARIOVALUE));
			response = responseEntity.stream()
					.map(item -> infoPersonaConverter.mappingInfoPersonaEntitytoInfoPersonaResDTO(item))
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
	public Page<InfoPersonaResDTO> pageListByInfoPersona(PageDTO<InfoPersonaReqDTO> requestDto)
			throws GenericException {
		log.info("Metodo repository pageListByInfoPersona iniciado");
		Page<InfoPersonaResDTO> response;
		Pageable pageable;
		try {
			resourceValidators.validarPagina(requestDto);

			ExampleMatcher ignoringExampleMatcher = ExampleMatcher.matching()
					.withStringMatcher(StringMatcher.CONTAINING).withIgnoreCase();

			Example<InfoPersona> example = Example.of(
					infoPersonaConverter.mappingInfoPersonaReqDTOtoInfoPersonaEntity(requestDto.getTabla()),
					ignoringExampleMatcher);

			if ((requestDto.getOrder() != null) && (requestDto.getOrderValue() != null)) {
				pageable = PageRequest.of(requestDto.getPage(), requestDto.getSize(),
						Sort.by(Sort.Direction.valueOf(requestDto.getOrder()), requestDto.getOrderValue()));
			} else {
				pageable = PageRequest.of(requestDto.getPage(), requestDto.getSize(), Direction.ASC,
						InfoPersona.IDUSUARIOVALUE);
			}

			Page<InfoPersona> responsePageEntity = infoPersonaDAO.findAll(example, pageable);
			response = responsePageEntity.map(infoPersonaConverter::mappingInfoPersonaEntitytoInfoPersonaResDTO);
		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("db-person-repository", e);
			throw e;
		}
		return response;
	}

	/**
	 * Operación retrieveInfoPersona.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public PersonaClienteUnifiedResDTO retrieveInfoPersona(ObjectIdDTO request) throws GenericException {
		log.info("Metodo repository retrieveInfoPersona iniciado");
		try {
			InfoPersona entity = infoPersonaValidators.validarEntidadInfoPersona(request);
			InfoPersonaResDTO personaRes = infoPersonaConverter.mappingInfoPersonaEntitytoInfoPersonaResDTO(entity);
			return infoPersonaConverter.toPersonaClienteUnifiedRes(personaRes, null, null);
		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("db-person-repository", e);
			throw e;
		}
	}
}
