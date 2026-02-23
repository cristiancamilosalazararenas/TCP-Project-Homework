package com.cristian.business;
/**
 * Interfaz que define el contrato para cualquier clase encargada y exportador de información.
 * Las implementaciones de esta interfaz deben encargarse de procesar
 * los datos recibidos y persistirlos en el destino correspondiente
 */
    public interface IInfoExporter {
    /**
     * Ejecuta el proceso de exportación de datos.
     *
     * @param parts arreglo de cadenas que contiene la información a exportar
     */
    void export(String [] parts);
}
