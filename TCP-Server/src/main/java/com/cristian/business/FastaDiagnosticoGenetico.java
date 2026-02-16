package com.cristian.business;

public class FastaDiagnosticoGenetico extends AbstractInfoExporter{

    @Override
    protected void validate(String[] parts) {
        if (parts.length !=  4) {
            throw new IllegalArgumentException("Diagnóstico Genetico requiere 4 partes");
        }
    }

    @Override
    protected String format(String[] parts) {
        return "";
    }

    @Override
    protected String getFileName() {
        return "";
    }
}
