package com.cristian.network;

import com.cristian.common.IConfigReader;

/**
 * Implementación de {@link ISSLConfig} que obtiene los parámetros de configuración
 * TCP y SSL/TLS desde una fuente de configuración externa mediante {@link IConfigReader}.
 * <p>
 * Los valores son leídos a partir de las siguientes claves:
 * <ul>
 *     <li>{@code server.address} — dirección del servidor</li>
 *     <li>{@code server.port} — puerto del servidor</li>
 *     <li>{@code ssl.truststore.path} — ruta del archivo {@code TrustStore}</li>
 *     <li>{@code ssl.truststore.password} — contraseña del {@code TrustStore}</li>
 * </ul>
 */
public class TCPConfig implements ISSLConfig {

    private final IConfigReader configReader;

    /**
     * Construye una instancia de {@code TCPConfig} con el lector de configuración proporcionado.
     *
     * @param configReader lector de configuración utilizado para obtener los parámetros de conexión
     */
    public TCPConfig(IConfigReader configReader) {
        this.configReader = configReader;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Valor leído desde la clave {@code server.address}.
     */
    @Override
    public String getHost() {
        return configReader.getString("server.address");
    }

    /**
     * {@inheritDoc}
     * <p>
     * Valor leído desde la clave {@code server.port}.
     */
    @Override
    public int getPort() {
        return configReader.getInt("server.port");
    }

    /**
     * {@inheritDoc}
     * <p>
     * Valor leído desde la clave {@code ssl.truststore.path}.
     */
    @Override
    public String getTrustStorePath() {
        return configReader.getString("ssl.truststore.path");
    }

    /**
     * {@inheritDoc}
     * <p>
     * Valor leído desde la clave {@code ssl.truststore.password}.
     */
    @Override
    public String getTrustStorePassword() {
        return configReader.getString("ssl.truststore.password");
    }
}