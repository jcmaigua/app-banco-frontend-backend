package com.personclientaccountmovement.repository.postgres.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "resourceConfigEMFactory", transactionManagerRef = "resourceConfigTM", basePackages = {
		"com.personclientaccountmovement.repository.postgres.dao" })
		/**
		 * Configuración de recursos y datasource PostgreSQL.
		 * 
		 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
		 * @version 1.0 - Version Inicial
		 * @since 15/04/2026
		 */
@EntityScan(basePackages = { "com.personclientaccountmovement.repository.postgres.entity" })
public class DsResourcePostgresConfig {

}
