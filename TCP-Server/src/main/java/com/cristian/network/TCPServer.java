package com.cristian.network;

import com.cristian.business.IMessageProcessor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer implements INetworkService{
    private final ITCPConfig tcpConfig;
    private final IMessageProcessor processor;

    public TCPServer(ITCPConfig tcpConfig, IMessageProcessor processor){
        this.tcpConfig = tcpConfig;
        this.processor = processor;
    }

    @Override
    public void start() {
        try(ServerSocket serverSocket = new ServerSocket(tcpConfig.getPort())){
            System.out.println("[Sever] Escuchando en el puerto: "+tcpConfig.getPort());
            while (true){
                try(Socket clientSocket = serverSocket.accept();
                    DataInputStream in = new DataInputStream(clientSocket.getInputStream());
                    DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream())
                ){
                    String clientMessage = in.readUTF();

                    String response = processor.process(clientMessage);
                    out.writeUTF(response);
                    out.flush();
                }
            }
        }catch (IOException e){
            System.out.println("[Server] Error critico: "+e.getMessage());
        }

    }
}
