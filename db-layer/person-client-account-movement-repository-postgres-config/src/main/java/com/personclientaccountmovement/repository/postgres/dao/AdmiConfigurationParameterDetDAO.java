package com.personclientaccountmovement.repository.postgres.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personclientaccountmovement.repository.postgres.entity.AdmiConfigurationParameterDet;


/**
 * Repositorio ADMI_CONFIGURATION_PARAMETER_DET
 *
 * @author Juan Maigua <mailto:jmaigua@telconet.ec>
 * @version 1.0
 * @since 23/04/2026
 */
@Repository
public interface AdmiConfigurationParameterDetDAO extends JpaRepository<AdmiConfigurationParameterDet, Long> {
        List<AdmiConfigurationParameterDet> findByValue(String value);
}
