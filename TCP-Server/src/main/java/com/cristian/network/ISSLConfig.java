package com.cristian.network;

public interface ISSLConfig extends ITCPConfig{
    String getKeyStorePath();
    String getKeyStorePassword();
}
