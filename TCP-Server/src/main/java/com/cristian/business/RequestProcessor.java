package com.cristian.business;
/**
 * Procesador principal de mensajes del sistema.
 * Esta clase implementa {@link IMessageProcessor} y actúa como punto de entrada
 * para todas las solicitudes recibidas en formato de texto. Su responsabilidad
 * es interpretar el mensaje, determinar el tipo de solicitud y delegar su
 * procesamiento al handler correspondiente.
 * Este diseño sigue el patrón <b>Factory + Command</b>, permitiendo agregar
 * nuevos tipos de solicitudes sin modificar esta clase.
 */
public class RequestProcessor implements IMessageProcessor{
    /**
     * Procesa un mensaje de entrada y delega su ejecución al handler correspondiente.
     *
     * @param message mensaje recibido en formato texto
     * @return resultado del procesamiento o mensaje de error si el formato es inválido
     */
    @Override
    public String process(String message) {
        if (!message.contains("#")) {
            return "Error procesando el mensaje...";
        }
        String[] parts = message.split("#");
        String requestType = parts[0];
        RequestHandlerFactory requestHandlerFactory = new RequestHandlerFactory();
        IRequestHandler requestHandler = requestHandlerFactory.createRequestHandler(requestType);
        return requestHandler.handle(parts);
    }
}