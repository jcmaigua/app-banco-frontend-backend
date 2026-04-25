package com.personclientaccountmovement.repository.postgres.handler;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.personclientaccountmovement.dto.AdmiConfigurationParameterCabReqDTO;
import com.personclientaccountmovement.dto.AdmiConfigurationParameterDetReqDTO;
import com.personclientaccountmovement.dto.AdmiConfigurationParameterDetResDTO;
import com.personclientaccountmovement.dto.ObjectIdDTO;
import com.personclientaccountmovement.dto.PageDTO;
import com.personclientaccountmovement.exception.GenericException;


/**
 * Handler ADMI_CONFIGURATION_PARAMETER_DET
 *
 * @author Juan Maigua <mailto:jmaigua@telconet.ec>
 * @version 1.0
 * @since 23/04/2026
 */
@Service
public interface IAdmiConfigurationParameterDetHandler {

	public List<AdmiConfigurationParameterDetResDTO> listParameterDetByParameterCab(
		AdmiConfigurationParameterCabReqDTO request) throws GenericException;
}
