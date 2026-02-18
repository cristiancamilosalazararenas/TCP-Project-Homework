package com.cristian.business;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class VirusFastaWriter {

    public String writeFasta(String virusRegistry, String fileName){
        File virusFolder = new File("virus");
        if (!virusFolder.exists()) {
            virusFolder.mkdirs();
        }
        try(FileWriter writer = new FileWriter("virus/"+fileName)){
            writer.write(virusRegistry);
            return "Virus registrado exitosamente.";
        } catch (IOException e){
            return "Error al registrar el virus: " + e.getMessage() + "...";
        }
    }
}
