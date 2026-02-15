package com.cristian.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClient implements IMessageService{
    private final ITCPConfig tcpConfig;

    public TCPClient(ITCPConfig tcpConfig){
        this.tcpConfig = tcpConfig;
    }

    @Override
    public String sendMessage(String message) {
        try(Socket socket = new Socket(tcpConfig.getHost(), tcpConfig.getPort())){
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
