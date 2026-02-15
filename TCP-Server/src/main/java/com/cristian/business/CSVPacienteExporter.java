package com.cristian.business;

import java.io.FileWriter;
import java.io.IOException;

public class CSVPacienteExporter  extends AbstractInfoExporter {

    @Override
    protected void validate(String[] parts) {
        if (parts.length != 9) {
            throw new IllegalArgumentException("CSV requiere 8 partes");
        }
    }

    @Override
    protected String format(String[] parts) {
        return parts[1] + "," + parts[2] + "," + parts[3] + "," + parts[4] + "," + parts[5] + "," + parts[6] + "," + parts[7] + "," + parts[8];
    }

    @Override
    protected String getFileName() {
        return "pacientes.csv";
    }
}

