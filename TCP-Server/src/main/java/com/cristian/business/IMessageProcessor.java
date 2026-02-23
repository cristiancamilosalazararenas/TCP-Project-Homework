package com.cristian.business;
/**
 * Interfaz que define el contrato para los procesadores de mensajes del sistema.
 * Las clases que implementen esta interfaz deben encargarse de recibir un mensaje
 * en formato de texto, interpretarlo y ejecutar la lógica necesaria para generar
 * una respuesta.
 */
public interface IMessageProcessor {
    /**
     * Procesa un mensaje recibido y retorna el resultado de su ejecución.
     *
     * @param message mensaje de entrada que contiene la solicitud a procesar
     * @return respuesta generada tras procesar el mensaje
     */
    String process(String message);
}
