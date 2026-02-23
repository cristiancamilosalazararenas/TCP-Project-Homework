package com.cristian.business;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class CSVDiagnosticoWriter {
    public void write(String idPaciente, String fecha, List<String[]> resultados) throws Exception {

        File carpeta = new File("data/pacientes/" + idPaciente);
        carpeta.mkdirs();

        File archivo = new File(carpeta, "diagnostico_" + fecha + ".csv");

        try (FileWriter writer = new FileWriter(archivo)) {

            writer.write("virus,posicion_inicio,posicion_fin\n");

            for (String[] r : resultados) {
                writer.write(r[0] + "," + r[1] + "," + r[2] + "\n");
            }
        }
    }
}
