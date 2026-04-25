package com.personclientaccountmovement.core.config;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.personclientaccountmovement.core.communication.consumer.GrpcConsumer;

import io.grpc.Server;
import io.grpc.ServerBuilder;

/**
* Arranque y ciclo de vida del servidor gRPC.
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Component
public class GrpcServerConfiguration {
	
	private Server server;

	@Value("${grpc.server.port}")
	private int port;	
	
	@Value("${grpc.server.inbound.message.size:10485760}")
	private int maxInboundSize;	
	
	@Autowired
	private GrpcConsumer<?> grpcConsumer;

	Logger log = LogManager.getLogger(this.getClass());

	public void start() {
		try {
			server = ServerBuilder.forPort(port)
					.maxInboundMessageSize(maxInboundSize)
					.addService(grpcConsumer)
					.build()
					.start();

			log.info("gRPC server started on port: {}", port);

			Runtime.getRuntime().addShutdownHook(new Thread(() -> {
				log.info("Shutting down gRPC server");
				stop();
			}));
		} catch (IOException e) {
			log.error("gRPC server failed to start on port {}: {}", port, e.getMessage(), e);
			throw new IllegalStateException("Could not start gRPC server on port " + port, e);
		}
	}
	public void stop() {
	    if (server != null) {
	        server.shutdown();
	    }
	}
	public void blockUntilShutdown() {
	    try 
	    {
	        if (server != null) {
	            server.awaitTermination();
	        }
	    }
	    catch (InterruptedException e) 
	    {
	    	 log.error("gRPC server started ERROR: {}",e.getMessage()); 
	    	 Thread.currentThread().interrupt();
	    }
	}

}
