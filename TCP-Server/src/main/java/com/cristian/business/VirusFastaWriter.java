package com.cristian.business;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
/**
 * Clase encargada de escribir registros de virus en archivos con formato FASTA.
 * Crear la carpeta de almacenamiento si no existe
 * Guardar archivos FASTA individuales por virus
 * Manejar errores de escritura
 * {@link VirusDAO} para guardar la información del virus.
 */
public class VirusFastaWriter {
    /**
     * Escribe un registro de virus en un archivo FASTA.
     *
     * @param virusRegistry contenido del archivo en formato FASTA
     * @param fileName nombre del archivo a crear
     * @return mensaje indicando el resultado de la operación
     *         <ul>
     *             <li>Mensaje de éxito si el archivo se guardó correctamente</li>
     *             <li>Mensaje de error si ocurre una excepción</li>
     *         </ul>
     */
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
