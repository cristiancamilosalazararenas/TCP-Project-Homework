package com.cristian.network;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyStore;

public class SSLTCPClient implements IMessageService{
    private final ISSLConfig sslConfig;

    public SSLTCPClient(ISSLConfig sslConfig){
        this.sslConfig = sslConfig;
    }

    private SSLSocketFactory createSSLSocketFactory() throws Exception{
        KeyStore ts = KeyStore.getInstance("PKCS12");
        char[] pwd = sslConfig.getTrustStorePassword().toCharArray();
        try(InputStream is = getClass().getClassLoader().getResourceAsStream(sslConfig.getTrustStorePath())){
            if(is == null){
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

    @Override
    public String sendMessage(String message) {
        try(Socket socket = createSSLSocketFactory().createSocket(sslConfig.getHost(), sslConfig.getPort())){
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
            System.err.println("[TCP] Errpr critico del sistema: "+e.getMessage());
            return "ERROR_CRITICO";
        }
    }
}
