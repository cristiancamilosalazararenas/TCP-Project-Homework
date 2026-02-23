package com.cristian.network;

/**
 * Interfaz que extiende {@link ITCPConfig} para añadir la configuración
 * necesaria en conexiones TCP seguras mediante SSL/TLS.
 * <p>
 * Las implementaciones de esta interfaz deben proporcionar, además de
 * los parámetros TCP base, la ruta y contraseña del almacén de confianza
 * ({@code TrustStore}) utilizado para validar los certificados del servidor.
 */
public interface ISSLConfig extends ITCPConfig {

    /**
     * Obtiene la ruta del archivo {@code TrustStore} utilizado para
     * verificar los certificados SSL/TLS del servidor.
     *
     * @return ruta del archivo {@code TrustStore} (ej: {@code "ssl/truststore.jks"})
     */
    String getTrustStorePath();

    /**
     * Obtiene la contraseña del archivo {@code TrustStore}.
     *
     * @return contraseña asociada al {@code TrustStore}
     */
    String getTrustStorePassword();
}