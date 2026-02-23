package com.cristian.business;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

/**
 * Clase responsable de generar archivos CSV con resultados de diagnósticos genéticos.
 * Se encarga de crear la estructura de carpetas necesaria
 * para almacenar diagnósticos por paciente y escribir los resultados
 * en un archivo CSV con formato estándar.
 * Y se encarga de mostrar la lista ded lso diferentes resultados obtenidos del analisis para ver
 * cuantas conincidencias existe entre el ADN y el virus

 */
public class CSVDiagnosticoWriter {
    /**
     * Genera un archivo CSV con los resultados de diagnóstico de un paciente.
     * @param idPaciente identificador único del paciente
     * @param fecha fecha del diagnóstico la cual se usa para el nombre del archivo
     * @param resultados lista de resultados donde cada elemento es un arreglo
     * nombre del virus,posición inicial , posición final
     * @throws Exception si ocurre un error durante la escritura del archivo
     */
    public void write(String idPaciente, String fecha, List<String[]> resultados) throws Exception {
        File carpeta = new File("pacientes/" + idPaciente);
        carpeta.mkdirs();

        File file = new File(carpeta, "diagnostico_" + fecha + ".csv");

        try (FileWriter writer = new FileWriter(file)) {

            writer.write("virus,posicion_inicio,posicion_fin\n");

            for (String[] r : resultados) {
                writer.write(r[0] + "," + r[1] + "," + r[2] + "\n");
            }
        }
    }
}
