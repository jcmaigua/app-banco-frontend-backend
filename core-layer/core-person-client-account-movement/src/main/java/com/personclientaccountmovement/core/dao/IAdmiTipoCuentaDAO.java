package com.personclientaccountmovement.core.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.personclientaccountmovement.dto.AdmiTipoCuentaReqDTO;
import com.personclientaccountmovement.dto.AdmiTipoCuentaResDTO;
import com.personclientaccountmovement.dto.ObjectIdDTO;
import com.personclientaccountmovement.dto.PageDTO;
import com.personclientaccountmovement.exception.GenericException;

/**
* DAO AdmiTipoCuenta
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Service
public interface IAdmiTipoCuentaDAO {
	public AdmiTipoCuentaResDTO saveAdmiTipoCuenta(
			AdmiTipoCuentaReqDTO request) throws GenericException;

	public AdmiTipoCuentaResDTO updateAdmiTipoCuenta(
			AdmiTipoCuentaReqDTO request) throws GenericException;

	public Boolean deleteAdmiTipoCuenta(ObjectIdDTO request) throws GenericException;

	public List<AdmiTipoCuentaResDTO> listByAdmiTipoCuenta(
			AdmiTipoCuentaReqDTO request) throws GenericException;

	public Page<AdmiTipoCuentaResDTO> pageListByAdmiTipoCuenta(
			PageDTO<AdmiTipoCuentaReqDTO> request) throws GenericException;
}
