package com.cristian;

import com.cristian.common.IConfigReader;
import com.cristian.common.PropertiesManager;
import com.cristian.network.IMessageService;
import com.cristian.network.ISSLConfig;
import com.cristian.network.SSLTCPClient;
import com.cristian.network.TCPConfig;

public class Main {
    static void main(String[] args) {
        IConfigReader reader = new PropertiesManager("application.properties");
        ISSLConfig tcpConfig = new TCPConfig(reader);
        IMessageService client = new SSLTCPClient(tcpConfig);
        String response = client.sendMessage("Camila:Cortes");
        System.out.println("Respuesta: %s".formatted(response));
    }
}
