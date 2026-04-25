package com.personclientaccountmovement.comp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.DispatcherServlet;

/**
* Aplicación Spring Boot de la capa compuesta.
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan({ "com.personclientaccountmovement" })
public class PersonClientAccountMovementAppApplication {

	@Value("${path.webservice}")
	private String pathWebService;

	/**
	 * Punto de entrada de la aplicación.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	public static void main(String[] args) {
		SpringApplication.run(PersonClientAccountMovementAppApplication.class, args);
	}

	/**
	 * Registra el dispatcher servlet de la API REST bajo el prefijo {@code path.webservice}.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Bean(name = DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_REGISTRATION_BEAN_NAME)
	public DispatcherServletRegistrationBean dispatcherServletRegistration(DispatcherServlet dispatcherServlet) {
		String apiBase = "/" + pathWebService + "/*";
		DispatcherServletRegistrationBean registration = new DispatcherServletRegistrationBean(dispatcherServlet,
				apiBase);
		registration.setAsyncSupported(true);
		registration.setLoadOnStartup(1);
		registration.setName(DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_BEAN_NAME);
		return registration;
	}

}
