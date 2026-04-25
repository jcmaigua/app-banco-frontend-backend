package com.personclientaccountmovement.core.datasource;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.sql.DataSource;

import jakarta.persistence.EntityManagerFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
* Configuración de acceso a PostgreSQL.
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Configuration
@EnableTransactionManagement
public class DbPostgresConfig { 
    
    private static final  Logger LOG = LogManager.getLogger(DbPostgresConfig.class);

    @Value("${resource-config.datasource.jdbc-url}")
	private String dbResourceConfigUrl;

	@Value("${resource-config.datasource.username}")
	private String dbResourceConfigUser;

	@Value("${resource-config.datasource.password}")
	private String dbResourceConfigPass;

	@Value("${resource-config.datasource.driver}")
	private String dbResourceConfigDriver;


	@Value("${spring.datasource.hikari.idle-timeout}")
    private int idleTimeout;

    @Value("${spring.datasource.hikari.connection-timeout}")
    private int connectionTimeout;

    @Value("${spring.datasource.hikari.maximum-pool-size}")
    private int maxPoolSize;

    @Value("${spring.datasource.hikari.minimum-idle}")
    private int minPoolSize;

    @Value("${spring.datasource.hikari.max-lifetime}")
    private int maxLifetime;
    
	private Map<String, Object> hibernateProperties() {
        Map<String, Object> hibernateProperties = new LinkedHashMap<>();
        hibernateProperties.put("hibernate.connection.release_mode", "auto");
        return hibernateProperties;
    }

    // INICIO CONFIGURACIÓN 
    @Primary
    @Bean(name = "dsResourceConfig")
    public DataSource dsNotification(@Qualifier("dsResourceConfigProperties") HikariConfig dataSourceConfig) {
        return new HikariDataSource(dataSourceConfig);
    }

    @Primary
    @Bean(name = "dsResourceConfigProperties")
    @ConfigurationProperties("resource-config.datasource")
    public HikariConfig dsResourceConfig() throws Exception {
    	HikariConfig dataSourceConfig = new HikariConfig();
        try {
            ArrayList<String> listaConexiones = new ArrayList<>();
			listaConexiones.add("ResourceManager");

            dataSourceConfig.setDriverClassName(dbResourceConfigDriver);
			dataSourceConfig.setJdbcUrl(dbResourceConfigUrl);
			dataSourceConfig.setUsername(dbResourceConfigUser);
			dataSourceConfig.setPassword(dbResourceConfigPass);
            dataSourceConfig.setPoolName("dsResourceConfig");
            dataSourceConfig.setConnectionTimeout(connectionTimeout);
            dataSourceConfig.setIdleTimeout(idleTimeout);
            dataSourceConfig.setMaximumPoolSize(maxPoolSize);
            dataSourceConfig.setMinimumIdle(minPoolSize);
            dataSourceConfig.setMaxLifetime(maxLifetime);
            dataSourceConfig.setValidationTimeout(10000);
            dataSourceConfig.setConnectionTestQuery("SELECT 1");
			dataSourceConfig.setConnectionInitSql("SELECT 1");
        }catch (Exception ex) {
			LOG.error(ex.getMessage());
		}

		return dataSourceConfig;

    }

    @Primary
    @Bean(name = "jdbcResourceConfig")
    public JdbcTemplate jdbcResourceConfigTemplate(@Qualifier("dsResourceConfig") DataSource dsResourceConfig) {
        return new JdbcTemplate(dsResourceConfig);
    }

    @Primary
    @Bean(name = "resourceConfigEMFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryResourceConfig(EntityManagerFactoryBuilder builder,
                                                                                @Qualifier("dsResourceConfig") DataSource dsResourceConfig) {
        return builder.dataSource(dsResourceConfig).properties(hibernateProperties())
                .packages("com.personclientaccountmovement.repository.postgres.entity")
                .persistenceUnit("dsResourceConfig").build();
    }

    @Primary
    @Bean(name = "resourceConfigTM")
    public PlatformTransactionManager transactionManagerResourceConfig(
            @Qualifier("resourceConfigEMFactory") EntityManagerFactory resourceConfigEMFactory) {
        return new JpaTransactionManager(resourceConfigEMFactory);
    }

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
	    return new PersistenceExceptionTranslationPostProcessor();
	}
    // FIN CONFIGURACIÓN 
}