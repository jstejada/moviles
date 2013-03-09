package com.fer.messageProtocol.messages;

public class Confirmacion {

	private String pasajeroId;
	private int tiempoAprox;
	
	public Confirmacion(String pasajeroId, int tiempoAprox) {
		super();
		this.pasajeroId = pasajeroId;
		this.tiempoAprox = tiempoAprox;
	}
	public String getPasajeroId() {
		return pasajeroId;
	}
	public int getTiempoAprox() {
		return tiempoAprox;
	}
	
	
}