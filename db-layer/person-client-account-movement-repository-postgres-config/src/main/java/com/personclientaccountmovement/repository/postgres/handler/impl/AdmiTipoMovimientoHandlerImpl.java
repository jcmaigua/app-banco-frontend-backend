package com.personclientaccountmovement.repository.postgres.handler.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.personclientaccountmovement.dto.AdmiTipoMovimientoReqDTO;
import com.personclientaccountmovement.dto.AdmiTipoMovimientoResDTO;
import com.personclientaccountmovement.dto.ObjectIdDTO;
import com.personclientaccountmovement.dto.PageDTO;
import com.personclientaccountmovement.enums.EnumStatus;
import com.personclientaccountmovement.exception.GenericException;
import com.personclientaccountmovement.support.ExceptionLogSupport;
import com.personclientaccountmovement.repository.postgres.dao.AdmiTipoMovimientoDAO;
import com.personclientaccountmovement.repository.postgres.entity.AdmiTipoMovimiento;
import com.personclientaccountmovement.repository.postgres.handler.IAdmiTipoMovimientoHandler;
import com.personclientaccountmovement.repository.postgres.utils.AdmiTipoMovimientoConverter;
import com.personclientaccountmovement.repository.postgres.utils.AdmiTipoMovimientoValidators;
import com.personclientaccountmovement.repository.postgres.utils.ResourceValidators;

