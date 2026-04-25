package com.personclientaccountmovement.integration.producer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.personclientaccountmovement.cons.PersonClientAccountMovementConfigConstants;
import com.personclientaccountmovement.exception.GenericException;
import com.personclientaccountmovement.integration.communication.IGenericMessageProducer;
import com.personclientaccountmovement.integration.dto.request.InternalRequestDTO;
import com.personclientaccountmovement.integration.dto.response.GenericResponseDTO;
import com.personclientaccountmovement.integration.grpc.gen.GrpcGenericRequest;
import com.personclientaccountmovement.integration.grpc.gen.GrpcGenericResponse;

/**
* Implementación de productor de mensajes gRPC síncronos.
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Service("grpcSyncMessageProducer")
public class GrpcSyncMessageProducer implements IGenericMessageProducer<InternalRequestDTO<?>, GenericResponseDTO<?>> {

	private static final Logger log = LogManager.getLogger(GrpcSyncMessageProducer.class);

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	@SuppressWarnings("unchecked")
	public GenericResponseDTO<?> sendMessage(InternalRequestDTO<?> request) throws GenericException {
		if (request.getBlockingStub() == null) {
			throw new GenericException("blockingStub is required for SYNC delivery",
					PersonClientAccountMovementConfigConstants.CODE_ERROR_GENERIC);
		}
		try {
			String payloadJson = request.getPayload() == null ? ""
					: objectMapper.writeValueAsString(request.getPayload());
			GrpcGenericRequest protoRequest = GrpcGenericRequest.newBuilder()
					.setMethod(request.getOption() != null ? request.getOption() : "")
					.setTransactionId(request.getTransactionId() != null ? request.getTransactionId() : "")
					.setPayloadJson(payloadJson)
					.setUserRequest(request.getOrigin() != null ? request.getOrigin() : "")
					.build();
			GrpcGenericResponse protoResponse = request.getBlockingStub().getGenericData(protoRequest);
			if (protoResponse.getErrorMessage() != null && !protoResponse.getErrorMessage().isEmpty()) {
				log.warn("gRPC respuesta con error method={} transactionId={} code={} message={}",
						request.getOption(), request.getTransactionId(), protoResponse.getCode(),
						protoResponse.getErrorMessage());
				throw new GenericException(protoResponse.getErrorMessage(),
						protoResponse.getCode() != 0 ? protoResponse.getCode()
								: PersonClientAccountMovementConfigConstants.CODE_ERROR_GENERIC);
			}
			Object body = protoResponse.getPayloadJson() == null || protoResponse.getPayloadJson().isBlank() ? null
					: objectMapper.readValue(protoResponse.getPayloadJson(), Object.class);
			return GenericResponseDTO.success(body);
		} catch (GenericException e) {
			throw e;
		} catch (Exception e) {
			log.error("Fallo llamada gRPC cliente method={} transactionId={}", request.getOption(),
					request.getTransactionId(), e);
			throw new GenericException(e.getMessage(), PersonClientAccountMovementConfigConstants.CODE_ERROR_GENERIC);
		}
	}
}
