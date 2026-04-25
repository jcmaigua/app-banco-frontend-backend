package com.personclientaccountmovement.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.DispatcherServlet;

import com.personclientaccountmovement.cons.PersonClientAccountMovementConfigConstants;
import com.personclientaccountmovement.core.config.GrpcServerConfiguration;
import com.personclientaccountmovement.core.handler.impl.ClienteHandlerImpl;
import com.personclientaccountmovement.core.handler.impl.CuentaHandlerImpl;
import com.personclientaccountmovement.core.handler.impl.MovimientoHandlerImpl;
import com.personclientaccountmovement.core.handler.impl.PersonaHandlerImpl;
import com.personclientaccountmovement.core.handler.impl.TipoCuentaHandlerImpl;
import com.personclientaccountmovement.core.handler.impl.TipoMovimientoHandlerImpl;
import com.personclientaccountmovement.integration.dispatcher.AnythingDispatcher;
import com.personclientaccountmovement.integration.dto.request.GenericRequestDTO;

/**
* Aplicación Spring Boot del núcleo PersonClient.
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@ComponentScan({"com.personclientaccountmovement" })
@EnableScheduling
public class PersonClientAppApplication implements ApplicationRunner {

	@Value("${path.webservice}")
	private String pathWebService;

	@Autowired
	private AnythingDispatcher dispatcher;

	@Autowired
	private GrpcServerConfiguration grpcServerConfiguration;

	@Autowired
	private PersonaHandlerImpl personaHandlerImpl;

	@Autowired
	private ClienteHandlerImpl clienteHandlerImpl;

	@Autowired
	private TipoMovimientoHandlerImpl tipoMovimientoHandlerImpl;

	@Autowired
	private TipoCuentaHandlerImpl tipoCuentaHandlerImpl;

	@Autowired
	private MovimientoHandlerImpl movimientoHandlerImpl;

	@Autowired
	private CuentaHandlerImpl cuentaHandlerImpl;

	/**
	 * Punto de entrada de la aplicación.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	public static void main(String[] args) {
		SpringApplication.run(PersonClientAppApplication.class, args)
				.getBean(PersonClientAppApplication.class)
				.runGrpcServer();
	}

	/**
	 * Ejecuta la lógica de arranque definida para la aplicación.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public void run(ApplicationArguments args) throws Exception {
		registerHandlers();
	}

	/**
	 * Registra en el despachador los manejadores de persona y cliente de este núcleo.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	private void registerHandlers() {

		// InfoPersona
		dispatcher.registerSimpleResultHandler(GenericRequestDTO.class,
				personaHandlerImpl::saveInfoPersona,
				PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_SAVE_INFO_PERSONA_METHOD);
		dispatcher.registerSimpleResultHandler(GenericRequestDTO.class,
				personaHandlerImpl::updateInfoPersona,
				PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_UPDATE_INFO_PERSONA_METHOD);
		dispatcher.registerSimpleResultHandler(GenericRequestDTO.class,
				personaHandlerImpl::deleteInfoPersona,
				PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_ELIMINADO_INFO_PERSONA_METHOD);
		dispatcher.registerSimpleResultHandler(GenericRequestDTO.class,
				personaHandlerImpl::listByInfoPersona,
				PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_LIST_BY_INFO_PERSONA_METHOD);
		dispatcher.registerSimpleResultHandler(GenericRequestDTO.class,
				personaHandlerImpl::pageListByInfoPersona,
				PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_PAGE_LIST_BY_INFO_PERSONA_METHOD);
		dispatcher.registerSimpleResultHandler(GenericRequestDTO.class,
				personaHandlerImpl::retrieveInfoPersona,
				PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_RETRIEVE_INFO_PERSONA_METHOD);

		// InfoCliente (esquema DB_CLIENTES_CUENTAS)
		dispatcher.registerSimpleResultHandler(GenericRequestDTO.class,
				clienteHandlerImpl::saveInfoCliente,
				PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_SAVE_INFO_CLIENTE_METHOD);
		dispatcher.registerSimpleResultHandler(GenericRequestDTO.class,
				clienteHandlerImpl::updateInfoCliente,
				PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_UPDATE_INFO_CLIENTE_METHOD);
		dispatcher.registerSimpleResultHandler(GenericRequestDTO.class,
				clienteHandlerImpl::deleteInfoCliente,
				PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_ELIMINADO_INFO_CLIENTE_METHOD);
		dispatcher.registerSimpleResultHandler(GenericRequestDTO.class,
				clienteHandlerImpl::listByInfoCliente,
				PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_LIST_BY_INFO_CLIENTE_METHOD);
		dispatcher.registerSimpleResultHandler(GenericRequestDTO.class,
				clienteHandlerImpl::pageListByInfoCliente,
				PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_PAGE_LIST_BY_INFO_CLIENTE_METHOD);
		dispatcher.registerSimpleResultHandler(GenericRequestDTO.class,
				clienteHandlerImpl::retrieveInfoCliente,
				PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_RETRIEVE_INFO_CLIENTE_METHOD);
		
		// AdmiTipoMovimiento
		dispatcher.registerSimpleResultHandler(GenericRequestDTO.class,
				tipoMovimientoHandlerImpl::saveAdmiTipoMovimiento,
				PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_SAVE_ADMI_TIPO_MOVIMIENTO_METHOD);
		dispatcher.registerSimpleResultHandler(GenericRequestDTO.class,
				tipoMovimientoHandlerImpl::updateAdmiTipoMovimiento,
				PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_UPDATE_ADMI_TIPO_MOVIMIENTO_METHOD);
		dispatcher.registerSimpleResultHandler(GenericRequestDTO.class,
				tipoMovimientoHandlerImpl::deleteAdmiTipoMovimiento,
				PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_ELIMINADO_ADMI_TIPO_MOVIMIENTO_METHOD);
		dispatcher.registerSimpleResultHandler(GenericRequestDTO.class,
				tipoMovimientoHandlerImpl::listByAdmiTipoMovimiento,
				PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_LIST_BY_ADMI_TIPO_MOVIMIENTO_METHOD);
		dispatcher.registerSimpleResultHandler(GenericRequestDTO.class,
				tipoMovimientoHandlerImpl::pageListByAdmiTipoMovimiento,
				PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_PAGE_LIST_BY_ADMI_TIPO_MOVIMIENTO_METHOD);

		// AdmiTipoCuenta
		dispatcher.registerSimpleResultHandler(GenericRequestDTO.class,
				tipoCuentaHandlerImpl::saveAdmiTipoCuenta,
				PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_SAVE_ADMI_TIPO_CUENTA_METHOD);
		dispatcher.registerSimpleResultHandler(GenericRequestDTO.class,
				tipoCuentaHandlerImpl::updateAdmiTipoCuenta,
				PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_UPDATE_ADMI_TIPO_CUENTA_METHOD);
		dispatcher.registerSimpleResultHandler(GenericRequestDTO.class,
				tipoCuentaHandlerImpl::deleteAdmiTipoCuenta,
				PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_ELIMINADO_ADMI_TIPO_CUENTA_METHOD);
		dispatcher.registerSimpleResultHandler(GenericRequestDTO.class,
				tipoCuentaHandlerImpl::listByAdmiTipoCuenta,
				PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_LIST_BY_ADMI_TIPO_CUENTA_METHOD);
		dispatcher.registerSimpleResultHandler(GenericRequestDTO.class,
				tipoCuentaHandlerImpl::pageListByAdmiTipoCuenta,
				PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_PAGE_LIST_BY_ADMI_TIPO_CUENTA_METHOD);
		

		// InfoMovimiento
		dispatcher.registerSimpleResultHandler(GenericRequestDTO.class,
				movimientoHandlerImpl::saveInfoMovimiento,
				PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_SAVE_INFO_MOVIMIENTO_METHOD);
		dispatcher.registerSimpleResultHandler(GenericRequestDTO.class,
				movimientoHandlerImpl::updateInfoMovimiento,
				PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_UPDATE_INFO_MOVIMIENTO_METHOD);
		dispatcher.registerSimpleResultHandler(GenericRequestDTO.class,
				movimientoHandlerImpl::deleteInfoMovimiento,
				PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_ELIMINADO_INFO_MOVIMIENTO_METHOD);
		dispatcher.registerSimpleResultHandler(GenericRequestDTO.class,
				movimientoHandlerImpl::listByInfoMovimiento,
				PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_LIST_BY_INFO_MOVIMIENTO_METHOD);
		dispatcher.registerSimpleResultHandler(GenericRequestDTO.class,
				movimientoHandlerImpl::pageListByInfoMovimiento,
				PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_PAGE_LIST_BY_INFO_MOVIMIENTO_METHOD);
		dispatcher.registerSimpleResultHandler(GenericRequestDTO.class,
				movimientoHandlerImpl::listByInfoMovimientoReporte,
				PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_LIST_BY_INFO_MOVIMIENTO_REPORTE_METHOD);
		dispatcher.registerSimpleResultHandler(GenericRequestDTO.class,
				movimientoHandlerImpl::pageListByInfoMovimientoReporte,
				PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_PAGE_LIST_BY_INFO_MOVIMIENTO_REPORTE_METHOD);
		dispatcher.registerSimpleResultHandler(GenericRequestDTO.class,
				movimientoHandlerImpl::pageListByInfoMovimientoReportePdf,
				PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_PAGE_LIST_BY_INFO_MOVIMIENTO_REPORTE_PDF_METHOD);

		// InfoCuenta
		dispatcher.registerSimpleResultHandler(GenericRequestDTO.class,
				cuentaHandlerImpl::saveInfoCuenta,
				PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_SAVE_INFO_CUENTA_METHOD);
		dispatcher.registerSimpleResultHandler(GenericRequestDTO.class,
				cuentaHandlerImpl::updateInfoCuenta,
				PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_UPDATE_INFO_CUENTA_METHOD);
		dispatcher.registerSimpleResultHandler(GenericRequestDTO.class,
				cuentaHandlerImpl::deleteInfoCuenta,
				PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_ELIMINADO_INFO_CUENTA_METHOD);
		dispatcher.registerSimpleResultHandler(GenericRequestDTO.class,
				cuentaHandlerImpl::listByInfoCuenta,
				PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_LIST_BY_INFO_CUENTA_METHOD);
		dispatcher.registerSimpleResultHandler(GenericRequestDTO.class,
				cuentaHandlerImpl::pageListByInfoCuenta,
				PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_PAGE_LIST_BY_INFO_CUENTA_METHOD);
		dispatcher.registerSimpleResultHandler(GenericRequestDTO.class,
				cuentaHandlerImpl::validarInfoCuentaByPersona,
				PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_VALIDAR_INFO_CUENTA_BY_USUARIO_METHOD);
	}

	/**
	 * Registra el servlet dispatcher con la ruta base configurada en {@code path.webservice}.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Bean
	public ServletRegistrationBean<DispatcherServlet> dispatcherRegistration(DispatcherServlet dispatcherServlet) {
		ServletRegistrationBean<DispatcherServlet> bean = new ServletRegistrationBean<>(dispatcherServlet,
				"/" + pathWebService + "/*");
		bean.setAsyncSupported(true);
		bean.setName(pathWebService);
		bean.setLoadOnStartup(1);
		return bean;
	}

	/**
	 * Inicia el servidor gRPC y bloquea el hilo actual hasta el apagado.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	public void runGrpcServer() {
		grpcServerConfiguration.start();
		grpcServerConfiguration.blockUntilShutdown();
	}
}
