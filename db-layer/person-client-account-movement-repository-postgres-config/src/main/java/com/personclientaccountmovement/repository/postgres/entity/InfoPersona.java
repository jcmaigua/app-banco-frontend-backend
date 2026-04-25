package com.personclientaccountmovement.repository.postgres.entity;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import jakarta.persistence.*;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.personclientaccountmovement.repository.postgres.utils.MsClientesCuentasConstants;

/**
* Entidad INFO_PERSONA
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Data
@Entity
@Table(name = "INFO_PERSONA", schema = MsClientesCuentasConstants.SCHEMA_CLIENTES_CUENTAS)
public class InfoPersona {
 
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_persona")
    @SequenceGenerator(sequenceName = MsClientesCuentasConstants.SCHEMA_CLIENTES_CUENTAS +
            ".seq_persona", allocationSize = 1, initialValue = 1, name = "seq_persona")
    @Column(name = "id_persona")
    private Long idPersona;
 
    @Column(name = "nombre")
    private String nombre;
 
    @Column(name = "apellido")
    private String apellido;
 
    @Column(name = "genero")
    private String genero;
 
    @Column(name = "edad")
    private Integer edad;
 
    @Column(name = "identificacion", unique = true, length = 10)
    private String identificacion;
 
    @Column(name = "direccion")
    private String direccion;
 
    @Column(name = "telefono")
    private String telefono;
 
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
 
    public static final String IDUSUARIOVALUE = "idPersona";
}