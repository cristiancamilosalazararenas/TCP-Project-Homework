package com.cristian.business;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
/**
 * Clase responsable de almacenar muestras genéticas en archivos FASTA
 * organizados por paciente.
 * Cada muestra se guarda dentro de una carpeta identificada con el documento
 * del paciente, siguiendo la estructura:
 *
 * pacientes/{documento}/muestra_{fecha}.fasta
 * El archivo generado contiene un encabezado FASTA con el formato:
 *
 * >documento|fecha
 * SECUENCIA_ADN
 */
public class GeneticFastaWriter {
    public String write(String documento, String fecha, String adn) {
        /**
         * Escribe una muestra genética en formato FASTA dentro de la carpeta
         * correspondiente al paciente.
         *
         * @param documento identificador único del paciente
         * @param fecha fecha de la muestra
         * @param adn secuencia genética a almacenar
         * @return mensaje de resultado en formato:
         *         <ul>
         *             <li>"OK#mensaje" si se guardó correctamente</li>
         *             <li>"ERROR#mensaje" si ocurrió un fallo</li>
         *         </ul>
         */
        File patientFolder = new File("pacientesDiagnlstico/" + documento);
        if (!patientFolder.exists()) {
            patientFolder.mkdirs();
        }
        // nombre archivo muestra
        File fastaFile = new File(patientFolder, "muestra_" + fecha + ".fasta");

        try (FileWriter writer = new FileWriter(fastaFile)) {
            writer.write(">" + documento + "|" + fecha + "\n");
            writer.write(adn + "\n");
            return "OK#Muestra genética almacenada";

        } catch (IOException e) {
            e.printStackTrace();
            return "ERROR#No se pudo guardar  la muestra"  + e.getMessage();
        }
    }
}
