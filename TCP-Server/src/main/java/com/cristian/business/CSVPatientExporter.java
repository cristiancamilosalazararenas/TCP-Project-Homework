package com.cristian.business;

/**
 * Implementación concreta de {@link AbstractInfoExporter} encargada de exportar
 * información de pacientes en formato CSV.
 * Esta clase valida que los datos recibidos tengan la cantidad esperada de campos
 * y los formatea en una línea CSV con la siguiente estructura:
 * documento,nombre,apellido,edad,correo,genero,ciudad,pais
 * El resultado se escribe en el archivo {@code pacientes.csv}.
 */
public class CSVPatientExporter extends AbstractInfoExporter {
    /**
     * Valida que el arreglo de datos contenga exactamente 9 elementos.
     *
     * @param parts arreglo de datos del paciente
     * @throws IllegalArgumentException si la cantidad de elementos es incorrecta
     */
    @Override
    protected void validate(String[] parts) {
        if (parts.length != 9) {
            throw new IllegalArgumentException("CSV requiere 9 partes");
        }
    }
    /**
     * Convierte los datos del paciente en una línea CSV.
     * @param parts arreglo de datos del paciente
     * @return cadena formateada en CSV
     * */
    @Override
    protected String format(String[] parts) {
        String document = parts[1];
        String name = parts[2];
        String lastname = parts[3];
        String age = parts[4];
        String correo = parts[5];
        String genero = parts[6];
        String ciudad = parts[7];
        String pais = parts[8];
        return document + "," + name + "," + lastname + "," + age + "," + correo + "," + genero + "," + ciudad + "," + pais;    }

    @Override
    protected String getFileName() {
        return "pacientes.csv";
    }
}

