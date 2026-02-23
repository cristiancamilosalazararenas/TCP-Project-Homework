package com.cristian.network;

/**
 * Interfaz que define el contrato para el envío de mensajes en la red.
 * <p>
 * Las implementaciones de esta interfaz son responsables de gestionar
 * la comunicación y transmisión de mensajes hacia un destino determinado,
 * retornando la respuesta obtenida tras el envío.
 */
public interface IMessageService {

    /**
     * Envía un mensaje al destino configurado por la implementación.
     *
     * @param message contenido del mensaje a enviar
     * @return respuesta obtenida tras el envío del mensaje
     */
    String sendMessage(String message);
}