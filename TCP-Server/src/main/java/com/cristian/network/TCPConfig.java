package com.cristian.network;

import com.cristian.common.IConfigReader;

public class TCPConfig implements ISSLConfig{

    private final IConfigReader configReader;

    public TCPConfig(IConfigReader configReader){
        this.configReader = configReader;
    }

    @Override
    public int getPort() {
        return configReader.getInt("server.port");
    }

    @Override
    public String getKeyStorePath() {
        return configReader.getString("ssl.keystore.path");
    }

    @Override
    public String getKeyStorePassword() {
        return configReader.getString("ssl.keystore.password");
    }
}
