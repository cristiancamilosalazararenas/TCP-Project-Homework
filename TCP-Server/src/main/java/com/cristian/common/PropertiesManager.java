package com.cristian.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesManager  implements IConfigReader{
    private final Properties props = new Properties();
    public PropertiesManager(String fileName){
        try(InputStream is = getClass().getClassLoader().getResourceAsStream(fileName)){
            if(is == null){
                throw  new RuntimeException("Error: no se encontró el archivo");
            }
            props.load(is);
            System.out.println("Archivo %s cargado correctamente".formatted(fileName));
        }catch (IOException e){
            System.out.println("[TCP] Error al leer archivo de configuración: "+e.getMessage());
        }
    }

    @Override
    public String getString(String key) {
        return props.getProperty(key);
    }

    @Override
    public int getInt(String key) {
        return Integer.parseInt(props.getProperty(key));
    }

    @Override
    public boolean getBoolean(String key) {
        return Boolean.parseBoolean(props.getProperty(key));
    }
}
