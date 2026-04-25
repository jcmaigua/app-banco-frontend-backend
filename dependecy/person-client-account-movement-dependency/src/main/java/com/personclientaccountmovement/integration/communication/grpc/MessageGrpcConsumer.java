package com.personclientaccountmovement.integration.communication.grpc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.personclientaccountmovement.cons.PersonClientAccountMovementConfigConstants;
import com.personclientaccountmovement.exception.GenericException;
import com.personclientaccountmovement.integration.dispatcher.ICompleteResultDispatcher;
import com.personclientaccountmovement.integration.dto.request.GenericRequestDTO;
import com.personclientaccountmovement.integration.grpc.gen.GrpcGenericRequest;
import com.personclientaccountmovement.integration.grpc.gen.GrpcGenericResponse;
import io.grpc.stub.StreamObserver;

/**
* Consumidor gRPC de mensajes genéricos.
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Service
public class MessageGrpcConsumer {

	private static final Logger log = LogManager.getLogger(MessageGrpcConsumer.class);

	private final ObjectMapper objectMapper = new ObjectMapper();

	public void process(GrpcGenericRequest request, StreamObserver<GrpcGenericResponse> responseObserver,
			ICompleteResultDispatcher dispatcher) {
		try {
			Object payloadNode = parsePayload(request.getPayloadJson());
			GenericRequestDTO<Object> dto = GenericRequestDTO.<Object>builder()
					.userRequest(request.getUserRequest())
					.payload(payloadNode)
					.build();
			Object result = dispatcher.dispatch(request.getMethod(), dto);
			String json = objectMapper.writeValueAsString(result);
			responseObserver.onNext(GrpcGenericResponse.newBuilder().setPayloadJson(json).build());
			responseObserver.onCompleted();
		} catch (GenericException e) {
			log.warn("gRPC dispatch GenericException method={} transactionId={} userRequest={} code={} message={}",
					request.getMethod(), request.getTransactionId(), request.getUserRequest(), e.getCodeError(),
					e.getMessageError(), e);
			responseObserver.onNext(GrpcGenericResponse.newBuilder()
					.setErrorMessage(e.getMessageError())
					.setCode(e.getCodeError() != null ? e.getCodeError() : PersonClientAccountMovementConfigConstants.CODE_ERROR_GENERIC)
					.build());
			responseObserver.onCompleted();
		} catch (Exception e) {
			log.error("gRPC process error", e);
			responseObserver.onNext(GrpcGenericResponse.newBuilder()
					.setErrorMessage(e.getMessage() != null ? e.getMessage() : e.toString())
					.setCode(PersonClientAccountMovementConfigConstants.CODE_ERROR_GENERIC)
					.build());
			responseObserver.onCompleted();
		}
	}

	private Object parsePayload(String payloadJson) throws com.fasterxml.jackson.core.JsonProcessingException {
		if (payloadJson == null || payloadJson.isBlank()) {
			return null;
		}
		return objectMapper.readTree(payloadJson);
	}
}
