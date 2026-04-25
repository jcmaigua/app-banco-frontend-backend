package com.personclientaccountmovement.repository.postgres.handler.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import com.personclientaccountmovement.dto.InfoMovimientoReqDTO;
import com.personclientaccountmovement.dto.InfoMovimientoCuentaResDTO;
import com.personclientaccountmovement.dto.ObjectIdDTO;
import com.personclientaccountmovement.dto.PageDTO;
import com.personclientaccountmovement.dto.ReportePdfDTO;
import com.personclientaccountmovement.enums.EnumStatus;
import com.personclientaccountmovement.exception.GenericException;
import com.personclientaccountmovement.support.ExceptionLogSupport;
import com.personclientaccountmovement.repository.postgres.dao.InfoMovimientoDAO;
import com.personclientaccountmovement.repository.postgres.entity.InfoMovimiento;
import com.personclientaccountmovement.repository.postgres.entity.InfoPersona;
import com.personclientaccountmovement.repository.postgres.handler.IInfoMovimientoHandler;
import com.personclientaccountmovement.repository.postgres.utils.InfoMovimientoConverter;
import com.personclientaccountmovement.repository.postgres.utils.InfoMovimientoValidators;
import com.personclientaccountmovement.repository.postgres.utils.ResourceValidators;
import com.personclientaccountmovement.repository.postgres.handler.IAdmiConfigurationParameterDetHandler;
import com.personclientaccountmovement.repository.postgres.handler.IInfoCuentaHandler;
import com.personclientaccountmovement.dto.AdmiConfigurationParameterCabReqDTO;
import com.personclientaccountmovement.dto.AdmiConfigurationParameterDetResDTO;
import com.personclientaccountmovement.dto.BusquedaGlobalDTO;
import com.personclientaccountmovement.dto.InfoCuentaReqDTO;
import com.personclientaccountmovement.dto.InfoCuentaResDTO;
import com.personclientaccountmovement.dto.InfoMovimientoPageResDTO;
import com.personclientaccountmovement.repository.postgres.entity.InfoCuenta;
import com.personclientaccountmovement.repository.postgres.utils.AdmiTipoMovimientoValidators;
import com.personclientaccountmovement.repository.postgres.entity.AdmiTipoCuenta;
import com.personclientaccountmovement.repository.postgres.entity.AdmiTipoMovimiento;
import com.personclientaccountmovement.dto.InfoMovimientoReporteResDTO;
import com.personclientaccountmovement.dto.InfoMovimientoReporteReqDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;



/**
* Persistencia y reglas de negocio de movimientos de cuenta en PostgreSQL.
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial 
* @since 15/04/2026
*/
@Service
@Transactional(rollbackFor = { GenericException.class })
public class InfoMovimientoHandlerImpl implements IInfoMovimientoHandler {

	Logger log = LogManager.getLogger(this.getClass());

	@Autowired
	private ResourceValidators resourceValidators;

	@Autowired
	private InfoMovimientoValidators infoMovimientoValidators;

	@Autowired
	private InfoMovimientoDAO infoMovimientoDAO;

	@Autowired
	private InfoMovimientoConverter infoMovimientoConverter;

	@Autowired
	private IInfoCuentaHandler infoCuentaHandler;

	@Autowired
	private IAdmiConfigurationParameterDetHandler iAdmiConfigurationParameterDetHandler;

	@Autowired
	private AdmiTipoMovimientoValidators admiTipoMovimientoValidators;

	@Autowired
	private EntityManager entityManager;


