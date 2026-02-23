package com.cristian.network;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyStore;

/**
 * Implementación de {@link IMessageService} que establece comunicación TCP
 * segura mediante SSL/TLS.
 * <p>
 * Esta clase se encarga de:
 * <ul>
 *     <li>Construir un {@link SSLSocketFactory} a partir del {@code TrustStore} configurado</li>
 *     <li>Establecer una conexión segura con el servidor usando los parámetros de {@link ISSLConfig}</li>
 *     <li>Enviar mensajes y recibir respuestas a través del socket SSL</li>
 * </ul>
 */
public class SSLTCPClient implements IMessageService {

    private final ISSLConfig sslConfig;

    /**
     * Construye una instancia de {@code SSLTCPClient} con la configuración SSL proporcionada.
     *
     * @param sslConfig configuración que provee el host, puerto, ruta y contraseña del {@code TrustStore}
     */
    public SSLTCPClient(ISSLConfig sslConfig) {
        this.sslConfig = sslConfig;
    }

    /**
     * Crea y configura un {@link SSLSocketFactory} a partir del {@code TrustStore}
     * definido en la configuración SSL.
     * <p>
     * El {@code TrustStore} se carga desde el classpath en formato {@code PKCS12}.
     * Con él se inicializa un {@link TrustManagerFactory} que valida los certificados
     * del servidor durante el handshake TLS.
     *
     * @return instancia de {@link SSLSocketFactory} lista para crear conexiones seguras
     * @throws FileNotFoundException si el archivo {@code TrustStore} no es encontrado en el classpath
     * @throws Exception si ocurre un error al cargar el {@code TrustStore} o inicializar el contexto SSL
     */
    private SSLSocketFactory createSSLSocketFactory() throws Exception {
        KeyStore ts = KeyStore.getInstance("PKCS12");
        char[] pwd = sslConfig.getTrustStorePassword().toCharArray();
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(sslConfig.getTrustStorePath())) {
            if (is == null) {
                throw new FileNotFoundException("Truststore no encontrado");
            }
            ts.load(is, pwd);
            TrustManagerFactory tmf = TrustManagerFactory
                    .getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(ts);
            SSLContext ctx = SSLContext.getInstance("TLS");
            ctx.init(null, tmf.getTrustManagers(), null);
            return ctx.getSocketFactory();
        }
    }

    /**
     * {@inheritDoc}
     * <p>
     * Establece una conexión SSL/TLS con el servidor configurado, envía el mensaje
     * y retorna la respuesta recibida. En caso de error, retorna una cadena descriptiva
     * del fallo ocurrido:
     * <ul>
     *     <li>{@code "ERROR_HOST_DESCONOCIDO"} si no se puede resolver el host</li>
     *     <li>{@code "ERROR_COMUNICACION"} si ocurre un fallo de entrada/salida</li>
     *     <li>{@code "ERROR_CRITICO"} si ocurre cualquier otro error inesperado del sistema</li>
     * </ul>
     */
    @Override
    public String sendMessage(String message) {
        try (Socket socket = createSSLSocketFactory().createSocket(sslConfig.getHost(), sslConfig.getPort())) {
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());
            System.out.println("[TCP] Conectados a servidor %s en puerto %s".formatted(sslConfig.getHost(), sslConfig.getPort()));
            out.writeUTF(message);
            out.flush();
            System.out.println("[TCP] Mensaje enviado: %s".formatted(message));
            String response = in.readUTF();
            System.out.println("[TCP] Respuesta: %s".formatted(response));
            return response;
        } catch (UnknownHostException _) {
            System.out.println("[TCP] Error de host: no se encuentra el host: %s".formatted(sslConfig.getHost()));
            return "ERROR_HOST_DESCONOCIDO";
        } catch (IOException e) {
            System.out.println("[TCP] Error critico %s".formatted(e.getMessage()));
            return "ERROR_COMUNICACION";
        } catch (Exception e) {
            System.err.println("[TCP] Error critico del sistema: " + e.getMessage());
            return "ERROR_CRITICO";
        }
    }
}