package com.cristian.business;

public class CSVDiagnosticoGeneticoExporter extends AbstractInfoExporter {
    @Override
    protected void validate(String[] parts) {
        if (parts.length !=  4) {
            throw new IllegalArgumentException("Diagnóstico requiere 3 partes");
        }
    }

    @Override
    protected String format(String[] parts) {
        return parts[1] + "," + parts[2] + "," + parts[3];
    }

    @Override
    protected String getFileName() {
        return "diagnosticoGenetico.csv";
    }
}
