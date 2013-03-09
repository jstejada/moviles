package com.fer.messageProtocol.sender;

import com.fer.messageProtocol.ProtocolManager;
import com.fer.messageProtocol.SMSC;

public abstract class TaxiSender implements MessageReceiver{

	MessageSender sender;
	
	public TaxiSender(MessageSender sender) {
		this.sender = sender;
	}
	
	public void confirmarServicio(String idPasajero, int tiempoAprox)
	{
		String message = ProtocolManager.getConfirmarServicio(idPasajero, tiempoAprox);
		sender.sendMessage(SMSC.SMSC_NUMBER, message);
	}
	
	
	public void onMessageReceived(String sender, String message){
		
		if (ProtocolManager.isSolicitarServicio(message))
		{
			String ubicacion = ProtocolManager.getSolicitarServicio(message);
			onServicioSolicitado(ubicacion);
		}
		else
		{
			onUnknownMessage(sender,message);
		}
		
	};
	
	public abstract void onServicioSolicitado(String ubicacion);
	public abstract void onUnknownMessage(String sender, String message);
}