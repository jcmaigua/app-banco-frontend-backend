package com.personclientaccountmovement.repository.postgres.dao;

import java.util.List;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import org.antlr.v4.runtime.atn.SemanticContext.AND;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.personclientaccountmovement.repository.postgres.entity.InfoMovimiento;

/**
* Repositorio INFO_MOVIMIENTO
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Repository
public interface InfoMovimientoDAO extends JpaRepository<InfoMovimiento, Long>, JpaSpecificationExecutor<InfoMovimiento>  {
    List<InfoMovimiento> findByCuentaIdAndEstado (Long idCuenta, String estado);
    List<InfoMovimiento> findByIdMovimientoAndEstado (Long idMovimiento, String estado);
    List<InfoMovimiento> findByIdMovimientoAndCuentaIdAndEstado (Long idMovimiento, Long idCuenta, String estado);

    @Query(" SELECT r FROM InfoMovimiento r " +
        " WHERE r.cuentaId = :idCuenta AND r.estado = 'ACTIVO' " +
        " ORDER BY r.idMovimiento DESC " +
        " LIMIT 1 ")
    List<InfoMovimiento> findLastMovimientoByCuentaId(@Param("idCuenta") Long idCuenta);

    @Query("SELECT SUM(r.valor) FROM InfoMovimiento r " +
        "JOIN AdmiTipoMovimiento t ON r.tipoMovimientoId = t.idTipoMovimiento " +
        "WHERE t.nombreMovimiento = 'Retiro' AND r.estado = 'ACTIVO' " +
        "AND r.fechaCreacion >= :inicioDia " +
        "AND r.fechaCreacion < :finDia " +
        "AND r.cuentaId = :idCuenta")
    BigDecimal findCantidadRetiroByCuentaId(
        @Param("idCuenta") Long idCuenta,
        @Param("inicioDia") LocalDateTime inicioDia,
        @Param("finDia") LocalDateTime finDia
    );

    @Query("SELECT r FROM InfoMovimiento r " +
       "JOIN InfoCuenta c ON c.idCuenta = r.cuentaId " +
       "JOIN InfoPersona p ON p.idPersona = c.personaId " +
       "WHERE p.identificacion LIKE CONCAT('%', :identificacion, '%') " +
       "AND r.fechaCreacion BETWEEN :fechaDesde AND :fechaHasta " +
       "AND c.estado = 'ACTIVO' AND r.estado = 'ACTIVO'" +
       "ORDER BY c.numeroCuenta, r.fechaCreacion DESC")
    List<InfoMovimiento> listByInfoMovimientoReporte(
            @Param("identificacion") String identificacion,
            @Param("fechaDesde") Date fechaDesde,
            @Param("fechaHasta") Date fechaHasta);
}
