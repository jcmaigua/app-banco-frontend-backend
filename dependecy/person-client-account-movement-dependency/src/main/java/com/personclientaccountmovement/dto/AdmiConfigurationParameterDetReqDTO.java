package com.personclientaccountmovement.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO ADMI_CONFIGURATION_PARAMETER_DET_REQ
 * 
 * @author Juan Maigua <mailto:jmaigua@telconet.ec>
 * @version 1.0
 * @since 23/05/2026
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdmiConfigurationParameterDetReqDTO {
    private Long idConfigurationParameterDet ;
    private Long configurationParameterCabId;
    private String value;
    private String status;
    private String creationUser;
    private Date creationDate;
}
