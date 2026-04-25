package com.personclientaccountmovement.repository.postgres.dao;

import java.awt.Label;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personclientaccountmovement.repository.postgres.entity.AdmiTipoMovimiento;

import java.util.List;

/**
* Repositorio ADMI_TIPO_MOVIMIENTO
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Repository
public interface AdmiTipoMovimientoDAO extends JpaRepository<AdmiTipoMovimiento, Long> {
    List<AdmiTipoMovimiento> findByNombreMovimientoAndEstado (String nombreMovimiento, String estado);
    List<AdmiTipoMovimiento> findByIdTipoMovimientoAndEstado (Long idTipoMovimiento, String estado);
}
