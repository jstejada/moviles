package com.fer.messageProtocol;

import com.fer.messageProtocol.messages.Confirmacion;

public class ProtocolManager {

	public final static String SOLICITAR_TAXI = "Solicitar Taxi";
	public final static String CONFIRMAR_TAXI = "Confirmar Taxi";
	public final static String SEPARADOR = ":";
	
	public static String getSolicitarServicio(String ubicacion)
	{
		return SOLICITAR_TAXI+SEPARADOR+ubicacion;
	}
	
	public static String getDireccion(String mensajeSolicitarServicio)
	{
		if (!isSolicitarServicio(mensajeSolicitarServicio))
		{
			throw new IllegalArgumentException();
		}
		return mensajeSolicitarServicio.split(SEPARADOR)[1];
	}
	
	public static String getConfirmarServicio(String pasajeroId, int tiempoAprox)
	{
		return CONFIRMAR_TAXI+SEPARADOR+pasajeroId +" "+tiempoAprox;
	}
	
	public static Confirmacion getConfirmarServicio(String mensaje)
	{
		if (!isConfirmarServicio(mensaje))
		{
			throw new IllegalArgumentException();
		}
		String[] params =  mensaje.split(SEPARADOR)[1].split(" ");
		return new Confirmacion(params[0], Integer.parseInt(params[1]));
	}
	
	public static String getServicioConfirmado(int tiempoAprox)
	{
		return "Tu servicio ha sido exitosamente confirmado. Llegara en aproximadamente "+tiempoAprox+" minutos.";
	}
	
	public static boolean isSolicitarServicio(String message)
	{
		return message.matches(SOLICITAR_TAXI+SEPARADOR+"[.]");
	}
	
	public static boolean isConfirmarServicio(String message)
	{
		return message.matches(CONFIRMAR_TAXI+SEPARADOR+"[0-9]+ [0-9]+");
	}
	
	public static boolean isServicioConfirmado(String message)
	{
		return message.matches("Tu servicio ha sido exitosamente confirmado. Llegara en aproximadamente [0-9]+ minutos.");
	}
	

	
}
