package com.cristian.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesManager implements IConfigReader{

    /*
    * Properties es una clase de Java que sirve para leer archivos
    * de configuración tipo clave=valor.
    *
    * Ejemplo de archivo.properties:
    * server.port=8080
    * ssl.keystore.path=certificado.p12
    * ssl.keystore.password=miPassword123
    * */
    private final Properties props = new Properties();

    public PropertiesManager(String fileName){
        /*
        * Abre el archivo application.properties
        * desde la carpeta resources/ del proyecto para leerlo
        * y almacénalo en la variable is.
        * */
        try(InputStream is = getClass().getClassLoader().getResourceAsStream(fileName)){
            if(is == null){
                throw  new RuntimeException("Error: no se encontró el archivo 'application.properties'.");
            }
            props.load(is);
            System.out.println("Archivo %s cargado correctamente".formatted(fileName));
            /*
            * El IOException se ejecuta cuando el archivo existe, pero hay
            * errores de lectura/escritura, es decir, un archivo corrupto.
            * */
        }catch (IOException e){
            System.out.println("[TCP] Error al leer el archivo 'application.properties': "+e.getMessage());
        }
    }

    /*
    * Piensa en un diccionario. Tu le das la clave y el te retorna el valor.
    * */
    @Override
    public String getString(String key) {
        return props.getProperty(key);
    }

    /*
     * Piensa en un diccionario. Tu le das la clave y el te retorna el valor.
     * */
    @Override
    public int getInt(String key) {
        return Integer.parseInt(props.getProperty(key));
    }

    /*
     * Piensa en un diccionario. Tu le das la clave y el te retorna el valor.
     * */
    @Override
    public boolean getBoolean(String key) {
        return Boolean.parseBoolean(props.getProperty(key));
    }
}
