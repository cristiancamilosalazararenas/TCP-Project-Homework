package com.cristian.business;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * Clase para procesar el diagnóstico de un paciente.
 * Compara la secuencia de ADN del paciente con virus registrados
 * y genera un CSV con las coincidencias encontradas.
 */
public class ProcessDiagnostic {

    public static String procesarDiagnostico(String idPaciente, String fecha, String secuenciaPaciente) {
        /**
         * Compara la secuencia del paciente con los virus y genera un CSV con los resultados.
         *
         * @param idPaciente       Identificador del paciente
         * @param fecha            Fecha del diagnóstico (para el nombre del archivo)
         * @param secuenciaPaciente Secuencia de ADN del paciente
         */
        try {
            File carpetaVirus = new File("virus");
            File[] virusFiles = carpetaVirus.listFiles();

            if (virusFiles == null || virusFiles.length == 0)
                return "ERROR#No hay virus registrados";

            File carpetaPaciente = new File("pacientesDiagnotico/" + idPaciente);
            carpetaPaciente.mkdirs();

            File csv = new File(carpetaPaciente, "diagnostico_" + fecha + ".csv");
            FileWriter writer = new FileWriter(csv);

            writer.write("virus,posicion_inicio,posicion_fin\n");

            boolean encontrado = false;

            for (File archivo : virusFiles) {

                if (!archivo.getName().endsWith(".fasta"))
                    continue;

                Scanner sc = new Scanner(archivo);

                String nombreVirus = sc.nextLine().replace(">", "");
                String secuenciaVirus = sc.nextLine().trim().toUpperCase();

                secuenciaPaciente = secuenciaPaciente.trim().toUpperCase();
                secuenciaVirus = secuenciaVirus.trim().toUpperCase();

                int minimalGeneticMatch = 3;

                for (int i = 0; i <= secuenciaVirus.length() - minimalGeneticMatch; i++) {
                    for (int j = 0; j <= secuenciaPaciente.length() - minimalGeneticMatch; j++) {
                        int k = 0;
                        while (k < minimalGeneticMatch && secuenciaVirus.charAt(i + k) == secuenciaPaciente.charAt(j + k)) {
                            k++;
                        }
                        if (k == minimalGeneticMatch) {
                            int fin = i + k;
                            writer.write(nombreVirus + "," + i + "," + fin + "\n");
                            encontrado = true;
                        }
                    }
                }

                sc.close();
            }

            writer.close();

            if (encontrado)
                return "OK#Diagnostico generado";
            else
                return "OK#Sin virus detectados";

        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR#Fallo procesamiento";
        }
    }
}