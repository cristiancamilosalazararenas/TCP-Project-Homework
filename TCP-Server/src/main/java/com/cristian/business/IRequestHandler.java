package com.cristian.business;
/**
 * Interfaz que define el contrato para los manejadores de solicitudes del sistema.
 * <p>
 * Las clases que implementen esta interfaz deben encargarse de procesar una solicitud
 * representada como un arreglo de cadenas y devolver una respuesta en formato texto.
 * */
public interface IRequestHandler {
    /**
     * Procesa una solicitud con los parámetros recibidos.
     *
     * @param parts arreglo de cadenas que contiene los datos necesarios
     *              para ejecutar la operación solicitada
     * @return resultado del procesamiento en forma de cadena porque responde a quien hizo la solicitud
     */
    String handle(String[] parts);
}
