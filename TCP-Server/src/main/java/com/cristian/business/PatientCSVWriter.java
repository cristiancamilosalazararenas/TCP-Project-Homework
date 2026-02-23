package com.cristian.business;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
/**
 * Clase encargada de persistir la información de pacientes en un archivo CSV.
 * <p>
 * Esta clase se ocupa de:
 * <ul>
 *     <li>Crear la carpeta de almacenamiento si no existe</li>
 *     <li>Escribir registros de pacientes en un archivo CSV</li>
 *     <li>Agregar nuevos registros sin sobrescribir los existentes</li>
 * </ul>
 */
public class PatientCSVWriter {
    /**
     * Guarda la información de un paciente en el archivo CSV.
     * Si la carpeta de almacenamiento no existe, se crea automáticamente.
     * El método agrega el registro al final del archivo.
     *
     * @param patientData línea CSV con los datos del paciente
     * @return mensaje indicando el resultado de la operación
     *         <ul>
     *             <li>Mensaje de éxito si se guardó correctamente</li>
     *             <li>Mensaje de error si ocurre una excepción</li>
     *         </ul>
     */
    public String writeFasta(String patientData){
        File patientFolder = new File("pacientesRegistrados");
        if (!patientFolder.exists()) {
            patientFolder.mkdirs();
        }
        try(FileWriter writer = new FileWriter("pacientesRegistrados/pacientes.csv", true)){
            writer.write(patientData + "\n");
            return "Paciente registrado exitosamente.";
        } catch (IOException e){
            return "Error al registrar el paciente: " + e.getMessage() + "...";
        }
    }
}
