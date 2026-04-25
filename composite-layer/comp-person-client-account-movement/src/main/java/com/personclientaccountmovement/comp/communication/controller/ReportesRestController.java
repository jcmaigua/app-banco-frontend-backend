package com.personclientaccountmovement.comp.communication.controller;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.personclientaccountmovement.comp.cons.CompPersonClientAccountMovementConstants;
import com.personclientaccountmovement.comp.communication.support.GenericResponseUnwrapUtil;
import com.personclientaccountmovement.cons.PersonClientAccountMovementConfigConstants;
import com.personclientaccountmovement.dto.InfoMovimientoReporteReqDTO;
import com.personclientaccountmovement.dto.PageDTO;
import com.personclientaccountmovement.exception.GenericException;
import com.personclientaccountmovement.integration.dto.response.GenericResponseDTO;
import com.personclientaccountmovement.integration.enums.EnumRequestType;
import com.personclientaccountmovement.integration.interfaces.IGenericProducer;
import com.personclientaccountmovement.integration.model.GenericProducerModel;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
* Controlador REST de reportes de movimientos.
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@CrossOrigin
@Tag(name = "ReportesRestController", description = "Reportes estado de cuenta")
@RestController
@RequestMapping("reportes")
public class ReportesRestController {

	Logger log = LogManager.getLogger(this.getClass());

	@Autowired
	private IGenericProducer iGenericProducer;

	@Autowired
	private BeanFactory beanFactory;

	/**
	 * Expone el endpoint REST listByInfoCuenta.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@GetMapping
	public CompletableFuture<GenericResponseDTO<Object>> listByInfoCuenta(
			@RequestParam(required = false) String fechaDesde,
			@RequestParam(required = false) String fechaHasta,
			@RequestParam(required = false) String identificacion) {
				log.info("Metodo controller RequestParam iniciado");
		return CompletableFuture.supplyAsync(() -> {
			try {
				InfoMovimientoReporteReqDTO payload = InfoMovimientoReporteReqDTO.builder()
						.fechaDesde(fechaDesde)
						.fechaHasta(fechaHasta)
						.identificacion(identificacion)
						.build();
				IGenericProducer producer = beanFactory.getBean(
						CompPersonClientAccountMovementConstants.PERSON_CLIENT_ACCOUNT_MOVEMENT_HANDLER,
						IGenericProducer.class);
				GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
						.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_LIST_BY_INFO_MOVIMIENTO_REPORTE_METHOD)
						.payload(payload)
						.type(EnumRequestType.SYNC)
						.transactionId(UUID.randomUUID().toString())
						.build();
				return GenericResponseDTO
						.success(GenericResponseUnwrapUtil.unwrap(producer.process(producerModel)));
			} catch (GenericException e) {
				return GenericResponseDTO.of(
						PersonClientAccountMovementConfigConstants.STATUS_ERROR,
						e.getCodeError() != null ? e.getCodeError()
								: PersonClientAccountMovementConfigConstants.CODE_ERROR_GENERIC,
						e.getMessageError() != null ? e.getMessageError() : e.getMessage(),
						null);
			}
		});
	}

	/**
	 * Expone el endpoint REST listByInfoCuenta.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@GetMapping("/page")
	public CompletableFuture<GenericResponseDTO<Object>> pagelistByInfoCuenta(
			@RequestParam(required = false) String fechaDesde,
			@RequestParam(required = false) String fechaHasta,
			@RequestParam(required = false) String identificacion,
			@RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer size) {
				log.info("Metodo controller RequestParam iniciado");
		return CompletableFuture.supplyAsync(() -> {
			try {

				InfoMovimientoReporteReqDTO payload = InfoMovimientoReporteReqDTO.builder()
						.fechaDesde(fechaDesde)
						.fechaHasta(fechaHasta)
						.identificacion(identificacion)
						.build();
				PageDTO pageDTO = new PageDTO();
				pageDTO.setPage(page);
				pageDTO.setSize(size);
				pageDTO.setTabla(payload);

				IGenericProducer producer = beanFactory.getBean(
						CompPersonClientAccountMovementConstants.PERSON_CLIENT_ACCOUNT_MOVEMENT_HANDLER,
						IGenericProducer.class);
				GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
						.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_PAGE_LIST_BY_INFO_MOVIMIENTO_REPORTE_METHOD)
						.payload(pageDTO)
						.type(EnumRequestType.SYNC)
						.transactionId(UUID.randomUUID().toString())
						.build();
				return GenericResponseDTO
						.success(GenericResponseUnwrapUtil.unwrap(producer.process(producerModel)));
			} catch (GenericException e) {
				return GenericResponseDTO.of(
						PersonClientAccountMovementConfigConstants.STATUS_ERROR,
						e.getCodeError() != null ? e.getCodeError()
								: PersonClientAccountMovementConfigConstants.CODE_ERROR_GENERIC,
						e.getMessageError() != null ? e.getMessageError() : e.getMessage(),
						null);
			}
		});
	}

	/**
	 * Expone el endpoint REST listByInfoCuenta.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@GetMapping("/pagePdf")
	public CompletableFuture<GenericResponseDTO<Object>> pageListByInfoMovimientoReportePdf(
			@RequestParam(required = false) String fechaDesde,
			@RequestParam(required = false) String fechaHasta,
			@RequestParam(required = false) String identificacion,
			@RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer size) {
				log.info("Metodo controller RequestParam iniciado");
		return CompletableFuture.supplyAsync(() -> {
			try {

				InfoMovimientoReporteReqDTO payload = InfoMovimientoReporteReqDTO.builder()
						.fechaDesde(fechaDesde)
						.fechaHasta(fechaHasta)
						.identificacion(identificacion)
						.build();
				PageDTO pageDTO = new PageDTO();
				pageDTO.setPage(page);
				pageDTO.setSize(size);
				pageDTO.setTabla(payload);

				IGenericProducer producer = beanFactory.getBean(
						CompPersonClientAccountMovementConstants.PERSON_CLIENT_ACCOUNT_MOVEMENT_HANDLER,
						IGenericProducer.class);
				GenericProducerModel<Object> producerModel = GenericProducerModel.<Object>builder()
						.option(PersonClientAccountMovementConfigConstants.CORE_CONFIG_MGMT_PAGE_LIST_BY_INFO_MOVIMIENTO_REPORTE_PDF_METHOD)
						.payload(pageDTO)
						.type(EnumRequestType.SYNC)
						.transactionId(UUID.randomUUID().toString())
						.build();
				return GenericResponseDTO
						.success(GenericResponseUnwrapUtil.unwrap(producer.process(producerModel)));
			} catch (GenericException e) {
				return GenericResponseDTO.of(
						PersonClientAccountMovementConfigConstants.STATUS_ERROR,
						e.getCodeError() != null ? e.getCodeError()
								: PersonClientAccountMovementConfigConstants.CODE_ERROR_GENERIC,
						e.getMessageError() != null ? e.getMessageError() : e.getMessage(),
						null);
			}
		});
	}
}
