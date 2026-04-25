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

import com.personclientaccountmovement.dto.AdmiTipoCuentaReqDTO;
import com.personclientaccountmovement.dto.AdmiTipoCuentaResDTO;
import com.personclientaccountmovement.dto.ObjectIdDTO;
import com.personclientaccountmovement.dto.PageDTO;
import com.personclientaccountmovement.enums.EnumStatus;
import com.personclientaccountmovement.exception.GenericException;
import com.personclientaccountmovement.support.ExceptionLogSupport;
import com.personclientaccountmovement.repository.postgres.dao.AdmiTipoCuentaDAO;
import com.personclientaccountmovement.repository.postgres.entity.AdmiTipoCuenta;
import com.personclientaccountmovement.repository.postgres.handler.IAdmiTipoCuentaHandler;
import com.personclientaccountmovement.repository.postgres.utils.AdmiTipoCuentaConverter;
import com.personclientaccountmovement.repository.postgres.utils.AdmiTipoCuentaValidators;
import com.personclientaccountmovement.repository.postgres.utils.ResourceValidators;

/**
* Impl handler AdmiTipoCuenta
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Service
@Transactional(rollbackFor = { GenericException.class })
public class AdmiTipoCuentaHandlerImpl implements IAdmiTipoCuentaHandler {

	Logger log = LogManager.getLogger(this.getClass());

	@Autowired
	private ResourceValidators resourceValidators;

	@Autowired
	private AdmiTipoCuentaValidators admiTipoCuentaValidators;

	@Autowired
	private AdmiTipoCuentaDAO admiTipoCuentaDAO;

	@Autowired
	private AdmiTipoCuentaConverter admiTipoCuentaConverter;

	/**
	 * Método que guarda un AdmiTipoCuenta
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public AdmiTipoCuentaResDTO saveAdmiTipoCuenta(AdmiTipoCuentaReqDTO requestDTO) throws GenericException {
		log.info("Metodo repository saveAdmiTipoCuenta iniciado");
		AdmiTipoCuentaResDTO response = new AdmiTipoCuentaResDTO();
		AdmiTipoCuenta responseEntity = new AdmiTipoCuenta();
		try {
			requestDTO.setEstado(EnumStatus.ACTIVO.toString());
			requestDTO.setFechaCreacion(new Date());

			responseEntity = admiTipoCuentaValidators.validarGuardarAdmiTipoCuenta(requestDTO);

			if (responseEntity == null) {
				AdmiTipoCuenta request = admiTipoCuentaConverter
					.mappingAdmiTipoCuentaReqDTOtoAdmiTipoCuentaEntity(requestDTO);
				responseEntity = admiTipoCuentaDAO.save(request);
			} 
			
			response = admiTipoCuentaConverter
					.mappingAdmiTipoCuentaEntitytoAdmiTipoCuentaResDTO(responseEntity);
		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("db-account-movement-repository", e);
			throw e;
		}
		return response;
	}

	/**
	 * Método que actualiza un AdmiTipoCuenta
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public AdmiTipoCuentaResDTO updateAdmiTipoCuenta(AdmiTipoCuentaReqDTO requestDTO) throws GenericException {
		log.info("Metodo repository updateAdmiTipoCuenta iniciado");
		AdmiTipoCuentaResDTO response = new AdmiTipoCuentaResDTO();
		AdmiTipoCuenta responseEntity = new AdmiTipoCuenta();
		try {
			requestDTO.setFechaModificacion(new Date());
			admiTipoCuentaValidators.validarActualizarAdmiTipoCuenta(requestDTO);
			AdmiTipoCuenta request = admiTipoCuentaConverter
					.mappingAdmiTipoCuentaReqDTOtoAdmiTipoCuentaEntity(requestDTO);
			responseEntity = admiTipoCuentaDAO.save(request);
			response = admiTipoCuentaConverter
					.mappingAdmiTipoCuentaEntitytoAdmiTipoCuentaResDTO(responseEntity);
		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("db-account-movement-repository", e);
			throw e;
		}
		return response;
	}

	/**
	 * Método que elimina un AdmiTipoCuenta
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public Boolean deleteAdmiTipoCuenta(ObjectIdDTO requestDTO) throws GenericException {
		log.info("Metodo repository deleteAdmiTipoCuenta iniciado");
		Boolean response = false;
		try {
			AdmiTipoCuenta request = admiTipoCuentaValidators.validarEntidadAdmiTipoCuenta(requestDTO);
			request.setEstado(EnumStatus.ELIMINADO.toString());
			request.setFechaModificacion(new Date());
			admiTipoCuentaDAO.saveAndFlush(request);
			response = true;
		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("db-account-movement-repository", e);
			throw e;
		}
		return response;
	}

	/**
	 * Método que retorna la lista de AdmiTipoCuentas con filtros
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public List<AdmiTipoCuentaResDTO> listByAdmiTipoCuenta(AdmiTipoCuentaReqDTO requestDto)
			throws GenericException {
				log.info("Metodo repository listByAdmiTipoCuenta iniciado");
		List<AdmiTipoCuentaResDTO> response = new ArrayList<>();
		List<AdmiTipoCuenta> responseEntity = new ArrayList<>();
		try {
			admiTipoCuentaValidators.validarAdmiTipoCuenta(requestDto);

			ExampleMatcher ignoringExampleMatcher = ExampleMatcher.matching()
					.withStringMatcher(StringMatcher.CONTAINING).withIgnoreCase();

			Example<AdmiTipoCuenta> listFiltros = Example.of(admiTipoCuentaConverter
					.mappingAdmiTipoCuentaReqDTOtoAdmiTipoCuentaEntity(requestDto),
					ignoringExampleMatcher);
			responseEntity = admiTipoCuentaDAO.findAll(listFiltros,
					Sort.by(Direction.ASC, AdmiTipoCuenta.IDTIPOCUENTAVALUE));
			response = responseEntity.stream().map(
					item -> admiTipoCuentaConverter.mappingAdmiTipoCuentaEntitytoAdmiTipoCuentaResDTO(item))
					.collect(Collectors.toList());
		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("db-account-movement-repository", e);
			throw e;
		}
		return response;
	}

	/**
	 * Método que retorna la paginación de una lista de AdmiTipoCuentas con
	 * filtros
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public Page<AdmiTipoCuentaResDTO> pageListByAdmiTipoCuenta(PageDTO<AdmiTipoCuentaReqDTO> requestDto)
			throws GenericException {
				log.info("Metodo repository pageListByAdmiTipoCuenta iniciado");
		Page<AdmiTipoCuentaResDTO> response = null;
		Pageable pageable;
		try {
			resourceValidators.validarPagina(requestDto);

			ExampleMatcher ignoringExampleMatcher = ExampleMatcher.matching()
					.withStringMatcher(StringMatcher.CONTAINING).withIgnoreCase();

			Example<AdmiTipoCuenta> example = Example.of(admiTipoCuentaConverter
					.mappingAdmiTipoCuentaReqDTOtoAdmiTipoCuentaEntity(requestDto.getTabla()),
					ignoringExampleMatcher);

			if ((requestDto.getOrder() != null) && (requestDto.getOrderValue() != null)) {
				pageable = PageRequest.of(requestDto.getPage(), requestDto.getSize(),
						Sort.by(Sort.Direction.valueOf(requestDto.getOrder()), requestDto.getOrderValue()));
			} else {
				pageable = PageRequest.of(requestDto.getPage(), requestDto.getSize(),
						Sort.Direction.ASC, AdmiTipoCuenta.IDTIPOCUENTAVALUE);
			}

			Page<AdmiTipoCuenta> responsePageEntity = admiTipoCuentaDAO.findAll(example, pageable);
			response = responsePageEntity
					.map(admiTipoCuentaConverter::mappingAdmiTipoCuentaEntitytoAdmiTipoCuentaResDTO);
		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("db-account-movement-repository", e);
			throw e;
		}
		return response;
	}
}
