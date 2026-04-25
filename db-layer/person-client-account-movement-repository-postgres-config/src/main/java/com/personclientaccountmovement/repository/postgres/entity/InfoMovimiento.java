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
* Entidad INFO_MOVIMIENTO
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Data
@Entity
@Table(name = "INFO_MOVIMIENTO", schema = MsClientesCuentasConstants.SCHEMA_CLIENTES_CUENTAS)
public class InfoMovimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_movimiento")
    @SequenceGenerator(sequenceName = MsClientesCuentasConstants.SCHEMA_CLIENTES_CUENTAS +
            ".seq_movimiento", allocationSize = 1, initialValue = 1, name = "seq_movimiento")
    @Column(name = "id_movimiento")
    private Long idMovimiento;

    @Column(name = "tipo_movimiento_id")
    private Long tipoMovimientoId;

    @Column(name = "cuenta_id")
    private Long cuentaId;

    @Column(name = "valor", precision = 15, scale = 2)
    private BigDecimal valor;

    @Column(name = "saldo", precision = 15, scale = 2)
    private BigDecimal saldo;

    @Column(name = "estado")
    private String estado;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = MsClientesCuentasConstants.TIMEZONE_DATE)
    @Column(name = "fecha_creacion")
    private Date fechaCreacion;

    @Column(name = "persona_creacion")
    private String personaCreacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tipo_movimiento_id", insertable = false, updatable = false)
    private AdmiTipoMovimiento admiTipoMovimiento;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cuenta_id", insertable = false, updatable = false)
    private InfoCuenta infoCuenta;

    public static final String IDMOVIMIENTOVALUE = "idMovimiento";
}