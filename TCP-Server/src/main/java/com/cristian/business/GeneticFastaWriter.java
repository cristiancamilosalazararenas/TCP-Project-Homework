package com.cristian.business;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GeneticFastaWriter {

    public String write(String documento, String fecha, String adn) {
        File patientFolder = new File("diagnosticos/" + documento);
        if (!patientFolder.exists()) {
            patientFolder.mkdirs();
        }
        File fastaFile = new File(patientFolder, fecha + ".fasta");
        try (FileWriter writer = new FileWriter(fastaFile)) {
            writer.write(">" + documento + "|" + fecha + "\n");
            writer.write(adn + "\n");
            return "[OK] Diagnóstico genético almacenado";
        } catch (IOException e) {
            e.printStackTrace();
            return "[ERROR] No se pudo guardar el diagnóstico";
        }
    }
}
