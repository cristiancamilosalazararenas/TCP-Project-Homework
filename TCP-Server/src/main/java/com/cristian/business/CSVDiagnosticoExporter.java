package com.cristian.business;

public class CSVDiagnosticoExporter extends AbstractInfoExporter {
    @Override
    protected void validate(String[] parts) {
        if (parts.length !=  4) {
            throw new IllegalArgumentException("Diagnóstico requiere 3 partes");
        }
    }

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
        return document + "," + name + "," + lastname + "," + age + "," + correo + "," + genero + "," + ciudad + "," + pais;
    }

    @Override
    protected String getFileName() {
        return "diagnostico.csv";
    }
}
