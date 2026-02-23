package com.cristian.common;

/**
 * Interfaz que define el contrato para la lectura de configuraciones.
 * <p>
 * Las implementaciones de esta interfaz son responsables de obtener
 * valores de configuración a partir de una clave, soportando los tipos
 * de datos más comunes: {@code String}, {@code int} y {@code boolean}.
 */
public interface IConfigReader {

    /**
     * Obtiene un valor de configuración como cadena de texto.
     *
     * @param key clave asociada al valor de configuración
     * @return el valor correspondiente a la clave como {@code String}
     */
    String getString(String key);

    /**
     * Obtiene un valor de configuración como número entero.
     *
     * @param key clave asociada al valor de configuración
     * @return el valor correspondiente a la clave como {@code int}
     */
    int getInt(String key);

    /**
     * Obtiene un valor de configuración como valor booleano.
     *
     * @param key clave asociada al valor de configuración
     * @return {@code true} o {@code false} según el valor asociado a la clave
     */
    boolean getBoolean(String key);
}