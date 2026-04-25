package com.personclientaccountmovement.repository.postgres.entity;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import jakarta.persistence.*;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.personclientaccountmovement.repository.postgres.utils.MsClientesCuentasConstants;

/**
* Entidad ADMI_TIPO_CUENTA
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Data
@Entity
@Table(name = "ADMI_TIPO_CUENTA", schema = MsClientesCuentasConstants.SCHEMA_CLIENTES_CUENTAS)
public class AdmiTipoCuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tipo_cuenta")
    @SequenceGenerator(sequenceName = MsClientesCuentasConstants.SCHEMA_CLIENTES_CUENTAS +
            ".seq_tipo_cuenta", allocationSize = 1, initialValue = 1, name = "seq_tipo_cuenta")
    @Column(name = "id_tipo_cuenta")
    private Long idTipoCuenta;

    @Column(name = "nombre_cuenta", unique = true)
    private String nombreCuenta;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "estado")
    private String estado;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = MsClientesCuentasConstants.TIMEZONE_DATE)
    @Column(name = "fecha_creacion")
    private Date fechaCreacion;

    @Column(name = "persona_creacion")
    private String personaCreacion;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = MsClientesCuentasConstants.TIMEZONE_DATE)
    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;

    @Column(name = "persona_modificacion")
    private String personaModificacion;

    public static final String IDTIPOCUENTAVALUE = "idTipoCuenta";
}