	/**
	 * Registra un movimiento, actualiza el saldo de la cuenta y devuelve el DTO enriquecido.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial 
	 * @since 15/04/2026
	 */
	@Override
	public InfoMovimientoCuentaResDTO saveInfoMovimiento(InfoMovimientoReqDTO requestDTO) throws GenericException {
		log.info("Metodo repository saveInfoMovimiento iniciado");
		InfoMovimientoCuentaResDTO response = new InfoMovimientoCuentaResDTO();
		InfoMovimiento responseEntity = new InfoMovimiento();
		try {
			requestDTO.setFechaCreacion(new Date());
			BigDecimal saldoActual = BigDecimal.ZERO;
			AdmiTipoMovimiento admiTipoMovimiento = admiTipoMovimientoValidators.validarTipoMovimientoById(requestDTO);
			requestDTO.setTipoMovimientoId(admiTipoMovimiento.getIdTipoMovimiento());
			responseEntity = infoMovimientoValidators.validarGuardarInfoMovimiento(requestDTO);
			InfoCuenta infoCuentas = infoMovimientoValidators.validarEntidadInfoCuentaByNumeroCuenta(requestDTO);
			requestDTO.setCuentaId(infoCuentas.getIdCuenta());
			saldoActual = infoCuentas.getSaldoActual();
			if(admiTipoMovimiento.getNombreMovimiento().contains("Retiro")) {
				List<AdmiConfigurationParameterDetResDTO> listParameters = iAdmiConfigurationParameterDetHandler
					.listParameterDetByParameterCab(
							AdmiConfigurationParameterCabReqDTO.builder()
									.name("MAXIMO_TRANSACCION_DIARIA").build());
				if (listParameters.isEmpty()) {
					throw new GenericException("No se encuentra parametrizado el maximo de retiros diarios", 10);
				}
				BigDecimal maximoRetiroDiario = new BigDecimal(listParameters.get(0).getValue());
				infoMovimientoValidators.validarSaldoInsuficiente(saldoActual, requestDTO.getValor());
				infoMovimientoValidators.validarMaximoRetiroDiario(infoCuentas.getIdCuenta(), maximoRetiroDiario, requestDTO.getValor());
				requestDTO.setValor(infoMovimientoValidators.validarQuitarSaldo(requestDTO.getValor()));
			
			} else {
				infoMovimientoValidators.validarAgregarSaldo(requestDTO.getValor());
			}
			saldoActual = saldoActual.add(requestDTO.getValor());
				
			requestDTO.setSaldo(saldoActual);
			if (responseEntity == null) {
				InfoMovimiento request = infoMovimientoConverter
					.mappingInfoMovimientoReqDTOtoInfoMovimientoEntity(requestDTO);
				request.setEstado(EnumStatus.ACTIVO.toString());
				responseEntity = infoMovimientoDAO.save(request);
			} 
			
			infoCuentas.setSaldoActual(saldoActual);
			infoCuentas.setFechaModificacion(new Date());
			infoCuentas.setPersonaModificacion(requestDTO.getPersonaCreacion());
			infoCuentaHandler.updateInfoCuentaByMovimiento(infoCuentas);
			response = infoMovimientoConverter
					.mappingInfoMovimientoEntityInfoCuentatoInfoMovimientoCuentaResDTO(responseEntity, infoCuentas, admiTipoMovimiento);
		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("db-account-movement-repository", e);
			throw e;
		}
		return response;
	}

