package com.personclientaccountmovement.repository.postgres.entity;

import java.util.Date;
import java.math.BigDecimal;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import jakarta.persistence.*;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.personclientaccountmovement.repository.postgres.utils.MsClientesCuentasConstants;


/**
* Entidad INFO_CUENTA
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Data
@Entity
@Table(name = "INFO_CUENTA", schema = MsClientesCuentasConstants.SCHEMA_CLIENTES_CUENTAS)
public class InfoCuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cuenta")
    @SequenceGenerator(sequenceName = MsClientesCuentasConstants.SCHEMA_CLIENTES_CUENTAS +
            ".seq_cuenta", allocationSize = 1, initialValue = 1, name = "seq_cuenta")
    @Column(name = "id_cuenta")
    private Long idCuenta;

    @Column(name = "numero_cuenta", unique = true)
    private String numeroCuenta;

    @Column(name = "tipo_cuenta_id")
    private Long tipoCuentaId;

    @Column(name = "persona_id")
    private Long personaId;

    @Column(name = "saldo_inicial", precision = 15, scale = 2)
    private BigDecimal saldoInicial;

    @Column(name = "saldo_actual", precision = 15, scale = 2)
    private BigDecimal saldoActual;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tipo_cuenta_id", insertable = false, updatable = false)
    private AdmiTipoCuenta admiTipoCuenta;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "persona_id", insertable = false, updatable = false)
    private InfoPersona infoPersona;

    public static final String IDCUENTAVALUE = "idCuenta";
}