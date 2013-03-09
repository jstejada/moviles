package com.fer.messageProtocol.sender;

import java.util.List;

import com.fer.messageProtocol.ProtocolManager;
import com.fer.messageProtocol.messages.Confirmacion;

public class SMSCSender implements MessageReceiver{
	
	private MessageSender sender;
	private List<String> taxisList;
	
	public SMSCSender(MessageSender sender, List<String> taxiList) {
		this.sender = sender;
		this.taxisList = taxiList;
	}
	
	public void sendConfirmar(String ubicacion)
	{
		for (String taxiId : taxisList) {
			String message = ProtocolManager.getSolicitarServicio(ubicacion);
			sender.sendMessage(taxiId, message);
		}
	}
	
	
	@Override
	public void onMessageReceived(String sender, String message) {
		
		if (ProtocolManager.isConfirmarServicio(message))
		{
			Confirmacion confirmacion = ProtocolManager.getConfirmarServicio(message);
			onConfirmarServicio(confirmacion);
		}
		else if (ProtocolManager.isSolicitarServicio(message))
		{
			String direccion = ProtocolManager.getDireccion(message);
			sendConfirmar(direccion);
		}
	}

	public void onConfirmarServicio(Confirmacion confirmacion) {
		String pasajeroId = confirmacion.getPasajeroId();
		sender.sendMessage(pasajeroId, ProtocolManager.getServicioConfirmado(confirmacion.getTiempoAprox()));
	}
	
}