	/**
	 * Actualiza un movimiento existente según el payload validado.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial 
	 * @since 15/04/2026
	 */
	@Override
	public InfoMovimientoCuentaResDTO updateInfoMovimiento(InfoMovimientoReqDTO requestDTO) throws GenericException {
		log.info("Metodo repository updateInfoMovimiento iniciado");
		InfoMovimientoCuentaResDTO response = new InfoMovimientoCuentaResDTO();
		InfoMovimiento responseEntity = new InfoMovimiento();
		try {
			AdmiTipoMovimiento admiTipoMovimiento = admiTipoMovimientoValidators.validarTipoMovimientoById(requestDTO);
			requestDTO.setTipoMovimientoId(admiTipoMovimiento.getIdTipoMovimiento());
			InfoCuenta infoCuentas = infoMovimientoValidators.validarEntidadInfoCuentaByNumeroCuenta(requestDTO);
			requestDTO.setCuentaId(infoCuentas.getIdCuenta());
			InfoMovimiento request = infoMovimientoValidators.validarActualizarInfoMovimiento(requestDTO);
			request.setTipoMovimientoId(admiTipoMovimiento.getIdTipoMovimiento());
			request.setCuentaId(infoCuentas.getIdCuenta());
			request.setSaldo(infoCuentas.getSaldoActual().subtract(request.getValor()));
			request.setFechaCreacion(new Date());
			request.setPersonaCreacion(requestDTO.getPersonaCreacion());
			if(admiTipoMovimiento.getNombreMovimiento().contains("Retiro")) {
				List<AdmiConfigurationParameterDetResDTO> listParameters = iAdmiConfigurationParameterDetHandler
					.listParameterDetByParameterCab(
							AdmiConfigurationParameterCabReqDTO.builder()
									.name("MAXIMO_TRANSACCION_DIARIA").build());
				if (listParameters.isEmpty()) {
					throw new GenericException("No se encuentra parametrizado el maximo de retiros diarios", 10);
				}
				BigDecimal maximoRetiroDiario = new BigDecimal(listParameters.get(0).getValue());

				infoMovimientoValidators.validarSaldoInsuficiente(request.getSaldo(), requestDTO.getValor());
				infoMovimientoValidators.validarMaximoRetiroDiarioActualiza(infoCuentas.getIdCuenta(), maximoRetiroDiario, requestDTO.getValor());
				requestDTO.setValor(infoMovimientoValidators.validarQuitarSaldo(requestDTO.getValor()));
			} else {
				infoMovimientoValidators.validarAgregarSaldo(requestDTO.getValor());
			}
			BigDecimal saldoActual = request.getSaldo().add(requestDTO.getValor());
			request.setValor(requestDTO.getValor());
			request.setSaldo(saldoActual);
			request.setFechaCreacion(new Date());
			request.setEstado(EnumStatus.ACTIVO.toString());
			responseEntity = infoMovimientoDAO.save(request);
			infoCuentas.setSaldoActual(saldoActual);
			infoCuentas.setFechaModificacion(new Date());
			infoCuentas.setPersonaModificacion(requestDTO.getPersonaCreacion());
			infoCuentaHandler.updateInfoCuentaByMovimiento(infoCuentas);
			response = infoMovimientoConverter
					.mappingInfoMovimientoEntityInfoCuentatoInfoMovimientoCuentaResDTO(responseEntity, infoCuentas, admiTipoMovimiento);
		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("db-account-movement-repository", e);
			throw e;
		}
		return response;
	}

	/**
	 * Marca o persiste la baja del movimiento indicado por identificador.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial 
	 * @since 15/04/2026
	 */
	@Override
	public Boolean deleteInfoMovimiento(ObjectIdDTO requestDTO) throws GenericException {
		log.info("Metodo repository deleteInfoMovimiento iniciado");
		Boolean response = false;
		try {
			InfoMovimiento request = infoMovimientoValidators.validarEntidadInfoMovimiento(requestDTO);
			InfoCuenta infoCuentas = infoMovimientoValidators.validarEntidadInfoMovimiento(request);
			infoCuentas.setSaldoActual(infoCuentas.getSaldoActual().subtract(request.getValor()));
			infoCuentaHandler.updateInfoCuentaByMovimiento(infoCuentas);
			request.setEstado(EnumStatus.ELIMINADO.toString());
			infoMovimientoDAO.saveAndFlush(request);
			response = true;
		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("db-account-movement-repository", e);
			throw e;
		}
		return response;
	}

