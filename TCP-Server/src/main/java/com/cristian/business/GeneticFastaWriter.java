package com.cristian.business;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GeneticFastaWriter {
    public String write(String documento, String fecha, String adn) {
        // carpeta del paciente
        File patientFolder = new File("pacientes/" + documento);
        if (!patientFolder.exists()) {
            patientFolder.mkdirs();
        }
        // nombre archivo muestra
        File fastaFile = new File(patientFolder, "muestra_" + fecha + ".fasta");

        try (FileWriter writer = new FileWriter(fastaFile)) {
            // encabezado FASTA
            writer.write(">" + documento + "|" + fecha + "\n");
            writer.write(adn + "\n");
            return "OK#Muestra genética almacenada";

        } catch (IOException e) {
            e.printStackTrace();
            return "ERROR#No se pudo guardar la muestra"  + e.getMessage();
        }
    }
}
