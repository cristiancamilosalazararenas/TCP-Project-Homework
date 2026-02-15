package com.cristian.network;

import com.cristian.business.IMessageProcessor;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocketFactory;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyStore;

public class SSLTCPServer implements INetworkService{
    private final ISSLConfig sslConfig;
    private final IMessageProcessor processor;

    public SSLTCPServer(ISSLConfig sslConfig, IMessageProcessor processor){
        this.sslConfig = sslConfig;
        this.processor = processor;
    }

    private SSLServerSocketFactory createSSLFactory() throws Exception{
        char[] password = sslConfig.getKeyStorePassword().toCharArray();
        KeyStore ks = KeyStore.getInstance("PKCS12");
        try(InputStream is = getClass().getClassLoader().getResourceAsStream(sslConfig.getKeyStorePath())){
            if(is == null){
                throw new IOException("No se encontro el archivo de keystore");
            }
            ks.load(is, password);
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            kmf.init(ks, password);
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(kmf.getKeyManagers(), null, null);
            return sslContext.getServerSocketFactory();

        }
    }

    @Override
    public void start() {
        try(ServerSocket serverSocket = createSSLFactory().createServerSocket(sslConfig.getPort())){
            System.out.println("[Sever] Escuchando en el puerto: "+sslConfig.getPort());
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
        } catch (Exception e) {
            System.err.println("Error critico");
        }

    }
}