/**
* Impl handler AdmiTipoMovimiento
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Service
@Transactional(rollbackFor = { GenericException.class })
public class AdmiTipoMovimientoHandlerImpl implements IAdmiTipoMovimientoHandler {

	Logger log = LogManager.getLogger(this.getClass());

	@Autowired
	private ResourceValidators resourceValidators;

	@Autowired
	private AdmiTipoMovimientoValidators admiTipoMovimientoValidators;

	@Autowired
	private AdmiTipoMovimientoDAO admiTipoMovimientoDAO;

	@Autowired
	private AdmiTipoMovimientoConverter admiTipoMovimientoConverter;

	/**
	 * Método que guarda un AdmiTipoMovimiento
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public AdmiTipoMovimientoResDTO saveAdmiTipoMovimiento(AdmiTipoMovimientoReqDTO requestDTO) throws GenericException {
		log.info("Metodo repository saveAdmiTipoMovimiento iniciado");
		AdmiTipoMovimientoResDTO response = new AdmiTipoMovimientoResDTO();
		AdmiTipoMovimiento responseEntity = new AdmiTipoMovimiento();
		try {
			requestDTO.setEstado(EnumStatus.ACTIVO.toString());
			requestDTO.setFechaCreacion(new Date());

			responseEntity = admiTipoMovimientoValidators.validarGuardarAdmiTipoMovimiento(requestDTO);

			if (responseEntity == null) {
				AdmiTipoMovimiento request = admiTipoMovimientoConverter
					.mappingAdmiTipoMovimientoReqDTOtoAdmiTipoMovimientoEntity(requestDTO);
				responseEntity = admiTipoMovimientoDAO.save(request);
			} 
			
			response = admiTipoMovimientoConverter
					.mappingAdmiTipoMovimientoEntitytoAdmiTipoMovimientoResDTO(responseEntity);
		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("db-account-movement-repository", e);
			throw e;
		}
		return response;
	}

	/**
	 * Método que actualiza un AdmiTipoMovimiento
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public AdmiTipoMovimientoResDTO updateAdmiTipoMovimiento(AdmiTipoMovimientoReqDTO requestDTO) throws GenericException {
		log.info("Metodo repository updateAdmiTipoMovimiento iniciado");
		AdmiTipoMovimientoResDTO response = new AdmiTipoMovimientoResDTO();
		AdmiTipoMovimiento responseEntity = new AdmiTipoMovimiento();
		try {
			requestDTO.setFechaModificacion(new Date());
			admiTipoMovimientoValidators.validarActualizarAdmiTipoMovimiento(requestDTO);
			AdmiTipoMovimiento request = admiTipoMovimientoConverter
					.mappingAdmiTipoMovimientoReqDTOtoAdmiTipoMovimientoEntity(requestDTO);
			responseEntity = admiTipoMovimientoDAO.save(request);
			response = admiTipoMovimientoConverter
					.mappingAdmiTipoMovimientoEntitytoAdmiTipoMovimientoResDTO(responseEntity);
		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("db-account-movement-repository", e);
			throw e;
		}
		return response;
	}

	/**
	 * Método que elimina un AdmiTipoMovimiento
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public Boolean deleteAdmiTipoMovimiento(ObjectIdDTO requestDTO) throws GenericException {
		log.info("Metodo repository deleteAdmiTipoMovimiento iniciado");
		Boolean response = false;
		try {
			AdmiTipoMovimiento request = admiTipoMovimientoValidators.validarEntidadAdmiTipoMovimiento(requestDTO);
			request.setEstado(EnumStatus.ELIMINADO.toString());
			request.setFechaModificacion(new Date());
			admiTipoMovimientoDAO.saveAndFlush(request);
			response = true;
		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("db-account-movement-repository", e);
			throw e;
		}
		return response;
	}

	/**
	 * Método que retorna la lista de AdmiTipoMovimientos con filtros
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public List<AdmiTipoMovimientoResDTO> listByAdmiTipoMovimiento(AdmiTipoMovimientoReqDTO requestDto)
			throws GenericException {
				log.info("Metodo repository listByAdmiTipoMovimiento iniciado");
		List<AdmiTipoMovimientoResDTO> response = new ArrayList<>();
		List<AdmiTipoMovimiento> responseEntity = new ArrayList<>();
		try {
			admiTipoMovimientoValidators.validarAdmiTipoMovimiento(requestDto);

			ExampleMatcher ignoringExampleMatcher = ExampleMatcher.matching()
					.withStringMatcher(StringMatcher.CONTAINING).withIgnoreCase();

			Example<AdmiTipoMovimiento> listFiltros = Example.of(admiTipoMovimientoConverter
					.mappingAdmiTipoMovimientoReqDTOtoAdmiTipoMovimientoEntity(requestDto),
					ignoringExampleMatcher);
			responseEntity = admiTipoMovimientoDAO.findAll(listFiltros,
					Sort.by(Direction.ASC, AdmiTipoMovimiento.IDTIPOMOVIMIENTOVALUE));
			response = responseEntity.stream().map(
					item -> admiTipoMovimientoConverter.mappingAdmiTipoMovimientoEntitytoAdmiTipoMovimientoResDTO(item))
					.collect(Collectors.toList());
		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("db-account-movement-repository", e);
			throw e;
		}
		return response;
	}

	/**
	 * Método que retorna la paginación de una lista de AdmiTipoMovimientos con
	 * filtros
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public Page<AdmiTipoMovimientoResDTO> pageListByAdmiTipoMovimiento(PageDTO<AdmiTipoMovimientoReqDTO> requestDto)
			throws GenericException {
				log.info("Metodo repository pageListByAdmiTipoMovimiento iniciado");
		Page<AdmiTipoMovimientoResDTO> response = null;
		Pageable pageable;
		try {
			resourceValidators.validarPagina(requestDto);

			ExampleMatcher ignoringExampleMatcher = ExampleMatcher.matching()
					.withStringMatcher(StringMatcher.CONTAINING).withIgnoreCase();

			Example<AdmiTipoMovimiento> example = Example.of(admiTipoMovimientoConverter
					.mappingAdmiTipoMovimientoReqDTOtoAdmiTipoMovimientoEntity(requestDto.getTabla()),
					ignoringExampleMatcher);

			if ((requestDto.getOrder() != null) && (requestDto.getOrderValue() != null)) {
				pageable = PageRequest.of(requestDto.getPage(), requestDto.getSize(),
						Sort.by(Sort.Direction.valueOf(requestDto.getOrder()), requestDto.getOrderValue()));
			} else {
				pageable = PageRequest.of(requestDto.getPage(), requestDto.getSize(),
						Sort.Direction.ASC, AdmiTipoMovimiento.IDTIPOMOVIMIENTOVALUE);
			}

			Page<AdmiTipoMovimiento> responsePageEntity = admiTipoMovimientoDAO.findAll(example, pageable);
			response = responsePageEntity
					.map(admiTipoMovimientoConverter::mappingAdmiTipoMovimientoEntitytoAdmiTipoMovimientoResDTO);
		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("db-account-movement-repository", e);
			throw e;
		}
		return response;
	}
}
