package com.personclientaccountmovement.repository.postgres.entity;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import jakarta.persistence.*;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.personclientaccountmovement.repository.postgres.utils.MsClientesCuentasConstants;


/**
 * Entidad ADMI_CONFIGURATION_PARAMETER_DET
 * 
 * @author Juan Maigua <mailto:jmaigua@telconet.ec>
 * @version 1.0
 * @since 05/03/2024
 */
@Data
@Entity
@Table(name = "ADMI_CONFIGURATION_PARAMETER_DET", schema = MsClientesCuentasConstants.SCHEMA_CLIENTES_CUENTAS)
public class AdmiConfigurationParameterDet {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_configuration_parameter_det")
    @SequenceGenerator(sequenceName = MsClientesCuentasConstants.SCHEMA_CLIENTES_CUENTAS + 
    ".seq_configuration_parameter_det", allocationSize = 1, initialValue = 1, name = "seq_configuration_parameter_det")
    @Column(name = "id_configuration_parameter_det")
    private Long idConfigurationParameterDet ;
    @Column(name = "configuration_parameter_cab_id")
    private Long configurationParameterCabId;
    @Column(name = "value")
    private String value;
    @Column(name = "status")
    private String status;
    @Column(name = "creation_user")
    private String creationUser;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = MsClientesCuentasConstants.TIMEZONE_DATE)
    @Column(name = "creation_date")
    private Date creationDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "configuration_parameter_cab_id", insertable=false, updatable=false)
    private AdmiConfigurationParameterCab admiConfigurationParameterCab;

    public static final String IDCONFIGURATIONPARAMETERDETVALUE = "idConfigurationParameterDet";
}
