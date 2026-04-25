package com.personclientaccountmovement.repository.postgres.utils;


import org.springframework.stereotype.Service;

import com.personclientaccountmovement.dto.AdmiConfigurationParameterCabReqDTO;
import com.personclientaccountmovement.dto.AdmiConfigurationParameterDetResDTO;
import com.personclientaccountmovement.enums.EnumStatus;
import com.personclientaccountmovement.repository.postgres.entity.AdmiConfigurationParameterCab;
import com.personclientaccountmovement.repository.postgres.entity.AdmiConfigurationParameterDet;


@Service
public class AdmiConfigurationParameterDetConverter {

        public AdmiConfigurationParameterDet mappingAdmiConfigurationParameterCabReqDTOtoAdmiConfigurationParameterDetEntity(
                        AdmiConfigurationParameterCabReqDTO requestDTO) {
                AdmiConfigurationParameterCab admiConfigurationParameterCab = new AdmiConfigurationParameterCab();
                admiConfigurationParameterCab.setIdConfigurationParameterCab((requestDTO.getIdConfigurationParameterCab() != null
                && requestDTO.getIdConfigurationParameterCab() != 0) ? requestDTO.getIdConfigurationParameterCab() : null);
                admiConfigurationParameterCab.setName((requestDTO.getName() == null || requestDTO.getName().isEmpty()
                                                || requestDTO.getName().trim().isEmpty()) ? null
                                                                : requestDTO.getName());
                AdmiConfigurationParameterDet admiConfigurationParameterDet = new AdmiConfigurationParameterDet();
                admiConfigurationParameterDet.setStatus(EnumStatus.ACTIVO.toString());
                admiConfigurationParameterDet.setAdmiConfigurationParameterCab(admiConfigurationParameterCab);
                return admiConfigurationParameterDet;
        }

        public AdmiConfigurationParameterDetResDTO mappingAdmiConfigurationParameterDetEntitytoAdmiConfigurationParameterDetResDTO(
                        AdmiConfigurationParameterDet response) {
                AdmiConfigurationParameterDetResDTO admiConfigurationParameterDetDTO = new AdmiConfigurationParameterDetResDTO();
                admiConfigurationParameterDetDTO.setIdConfigurationParameterDet((response.getIdConfigurationParameterDet() != null
                && response.getIdConfigurationParameterDet() != 0) ? response.getIdConfigurationParameterDet() : null);
                admiConfigurationParameterDetDTO
                                .setConfigurationParameterCabId((response.getConfigurationParameterCabId() != null
                                && response.getConfigurationParameterCabId() != 0) ? response.getConfigurationParameterCabId() : null);
                admiConfigurationParameterDetDTO.setConfigurationParameterCabName((response.getAdmiConfigurationParameterCab().getName() == null || 
                                response.getAdmiConfigurationParameterCab().getName().isEmpty()
                                || response.getAdmiConfigurationParameterCab().getName() .trim().isEmpty()) ? null
                                                : response.getAdmiConfigurationParameterCab().getName());
                admiConfigurationParameterDetDTO.setValue(response.getValue());
                admiConfigurationParameterDetDTO.setStatus(response.getStatus());
                admiConfigurationParameterDetDTO.setCreationUser(response.getCreationUser());
                admiConfigurationParameterDetDTO.setCreationDate(response.getCreationDate());
                return admiConfigurationParameterDetDTO;
        }
  
}
