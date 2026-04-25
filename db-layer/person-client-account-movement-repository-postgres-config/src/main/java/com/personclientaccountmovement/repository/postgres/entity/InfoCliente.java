package com.personclientaccountmovement.repository.postgres.entity;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.personclientaccountmovement.repository.postgres.utils.MsClientesCuentasConstants;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Entidad INFO_CLIENTE
 *
 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
 * @version 1.0 - Version Inicial
 * @since 15/04/2026
 */
@Data
@Entity
@Table(name = "INFO_CLIENTE", schema = MsClientesCuentasConstants.SCHEMA_CLIENTES_CUENTAS)
public class InfoCliente {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cliente")
	@SequenceGenerator(sequenceName = MsClientesCuentasConstants.SCHEMA_CLIENTES_CUENTAS + ".seq_cliente", allocationSize = 1,
			initialValue = 1, name = "seq_cliente")
	@Column(name = "id_cliente")
	private Long idCliente;

	@Column(name = "persona_id")
	private Long personaId;

	@Column(name = "contrasena")
	private String contrasena;

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
	@JoinColumn(name = "persona_id", insertable = false, updatable = false)
	private InfoPersona infoPersona;

	public static final String IDCLIENTEVALUE = "idCliente";
}
