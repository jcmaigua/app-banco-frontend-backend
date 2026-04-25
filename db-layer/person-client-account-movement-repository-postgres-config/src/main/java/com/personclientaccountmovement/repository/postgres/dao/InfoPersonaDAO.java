package com.personclientaccountmovement.repository.postgres.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personclientaccountmovement.repository.postgres.entity.InfoPersona;

/**
* Repositorio INFO_PERSONA
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Repository
public interface InfoPersonaDAO extends JpaRepository<InfoPersona, Long> {
    List<InfoPersona> findByIdPersonaAndEstado (Long idPersona, String estado);
    List<InfoPersona> findByIdentificacionAndEstado(String identificacion, String estado);
    List<InfoPersona> findByNombreAndApellidoAndEstado(String nombre, String apellido,String estado);
}
