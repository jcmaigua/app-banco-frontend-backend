package com.personclientaccountmovement.integration.dto.request;

import com.personclientaccountmovement.integration.enums.EnumRequestType;
import com.personclientaccountmovement.integration.grpc.gen.GrpcGenericServiceGrpc;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* DTO interno de solicitud para el flujo de despacho.
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InternalRequestDTO<P> {

	private String transactionId;
	private P payload;
	private String origin;
	private EnumRequestType requestType;
	private String topicName;
	private GrpcGenericServiceGrpc.GrpcGenericServiceBlockingStub blockingStub;
	private String option;
}