	/**
	 * Lista movimientos aplicando filtros por ejemplo y orden por id de movimiento.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial 
	 * @since 15/04/2026
	 */
	@Override
	public List<InfoMovimientoCuentaResDTO> listByInfoMovimiento(InfoMovimientoReqDTO requestDto)
			throws GenericException {
				log.info("Metodo repository listByInfoMovimiento iniciado");
		List<InfoMovimientoCuentaResDTO> response = new ArrayList<>();
		List<InfoMovimiento> responseEntity = new ArrayList<>();
		try {
			infoMovimientoValidators.validarInfoMovimiento(requestDto);

			ExampleMatcher ignoringExampleMatcher = ExampleMatcher.matching()
					.withStringMatcher(StringMatcher.CONTAINING).withIgnoreCase();

			Example<InfoMovimiento> listFiltros = Example.of(infoMovimientoConverter
					.mappingInfoMovimientoReqDTOtoInfoMovimientoEntityList(requestDto),
					ignoringExampleMatcher);
			responseEntity = infoMovimientoDAO.findAll(listFiltros,
					Sort.by(Direction.ASC, InfoMovimiento.IDMOVIMIENTOVALUE));
			response = responseEntity.stream().map(
					item -> infoMovimientoConverter.mappingInfoMovimientoEntitytoInfoMovimientoCuentaResDTO(item))
					.collect(Collectors.toList());
		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("db-account-movement-repository", e);
			throw e;
		}
		return response;
	}

	/**
	 * Pagina movimientos con filtros dinámicos; la página es índice base 0 (primera página = 0).
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial 
	 * @since 15/04/2026
	 */
	@Override
	public Page<InfoMovimientoPageResDTO> pageListByInfoMovimiento(PageDTO<BusquedaGlobalDTO> requestDto)
			throws GenericException {
		log.info("Metodo repository pageListByInfoMovimiento iniciado");
		Pageable pageable;
		try {
			resourceValidators.validarPagina(requestDto);
			if ((requestDto.getOrder() != null) && (requestDto.getOrderValue() != null)) {
				pageable = PageRequest.of(requestDto.getPage(), requestDto.getSize(),
						Sort.by(Sort.Direction.valueOf(requestDto.getOrder()), requestDto.getOrderValue()));
			} else {
				pageable = PageRequest.of(requestDto.getPage(), requestDto.getSize(),
						Sort.Direction.DESC, InfoMovimiento.IDMOVIMIENTOVALUE);
			}

			String valorBusqueda = requestDto.getTabla().getValor(); 

			Specification<InfoMovimiento> spec = (root, query, cb) -> {
				Predicate movimientoActiva = cb.equal(root.get("estado"), "ACTIVO");
				Join<InfoMovimiento, AdmiTipoMovimiento> tipoMovimientoJoin = root.join("admiTipoMovimiento", JoinType.LEFT);
    			Join<InfoMovimiento, InfoCuenta> cuentaJoin = root.join("infoCuenta", JoinType.LEFT);
				Join<InfoCuenta, InfoPersona> personaJoin = cuentaJoin.join("infoPersona", JoinType.LEFT);
				Join<InfoCuenta, AdmiTipoCuenta> tipoCuentaJoin = cuentaJoin.join("admiTipoCuenta", JoinType.LEFT);
				Predicate personaActiva = cb.equal(personaJoin.get("estado"), "ACTIVO");
				Predicate cuentaActiva = cb.equal(cuentaJoin.get("estado"), "ACTIVO");

				Predicate requisitosObligatorios = cb.and(movimientoActiva, cuentaActiva, personaActiva);

				if (valorBusqueda == null || valorBusqueda.trim().isEmpty()) {
					return null;
				}

				String patron = "%" + valorBusqueda.toLowerCase() + "%";

				Predicate busquedaGlobalOr = cb.or(
					cb.like(cb.lower(root.get("estado")), patron),
					cb.like(cb.lower(cuentaJoin.get("numeroCuenta")), patron),
					cb.like(cb.lower(personaJoin.get("nombre")), patron),
					cb.like(cb.lower(personaJoin.get("apellido")), patron),
					cb.like(cb.lower(personaJoin.get("identificacion")), patron),
					cb.like(cb.lower(tipoCuentaJoin.get("nombreCuenta")), patron),
					cb.like(cb.lower(tipoMovimientoJoin.get("nombreMovimiento")), patron)
				);

				return cb.and(busquedaGlobalOr);
			};
			Page<InfoMovimiento> responsePageEntity = infoMovimientoDAO.findAll(spec, pageable);
			return responsePageEntity.map(infoMovimientoConverter::mappingInfoMovimientoEntitytoInfoMovimientoPageResDTO);


		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("db-account-movement-repository", e);
			throw e;
		}
	}

