package com.personclientaccountmovement.repository.postgres.entity;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import jakarta.persistence.*;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.personclientaccountmovement.repository.postgres.utils.MsClientesCuentasConstants;

/**
* Entidad ADMI_TIPO_MOVIMIENTO
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Data
@Entity
@Table(name = "ADMI_TIPO_MOVIMIENTO", schema = MsClientesCuentasConstants.SCHEMA_CLIENTES_CUENTAS)
public class AdmiTipoMovimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tipo_movimiento")
    @SequenceGenerator(sequenceName = MsClientesCuentasConstants.SCHEMA_CLIENTES_CUENTAS +
            ".seq_tipo_movimiento", allocationSize = 1, initialValue = 1, name = "seq_tipo_movimiento")
    @Column(name = "id_tipo_movimiento")
    private Long idTipoMovimiento;

    @Column(name = "nombre_movimiento", unique = true)
    private String nombreMovimiento;

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

    public static final String IDTIPOMOVIMIENTOVALUE = "idTipoMovimiento";
}