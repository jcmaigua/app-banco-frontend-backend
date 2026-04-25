package com.personclientaccountmovement.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import com.personclientaccountmovement.cons.PersonClientAccountMovementConfigConstants;
import com.personclientaccountmovement.exception.GenericException;

/**
* Estados persistidos en el dominio banco (registro activo y baja lógica).
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial 
* @since 15/04/2026
*/
public enum EnumStatus {
	ACTIVO("ACTIVO"),
	ELIMINADO("ELIMINADO");

	private final String status;

	EnumStatus(String status) {
		this.status = status;
	}

	/**
	 * Devuelve la etiqueta de negocio asociada al estado.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial 
	 * @since 15/04/2026
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Busca el enum cuyo texto de estado coincide exactamente con el valor dado.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial 
	 * @since 15/04/2026
	 */
	public static Optional<EnumStatus> getEnumByValue(String status) {
		return Arrays.stream(EnumStatus.values())
				.filter(env -> env.status.equals(status))
				.findFirst();
	}

	/**
	 * Resuelve el enum por nombre de constante (ignora mayúsculas).
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial 
	 * @since 15/04/2026
	 */
	public static EnumStatus getEnumByName(String name) {
		for (EnumStatus b : EnumStatus.values()) {
			if (b.name().equalsIgnoreCase(name)) {
				return b;
			}
		}
		return null;
	}

	/**
	 * Valida y convierte un nombre de estado a {@link EnumStatus}.
	 * 
	 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
	 * @version 1.0 - Version Inicial 
	 * @since 15/04/2026
	 */
	public static EnumStatus getEnum(String status) throws GenericException {
		if (status == null) {
			throw new GenericException("El valor de estado es no valido",
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
		ArrayList<String> listEnum = new ArrayList<>();

		for (EnumStatus itemEnum : EnumStatus.values()) {
			listEnum.add(itemEnum.name());
		}
		if (!listEnum.contains(status)) {
			throw new GenericException("El valor de estado es no valido",
					PersonClientAccountMovementConfigConstants.MISSING_VALUES);
		}
		return EnumStatus.valueOf(status);

	}
}