	/**
	 * Movimientos de un persona en un rango de fechas para reporte de estado de cuenta.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial 
	 * @since 15/04/2026
	 */
	@Override
	public List<InfoMovimientoReporteResDTO> listByInfoMovimientoReporte(InfoMovimientoReporteReqDTO requestDTO) throws GenericException {

		log.info("Metodo repository listByInfoMovimientoReporte iniciado");

		List<InfoMovimientoReporteResDTO> response = new ArrayList<>();

		try {
			// Validación
			infoMovimientoValidators.validarInfoMovimientoReporte(requestDTO);

			// Convertir fechas SOLO si vienen
			Date fechaDesde = null;
			Date fechaHasta = null;

			if (requestDTO.getFechaDesde() != null  && !requestDTO.getFechaDesde().isEmpty() 
				&& requestDTO.getFechaHasta() != null && !requestDTO.getFechaHasta().isEmpty() ) {
				fechaDesde = infoMovimientoConverter.fechaInicio(requestDTO.getFechaDesde());
				fechaHasta = infoMovimientoConverter.fechaFin(requestDTO.getFechaHasta());
			}

			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<InfoMovimiento> query = cb.createQuery(InfoMovimiento.class);
			Root<InfoMovimiento> root = query.from(InfoMovimiento.class);

			Join<InfoMovimiento, InfoCuenta> cuentaJoin = root.join("infoCuenta");
			Join<InfoCuenta, InfoPersona> personaJoin = cuentaJoin.join("infoPersona");

			List<Predicate> predicates = new ArrayList<>();
			predicates.add(cb.equal(cuentaJoin.get("estado"), "ACTIVO"));
			if (requestDTO.getIdentificacion() != null && !requestDTO.getIdentificacion().isEmpty()) {
				predicates.add(cb.like(
						personaJoin.get("identificacion"),
						"%" + requestDTO.getIdentificacion() + "%"
				));
			}

			// Filtro fechas (solo si vienen ambas)
			if (fechaDesde != null && fechaHasta != null) {
				predicates.add(cb.between(
						root.get("fechaCreacion"),
						fechaDesde,
						fechaHasta
				));
			}

			query.where(cb.and(predicates.toArray(new Predicate[0])));

			query.orderBy(
					cb.asc(cuentaJoin.get("numeroCuenta")),
					cb.desc(root.get("fechaCreacion"))
			);

			List<InfoMovimiento> movimientos = entityManager.createQuery(query).getResultList();

			// Validación resultado
			if (movimientos == null || movimientos.isEmpty()) {
				throw new GenericException("No se encontraron movimientos", 10);
			}

			// Mapping
			response = movimientos.stream()
					.map(infoMovimientoConverter::mappingInfoMovimientoEntitytoInfoMovimientoReporteResDTO)
					.collect(Collectors.toList());

		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("db-account-movement-repository", e);
			throw e;
		}

		return response;
	}

