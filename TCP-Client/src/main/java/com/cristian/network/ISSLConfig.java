package com.cristian.network;

public interface ISSLConfig extends ITCPConfig{
    String getTrustStorePath();
    String getTrustStorePassword();
}
