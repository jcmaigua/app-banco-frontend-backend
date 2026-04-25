package com.personclientaccountmovement.repository.postgres.entity;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import jakarta.persistence.*;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.personclientaccountmovement.repository.postgres.utils.MsClientesCuentasConstants;

/**
 * Entidad ADMI_CONFIGURATION_PARAMETER_CAB
 * 
 * @author Juan Maigua <mailto:jmaigua@telconet.ec>
 * @version 1.0
 * @since 05/03/2026
 */
@Data
@Entity
@Table(name = "ADMI_CONFIGURATION_PARAMETER_CAB", schema = MsClientesCuentasConstants.SCHEMA_CLIENTES_CUENTAS)
public class AdmiConfigurationParameterCab {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_configuration_parameter_cab")
    @SequenceGenerator(sequenceName = MsClientesCuentasConstants.SCHEMA_CLIENTES_CUENTAS + 
    ".seq_configuration_parameter_cab", allocationSize = 1, initialValue = 1, name = "seq_configuration_parameter_cab")
    @Column(name = "id_configuration_parameter_cab")
    private Long idConfigurationParameterCab;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "status")
    private String status;
    @Column(name = "creation_user")
    private String creationUser;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = MsClientesCuentasConstants.TIMEZONE_DATE)
    @Column(name = "creation_date")
    private Date creationDate;

    public static final String IDCONFIGURATIONPARAMETERCABVALUE = "idConfigurationParameterCab";
}
