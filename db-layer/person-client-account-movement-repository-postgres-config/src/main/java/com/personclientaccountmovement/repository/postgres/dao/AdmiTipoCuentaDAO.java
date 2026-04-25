package com.personclientaccountmovement.repository.postgres.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personclientaccountmovement.repository.postgres.entity.AdmiTipoCuenta;



/**
* Repositorio ADMI_TIPO_CUENTA
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Repository
public interface AdmiTipoCuentaDAO extends JpaRepository<AdmiTipoCuenta, Long> {

	Optional<AdmiTipoCuenta> findByNombreCuentaIgnoreCaseAndEstado(String nombreCuenta, String estado);
}
