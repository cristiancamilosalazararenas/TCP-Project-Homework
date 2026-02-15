package com.cristian.network;

import com.cristian.common.IConfigReader;

public class TCPConfig implements ISSLConfig{
    private final IConfigReader configReader;

    public TCPConfig(IConfigReader configReader){
        this.configReader = configReader;
    }
    @Override
    public String getHost() {
        return configReader.getString("server.address");
    }

    @Override
    public int getPort() {
        return configReader.getInt("server.port");
    }

    @Override
    public String getTrustStorePath() {
        return configReader.getString("ssl.truststore.path");
    }

    @Override
    public String getTrustStorePassword() {
        return configReader.getString("ssl.truststore.password");
    }
}