	/**
	 * Movimientos de un persona en un rango de fechas para reporte de estado de cuenta.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial 
	 * @since 15/04/2026
	 */
	@Override
	public Page<InfoMovimientoReporteResDTO> pageListByInfoMovimientoReporte(
			PageDTO<InfoMovimientoReporteReqDTO> requestPageDTO) throws GenericException {

		log.info("Metodo repository listByInfoMovimientoReporte iniciado");

		Pageable pageable;

		try {
			resourceValidators.validarPagina(requestPageDTO);
			InfoMovimientoReporteReqDTO requestDTO = requestPageDTO.getTabla();

			if (requestPageDTO.getOrder() != null && requestPageDTO.getOrderValue() != null) {
				pageable = PageRequest.of(
						requestPageDTO.getPage(),
						requestPageDTO.getSize(),
						Sort.by(Sort.Direction.valueOf(requestPageDTO.getOrder()),
								requestPageDTO.getOrderValue())
				);
			} else {
				pageable = PageRequest.of(
						requestPageDTO.getPage(),
						requestPageDTO.getSize(),
						Sort.Direction.DESC,
						InfoMovimiento.IDMOVIMIENTOVALUE
				);
			}

			infoMovimientoValidators.validarInfoMovimientoReporte(requestDTO);
			Date fechaDesde = null;
			Date fechaHasta = null;

			if (requestDTO.getFechaDesde() != null  && !requestDTO.getFechaDesde().isEmpty() 
				&& requestDTO.getFechaHasta() != null && !requestDTO.getFechaHasta().isEmpty() ) {
				fechaDesde = infoMovimientoConverter.fechaInicio(requestDTO.getFechaDesde());
				fechaHasta = infoMovimientoConverter.fechaFin(requestDTO.getFechaHasta());
			}

			CriteriaBuilder cb = entityManager.getCriteriaBuilder();

			CriteriaQuery<InfoMovimiento> query = cb.createQuery(InfoMovimiento.class);
			Root<InfoMovimiento> root = query.from(InfoMovimiento.class);
			Join<InfoMovimiento, InfoCuenta> cuentaJoin = root.join("infoCuenta");
			Join<InfoCuenta, InfoPersona> personaJoin = cuentaJoin.join("infoPersona");
			List<Predicate> predicates = new ArrayList<>();
			//predicates.add(cb.equal(root.get("estado"), "ACTIVO"));
			//predicates.add(cb.equal(cuentaJoin.get("estado"), "ACTIVO"));

			if (requestDTO.getIdentificacion() != null && !requestDTO.getIdentificacion().isEmpty()) {

				String patron = "%" + requestDTO.getIdentificacion().toLowerCase() + "%";
				Predicate busquedaGlobalOr = cb.or(
					cb.like(cb.lower(personaJoin.get("nombre")), patron),
					cb.like(cb.lower(personaJoin.get("apellido")), patron),
					cb.like(cb.lower(personaJoin.get("identificacion")), patron)
				);
				predicates.add(busquedaGlobalOr);
			}

			if (fechaDesde != null && fechaHasta != null) {
				predicates.add(cb.between(root.get("fechaCreacion"), fechaDesde, fechaHasta));
			}

			query.where(cb.and(predicates.toArray(new Predicate[0])));

			query.orderBy(cb.desc(root.get(InfoMovimiento.IDMOVIMIENTOVALUE)));
			TypedQuery<InfoMovimiento> typedQuery = entityManager.createQuery(query);
			typedQuery.setFirstResult((int) pageable.getOffset());
			typedQuery.setMaxResults(pageable.getPageSize());
			List<InfoMovimiento> movimientos = typedQuery.getResultList();
			CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
			Root<InfoMovimiento> countRoot = countQuery.from(InfoMovimiento.class);
			Join<InfoMovimiento, InfoCuenta> countCuentaJoin = countRoot.join("infoCuenta");
			Join<InfoCuenta, InfoPersona> countPersonaJoin = countCuentaJoin.join("infoPersona");
			List<Predicate> countPredicates = new ArrayList<>();
			//countPredicates.add(cb.equal(countRoot.get("estado"), "ACTIVO"));
			//countPredicates.add(cb.equal(countCuentaJoin.get("estado"), "ACTIVO"));

			if (requestDTO.getIdentificacion() != null && !requestDTO.getIdentificacion().isEmpty()) {
				String patron = "%" + requestDTO.getIdentificacion().toLowerCase() + "%";
				Predicate busquedaGlobalOr = cb.or(
					cb.like(cb.lower(personaJoin.get("nombre")), patron),
					cb.like(cb.lower(personaJoin.get("apellido")), patron),
					cb.like(cb.lower(personaJoin.get("identificacion")), patron)
				);
				predicates.add(busquedaGlobalOr);
			}
			if (fechaDesde != null && fechaHasta != null) {
				countPredicates.add(cb.between(countRoot.get("fechaCreacion"), fechaDesde, fechaHasta));
			}
			countQuery.select(cb.count(countRoot));
			countQuery.where(cb.and(countPredicates.toArray(new Predicate[0])));
			Long total = entityManager.createQuery(countQuery).getSingleResult();

			List<InfoMovimientoReporteResDTO> response = movimientos.stream()
					.map(infoMovimientoConverter::mappingInfoMovimientoEntitytoInfoMovimientoReporteResDTO)
					.collect(Collectors.toList());

			return new PageImpl<>(response, pageable, total);

		} catch (GenericException e) {
			ExceptionLogSupport.logWarn("db-account-movement-repository", e);
			throw e;
		}
	}

