package com.personclientaccountmovement.repository.postgres.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.personclientaccountmovement.repository.postgres.entity.InfoCliente;

/**
* Repositorio Spring Data para InfoCliente.
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Repository
public interface InfoClienteDAO extends JpaRepository<InfoCliente, Long>, JpaSpecificationExecutor<InfoCliente>{
    List<InfoCliente> findByPersonaIdAndEstado (Long personaId, String estado);
    List<InfoCliente> findByIdClienteAndEstado (Long idCliente, String estado);
    
}
