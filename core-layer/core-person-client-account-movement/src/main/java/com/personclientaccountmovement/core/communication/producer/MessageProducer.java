package com.personclientaccountmovement.core.communication.producer;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.personclientaccountmovement.core.config.PersonClientAppConfig;
import com.personclientaccountmovement.exception.GenericException;
import com.personclientaccountmovement.integration.communication.IGenericMessageProducer;
import com.personclientaccountmovement.integration.config.CommonConfig;
import com.personclientaccountmovement.integration.dto.request.InternalRequestDTO;
import com.personclientaccountmovement.integration.dto.response.GenericResponseDTO;
import com.personclientaccountmovement.integration.interfaces.IGenericProducer;
import com.personclientaccountmovement.integration.model.GenericProducerModel;
import com.personclientaccountmovement.integration.util.CommonUtil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
* Productor de mensajes salientes (contexto local del módulo).
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
@Component("MessageProducer")
@SuppressWarnings("unchecked")
public class MessageProducer implements IGenericProducer {

    Logger log = LogManager.getLogger(this.getClass());

    @Autowired
    private BeanFactory factory;
    @Autowired
    private PersonClientAppConfig config;   

/**
* Procesa el modelo de productor y devuelve la respuesta.
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
    @Override
    public <P, R> R process(GenericProducerModel<P> request) throws GenericException {
        log.info("getType: "+request.getType());
        log.info("getTransactionId: "+request.getTransactionId());
        log.info("getPayload: "+request.getPayload());
        log.info("getOrigin: "+request.getOrigin());
        log.info("getService: "+request.getService());

        @SuppressWarnings("rawtypes")
        IGenericMessageProducer<InternalRequestDTO, GenericResponseDTO> producer = factory.getBean(
                CommonConfig.DELIVERY_CHANNEL.get(request.getType()), IGenericMessageProducer.class);

        try {
            return (R) producer.sendMessage(InternalRequestDTO.<P>builder()
                    .transactionId(request.getTransactionId())
                    .payload(request.getPayload())
                    .origin(request.getOrigin())
                    .requestType(request.getType())
                    .topicName(null)
                    .blockingStub(CommonUtil.getBlockingStub(
                            config.getGrpcServerMap().get(request.getService()),
                            config.getGrpcIdleTimeout(),
                            (request.getRequestTimeoutSeconds() > 0) ? request.getRequestTimeoutSeconds()
                                    : config.getGrpcDeadlineTimeout(),
                            config.getInboundMessageSize(),
                            config.getOutboundMessageSize()
                    ))
                    .option(request.getOption())
                    .build());
        } catch (GenericException e) {
            log.error("[core-person-client-account-movement MessageProducer] Fallo option={} service={} type={} transactionId={}",
                    request.getOption(), request.getService(), request.getType(), request.getTransactionId(), e);
            throw e;
        }
    }

}
