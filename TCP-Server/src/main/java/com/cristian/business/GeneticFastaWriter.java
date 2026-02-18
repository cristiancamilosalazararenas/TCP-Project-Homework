package com.cristian.business;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GeneticFastaWriter {
    public String write(String documento, String fecha, String adn) {

        File folder = new File("diagnosticos");
        if (!folder.exists()) {
            folder.mkdirs();
        }

        try (FileWriter writer = new FileWriter("diagnosticos/diagnosticos.fasta", true)) {
            writer.write(">" + documento + "|" + fecha + "\n");
            writer.write(adn + "\n");
            return "[OK] Diagnóstico genético almacenado";
        } catch (IOException e) {
            return "[ERROR] No se pudo guardar el diagnóstico";
        }
    }
}
