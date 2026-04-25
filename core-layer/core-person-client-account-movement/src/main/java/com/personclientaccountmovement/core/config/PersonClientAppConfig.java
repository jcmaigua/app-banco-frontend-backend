package com.personclientaccountmovement.core.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;
import lombok.Data;

/**
* Beans y escaneo de componentes de PersonClient.
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Data
@Configuration
public class PersonClientAppConfig {
	@Value("#{${grpc.client-server.map}}")
	private Map<String, String> grpcServerMap;
	@Value("${grpc.client.timeout.idle:0}")
	private int grpcIdleTimeout;
	@Value("${grpc.client.timeout.deadline-after:0}")
	private int grpcDeadlineTimeout;
	@Value("${grpc.client.inbound.message.size:5242880}")
    private int inboundMessageSize;
    @Value("${grpc.client.outbound.message.size:5242880}")
    private int outboundMessageSize;

	@PostConstruct
	private void setDefaultValues() {
		grpcServerMap = (grpcServerMap == null) ? new HashMap<>() : grpcServerMap;
	}
}
