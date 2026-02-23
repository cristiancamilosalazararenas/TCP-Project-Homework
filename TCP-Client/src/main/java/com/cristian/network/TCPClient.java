package com.cristian.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Implementación de {@link IMessageService} que establece comunicación TCP
 * sin cifrado sobre una conexión estándar.
 * <p>
 * Esta clase se encarga de:
 * <ul>
 *     <li>Establecer una conexión TCP con el servidor usando los parámetros de {@link ITCPConfig}</li>
 *     <li>Enviar mensajes al servidor a través del socket</li>
 *     <li>Recibir y retornar la respuesta obtenida del servidor</li>
 * </ul>
 *
 * @see SSLTCPClient para una implementación equivalente con cifrado SSL/TLS
 */
public class TCPClient implements IMessageService {

    private final ITCPConfig tcpConfig;

    /**
     * Construye una instancia de {@code TCPClient} con la configuración TCP proporcionada.
     *
     * @param tcpConfig configuración que provee el host y el puerto del servidor destino
     */
    public TCPClient(ITCPConfig tcpConfig) {
        this.tcpConfig = tcpConfig;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Establece una conexión TCP con el servidor configurado, envía el mensaje
     * y retorna la respuesta recibida. En caso de error, retorna una cadena descriptiva
     * del fallo ocurrido:
     * <ul>
     *     <li>{@code "ERROR_HOST_DESCONOCIDO"} si no se puede resolver el host</li>
     *     <li>{@code "ERROR_COMUNICACION"} si ocurre un fallo de entrada/salida</li>
     * </ul>
     */
    @Override
    public String sendMessage(String message) {
        try (Socket socket = new Socket(tcpConfig.getHost(), tcpConfig.getPort())) {
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());
            System.out.println("[TCP] Conectados a servidor %s en puerto %s".formatted(tcpConfig.getHost(), tcpConfig.getPort()));
            out.writeUTF(message);
            out.flush();
            System.out.println("[TCP] Mensaje enviado: %s".formatted(message));
            String response = in.readUTF();
            System.out.println("[TCP] Respuesta: %s".formatted(response));
            return response;
        } catch (UnknownHostException _) {
            System.out.println("[TCP] Error de host: no se encuentra el host: %s".formatted(tcpConfig.getHost()));
            return "ERROR_HOST_DESCONOCIDO";
        } catch (IOException e) {
            System.out.println("[TCP] Error critico %s".formatted(e.getMessage()));
            return "ERROR_COMUNICACION";
        }
    }
}