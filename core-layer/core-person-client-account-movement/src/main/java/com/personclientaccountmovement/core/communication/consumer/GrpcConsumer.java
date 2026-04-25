package com.personclientaccountmovement.core.communication.consumer;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personclientaccountmovement.integration.communication.grpc.MessageGrpcConsumer;
import com.personclientaccountmovement.integration.dispatcher.ICompleteResultDispatcher;
import com.personclientaccountmovement.integration.grpc.gen.GrpcGenericRequest;
import com.personclientaccountmovement.integration.grpc.gen.GrpcGenericResponse;
import com.personclientaccountmovement.integration.grpc.gen.GrpcGenericServiceGrpc.GrpcGenericServiceImplBase;
import io.grpc.stub.StreamObserver;

/**
* Consumidor gRPC de peticiones genéricas.
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Service
public class GrpcConsumer<T> extends GrpcGenericServiceImplBase{

	@Autowired
	private BeanFactory  beanFactory;

	private ICompleteResultDispatcher dispatcher;

	@Autowired
	private MessageGrpcConsumer grpcConsumer;

	/**
	 * Obtiene o evalúa el valor asociado a getGenericData.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	@Override
	public void getGenericData(GrpcGenericRequest request, StreamObserver<GrpcGenericResponse> responseObserver) {
		dispatcher = beanFactory.getBean("AnythingDispatcher", ICompleteResultDispatcher.class);
		grpcConsumer.process(request, responseObserver, dispatcher);
	}

}