	/**
	 * Movimientos de un persona en un rango de fechas para reporte de estado de cuenta reporte.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial 
	 * @since 15/04/2026
	 */
	@Override
	public ReportePdfDTO pageListByInfoMovimientoReportePdf(
			PageDTO<InfoMovimientoReporteReqDTO> requestPageDTO) throws GenericException {
		
		Page<InfoMovimientoReporteResDTO> page = pageListByInfoMovimientoReporte(requestPageDTO);
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

			Document document = new Document(PageSize.A4.rotate());
			PdfWriter.getInstance(document, baos);
			document.open();

			Font font = new Font(Font.HELVETICA, 14, Font.BOLD);
			Paragraph titulo = new Paragraph("Reporte de Movimientos", font);
			titulo.setAlignment(Element.ALIGN_CENTER);
			titulo.setSpacingAfter(5f); 
			document.add(titulo);

			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			String fechaFormateada = now.format(formatter);
			Font fontNormal = new Font(Font.HELVETICA, 10, Font.NORMAL);
			Paragraph fecha = new Paragraph("Fecha reporte: " + fechaFormateada, fontNormal);
			fecha.setAlignment(Element.ALIGN_CENTER);
			fecha.setSpacingAfter(10f);
			document.add(fecha);

			Font fontHeader = new Font(Font.HELVETICA, 8, Font.BOLD);

			PdfPTable table = new PdfPTable(10);
			table.addCell(new Phrase("Fecha", fontHeader));
			table.addCell(new Phrase("Identificación", fontHeader));
			table.addCell(new Phrase("Nombre", fontHeader));
			table.addCell(new Phrase("Apellido", fontHeader));
			table.addCell(new Phrase("Número Cuenta", fontHeader));
			table.addCell(new Phrase("Tipo", fontHeader));
			table.addCell(new Phrase("Saldo Inicial", fontHeader));
			table.addCell(new Phrase("Estado", fontHeader));
			table.addCell(new Phrase("Movimiento", fontHeader));
			table.addCell(new Phrase("Saldo disponible", fontHeader));

			Font fontBody = new Font(Font.HELVETICA, 8, Font.NORMAL);

			for (InfoMovimientoReporteResDTO item : page.getContent()) {
				table.addCell(new Phrase(String.valueOf(item.getFecha()), fontBody));
				table.addCell(new Phrase(String.valueOf(item.getIdentificacion()), fontBody));
				table.addCell(new Phrase(String.valueOf(item.getNombre()), fontBody));
				table.addCell(new Phrase(String.valueOf(item.getApellido()), fontBody));
				table.addCell(new Phrase(String.valueOf(item.getNumeroCuenta()), fontBody));
				table.addCell(new Phrase(String.valueOf(item.getTipoCuentaNombre()), fontBody));
				table.addCell(new Phrase(String.valueOf(item.getSaldoInicial()), fontBody));
				table.addCell(new Phrase(String.valueOf(item.getEstado()), fontBody));
				table.addCell(new Phrase(String.valueOf(item.getMovimiento()), fontBody));
				table.addCell(new Phrase(String.valueOf(item.getSaldoDisponible()), fontBody));
			}
			document.add(table);
			document.close();
			String base64 = Base64.getEncoder().encodeToString(baos.toByteArray());
			ReportePdfDTO dto = new ReportePdfDTO();
			dto.setNombreArchivo("reporte_"+fechaFormateada+".pdf");
			dto.setBase64(base64);
			return dto;

		} catch (Exception e) {
			throw new RuntimeException("Error generando PDF", e);
		}
	}
}
