package com.cristian.network;

/**
 * Interfaz que define el contrato para la configuración de conexiones TCP.
 * <p>
 * Las implementaciones de esta interfaz son responsables de proporcionar
 * los parámetros básicos requeridos para establecer una conexión TCP:
 * el host de destino y el puerto de escucha.
 */
public interface ITCPConfig {

    /**
     * Obtiene la dirección del host al que se establecerá la conexión TCP.
     *
     * @return dirección del host como {@code String} (ej: {@code "localhost"} o {@code "192.168.1.1"})
     */
    String getHost();

    /**
     * Obtiene el número de puerto al que se establecerá la conexión TCP.
     *
     * @return número de puerto (ej: {@code 8080})
     */
    int getPort();
}