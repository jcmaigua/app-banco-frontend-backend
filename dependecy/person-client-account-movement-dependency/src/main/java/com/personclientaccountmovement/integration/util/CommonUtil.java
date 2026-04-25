package com.personclientaccountmovement.integration.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import com.personclientaccountmovement.cons.PersonClientAccountMovementConfigConstants;
import com.personclientaccountmovement.exception.GenericException;
import com.personclientaccountmovement.integration.grpc.gen.GrpcGenericServiceGrpc;
import com.personclientaccountmovement.integration.model.GenericProducerModel;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;

/**
* Utilidades comunes para la integración entre servicios.
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/


public final class CommonUtil {

	private CommonUtil() {
	}

	/**
	 * Invoca por reflexión un método de instancia {@code (GenericProducerModel)} del target.
	 */
	public static <P, R> R executeMethod(Object target, String methodName, GenericProducerModel<P> request)
			throws GenericException {
		if (methodName == null || methodName.isBlank()) {
			throw new GenericException("No method mapped for request option",
					PersonClientAccountMovementConfigConstants.CODE_ERROR_GENERIC);
		}
		Method method = findProducerMethod(target.getClass(), methodName);
		method.setAccessible(true);
		try {
			@SuppressWarnings("unchecked")
			R result = (R) method.invoke(target, request);
			return result;
		} catch (InvocationTargetException e) {
			Throwable c = e.getCause();
			if (c instanceof GenericException) {
				throw (GenericException) c;
			}
			throw new GenericException(c != null ? c.getMessage() : e.getMessage(),
					PersonClientAccountMovementConfigConstants.CODE_ERROR_GENERIC);
		} catch (IllegalAccessException e) {
			throw new GenericException("Cannot invoke handler " + methodName + ": " + e.getMessage(),
					PersonClientAccountMovementConfigConstants.CODE_ERROR_GENERIC);
		}
	}

	private static Method findProducerMethod(Class<?> start, String name) throws GenericException {
		for (Class<?> c = start; c != null; c = c.getSuperclass()) {
			for (Method m : c.getDeclaredMethods()) {
				if (name.equals(m.getName()) && m.getParameterCount() == 1
						&& m.getParameterTypes()[0] == GenericProducerModel.class) {
					return m;
				}
			}
		}
		throw new GenericException("Handler method not found: " + name,
				PersonClientAccountMovementConfigConstants.CODE_ERROR_GENERIC);
	}

	public static GrpcGenericServiceGrpc.GrpcGenericServiceBlockingStub getBlockingStub(String target,
			int idleTimeoutMinutes, int deadlineSeconds, int inboundMessageSize, int outboundMessageSize) {
		NettyChannelBuilder builder = NettyChannelBuilder.forTarget(target)
				.maxInboundMessageSize(inboundMessageSize);
		// Los servicios internos actuales exponen gRPC sin TLS.
		// Si en futuro se usa grpcs://, se mantiene transport security.
		if (target != null && target.startsWith("grpcs://")) {
			builder.useTransportSecurity();
		} else {
			builder.usePlaintext();
		}
		if (idleTimeoutMinutes > 0) {
			builder.keepAliveTime(idleTimeoutMinutes, TimeUnit.MINUTES);
		}
		return GrpcGenericServiceGrpc.newBlockingStub(builder.build())
				.withDeadlineAfter(deadlineSeconds > 0 ? deadlineSeconds : 60, TimeUnit.SECONDS);
	}
}
