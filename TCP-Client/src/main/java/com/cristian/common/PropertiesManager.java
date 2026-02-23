package com.cristian.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Implementación de {@link IConfigReader} basada en archivos {@code .properties}.
 * <p>
 * Esta clase carga un archivo de propiedades desde el classpath al momento
 * de su construcción y expone los valores mediante los métodos definidos
 * en {@link IConfigReader}, permitiendo obtenerlos como {@code String},
 * {@code int} o {@code boolean}.
 */
public class PropertiesManager implements IConfigReader {

    Properties props = new Properties();

    /**
     * Construye una instancia de {@code PropertiesManager} cargando el archivo
     * de propiedades especificado desde el classpath.
     *
     * @param fileName nombre del archivo {@code .properties} a cargar (ej: {@code "config.properties"})
     * @throws RuntimeException si el archivo no es encontrado en el classpath
     */
    public PropertiesManager(String fileName) {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (is == null) {
                throw new RuntimeException("Error: No se encontró el archivo");
            }
            props.load(is);
        } catch (IOException e) {
            System.out.println("Error critico al leer las propiedades: " + e.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getString(String key) {
        return props.getProperty(key);
    }

    /**
     * {@inheritDoc}
     * @throws NumberFormatException si el valor asociado a la clave no es un número entero válido
     */
    @Override
    public int getInt(String key) {
        return Integer.parseInt(props.getProperty(key));
    }

    /**
     * {@inheritDoc}
     * <p>
     * Si el valor asociado a la clave no es {@code "true"} (ignorando mayúsculas),
     * el método retornará {@code false}.
     */
    @Override
    public boolean getBoolean(String key) {
        return Boolean.parseBoolean(props.getProperty(key));
    }
}