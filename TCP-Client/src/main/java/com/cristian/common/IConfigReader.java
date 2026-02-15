package com.cristian.common;

public interface IConfigReader {
    String getString(String key);
    int getInt(String key);
    boolean getBoolean(String key);
}