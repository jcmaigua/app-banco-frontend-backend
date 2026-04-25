package com.personclientaccountmovement.comp.cons;

/**
* Constantes de la capa compuesta.
* 
* @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
* @version 1.0 - Version Inicial
* @since 15/04/2026
*/
public class CompPersonClientAccountMovementConstants {
	
	private CompPersonClientAccountMovementConstants() {}
	
	//Definicion del Service bean name del producer
	public static final String MESSAGE_PRODUCER = "MessageProducer";
	
	//Service bean name de handlers
	public static final String PERSON_CLIENT_ACCOUNT_MOVEMENT_HANDLER    = "PersonClientAccountMovementHandler";

	// Producer message channel map (gRPC / REST / ...)
	public static final String PERSON_CLIENTE_ACCOUNT_MOVEMENT_MAP   = "person-client-account-movement";

}

