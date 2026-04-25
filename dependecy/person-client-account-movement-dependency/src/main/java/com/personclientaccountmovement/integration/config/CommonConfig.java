package com.personclientaccountmovement.integration.config;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

import com.personclientaccountmovement.integration.enums.EnumRequestType;

/**
* Nombres de beans Spring por tipo de canal de entrega.
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
public final class CommonConfig {

/**
* Impide instanciación; utilidad de constantes y mapas estáticos.
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
	private CommonConfig() {
	}

	private static final Map<EnumRequestType, String> DELIVERY = buildDeliveryMap();

	/**
	 * Construye el mapa de tipo de petición a nombre de bean productor.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	private static Map<EnumRequestType, String> buildDeliveryMap() {
		Map<EnumRequestType, String> m = new EnumMap<>(EnumRequestType.class);
		m.put(EnumRequestType.SYNC, "grpcSyncMessageProducer");
		m.put(EnumRequestType.ASYNC, "grpcSyncMessageProducer");
		m.put(EnumRequestType.REST, "restMessageProducer");
		return Collections.unmodifiableMap(m);
	}

	/**
	 * Vista pública del mapa de canales a beans; compatibilidad con {@code DELIVERY_CHANNEL.get(...)}.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial
	 * @since 15/04/2026
	 */
	public static final Map<EnumRequestType, String> DELIVERY_CHANNEL = DELIVERY;
}
