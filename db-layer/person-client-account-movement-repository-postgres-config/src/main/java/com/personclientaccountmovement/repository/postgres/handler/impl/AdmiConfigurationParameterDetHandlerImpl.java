package com.personclientaccountmovement.repository.postgres.handler.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.personclientaccountmovement.dto.AdmiConfigurationParameterCabReqDTO;
import com.personclientaccountmovement.dto.AdmiConfigurationParameterDetResDTO;
import com.personclientaccountmovement.exception.GenericException;
import com.personclientaccountmovement.repository.postgres.dao.AdmiConfigurationParameterDetDAO;
import com.personclientaccountmovement.repository.postgres.entity.AdmiConfigurationParameterDet;
import com.personclientaccountmovement.repository.postgres.handler.IAdmiConfigurationParameterDetHandler;
import com.personclientaccountmovement.repository.postgres.utils.AdmiConfigurationParameterDetConverter;
import com.personclientaccountmovement.repository.postgres.utils.ResourceValidators;
import com.personclientaccountmovement.support.ExceptionLogSupport;



/**
 * Impl handler AdmiConfigurationParameterDet
 * 
 * @author Juan Maigua <mailto:jmaigua@telconet.ec>
 * @version 1.0
 * @since 11/03/2024
 */
@Service
@Transactional(rollbackFor = { GenericException.class })
public class AdmiConfigurationParameterDetHandlerImpl implements IAdmiConfigurationParameterDetHandler {


	@Autowired
	private ResourceValidators resourceValidators;

	@Autowired
	private AdmiConfigurationParameterDetDAO admiConfigurationParameterDetDAO;

	@Autowired
	private AdmiConfigurationParameterDetConverter admiConfigurationParameterDetConverter;

	/**
	 * Método que retorna la lista de AdmiConfigurationParameterDet mediante AdmiConfigurationParameterCab
	 * 
	 * @author Juan Maigua <mailto:jmaigua@telconet.ec>
	 * @version 1.0
	 * @since 23/04/2026
	 */
	@Override
	public List<AdmiConfigurationParameterDetResDTO> listParameterDetByParameterCab(
		AdmiConfigurationParameterCabReqDTO requestDto) throws GenericException {
			List<AdmiConfigurationParameterDetResDTO> response = new ArrayList<>();
			List<AdmiConfigurationParameterDet> responseEntity = new ArrayList<>();
			try {
				resourceValidators.validarConfigurationParameterCab(requestDto);
				AdmiConfigurationParameterDet request = admiConfigurationParameterDetConverter
						.mappingAdmiConfigurationParameterCabReqDTOtoAdmiConfigurationParameterDetEntity(requestDto);
				Example<AdmiConfigurationParameterDet> listFiltros = Example.of(request);
				responseEntity = admiConfigurationParameterDetDAO.findAll(listFiltros,
						Sort.by(Direction.ASC, AdmiConfigurationParameterDet.IDCONFIGURATIONPARAMETERDETVALUE));
				response = responseEntity.stream()
						.map(item -> admiConfigurationParameterDetConverter
								.mappingAdmiConfigurationParameterDetEntitytoAdmiConfigurationParameterDetResDTO(item))
						.collect(Collectors.toList());
			} catch (GenericException e) {
				ExceptionLogSupport.logWarn("db-account-movement-repository", e);
				throw e;
			}
			return response;
		}

}
