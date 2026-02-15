package com.cristian;

import com.cristian.business.IMessageProcessor;
import com.cristian.business.NameProcessor;
import com.cristian.common.IConfigReader;
import com.cristian.common.PropertiesManager;
import com.cristian.network.INetworkService;
import com.cristian.network.ISSLConfig;
import com.cristian.network.SSLTCPServer;
import com.cristian.network.TCPConfig;

public class Main {
    static void main() {
        IConfigReader reader = new PropertiesManager("application.properties");
        ISSLConfig tcpConfig = new TCPConfig(reader);
        IMessageProcessor processor = new NameProcessor();
        INetworkService server = new SSLTCPServer(tcpConfig, processor);
        server.start();
    }
}
