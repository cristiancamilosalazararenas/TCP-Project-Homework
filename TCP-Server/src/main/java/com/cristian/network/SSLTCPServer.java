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

    /*
    * Esta función crea una "fábrica" que genera sockets seguros (con SSL).
    * */
    private SSLServerSocketFactory createSSLFactory() throws Exception{
        char[] password = sslConfig.getKeyStorePassword().toCharArray();
        KeyStore ks = KeyStore.getInstance("PKCS12");
        try(InputStream is = getClass().getClassLoader().getResourceAsStream(sslConfig.getKeyStorePath())){
            if(is == null){
                throw new IOException("[Error] No se encontró el archivo de keystore.");
            }
            ks.load(is, password);
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            kmf.init(ks, password);
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(kmf.getKeyManagers(), null, null);
            // Devuelve la "fábrica" que creará sockets seguros.
            return sslContext.getServerSocketFactory();
        }
    }

    @Override
    public void start() {
        try(ServerSocket serverSocket = createSSLFactory().createServerSocket(sslConfig.getPort())){
            // Imprime el mensaje de que el servidor está activo.
            System.out.println("[Server] Escuchando en el puerto: "+ sslConfig.getPort());
            while (true){
                Socket clientSocket = serverSocket.accept();
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try(// Para leer datos del cliente.
                            DataInputStream in = new DataInputStream(clientSocket.getInputStream());
                            // Para escribir datos del cliente.
                            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream())
                        ){// Lee el mensaje que envió el cliente.
                            String clientMessage = in.readUTF();
                            /*
                            * Llama al processor para transformar el mensaje.
                            * Ej:"Juan:Pérez" a "Hola Juan Pérez"
                            * */
                            String response = processor.process(clientMessage);
                            // Envía la respuesta al cliente.
                            out.writeUTF(response);
                            out.flush();
                        } catch (IOException e){
                            System.out.println("[Server] Error procesando cliente: " + e.getMessage());
                        }
                    }
                });
                thread.start();
            }
        }catch (IOException e){
            System.out.println("[Server] Error crítico: " + e.getMessage() + ".");
        } catch (Exception e) {
            System.err.println("Error crítico.");
        }
    }
}
