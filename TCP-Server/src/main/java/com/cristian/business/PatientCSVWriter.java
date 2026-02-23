package com.cristian.business;

import java.io.*;

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
    public String writeCSV(String patientData){
        File patientFolder = new File("patientsRegistrados");
        if (!patientFolder.exists()) {
            patientFolder.mkdirs();
        }
        try(FileWriter writer = new FileWriter("patientsRegistrados/patients.csv", true)){
            writer.write(patientData + "\n");
            return "Paciente registrado exitosamente.";
        } catch (IOException e){
            return "Error al registrar el paciente: " + e.getMessage() + "...";
        }
    }

    /**
     * Verifica si un paciente ya se encuentra registrado en el archivo CSV.
     * <p>
     * El método lee línea por línea el archivo {@code pacientesRegistrados/pacientes.csv}
     * y compara el primer campo de cada línea (identificación) con el {@code idNumber}
     * proporcionado. Si el archivo no existe, se asume que no hay pacientes registrados.
     *
     * @param idNumber número de identificación del paciente a buscar
     * @return {@code true} si ya existe un paciente con esa identificación,
     *         {@code false} si no existe o si el archivo aún no ha sido creado
     */
    public boolean patientExists(String idNumber) {
        File file = new File("patientsRegistrados/patients.csv");
        if (!file.exists()) return false;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length > 0 && fields[0].equals(idNumber)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de pacientes: " + e.getMessage());
        }
        return false;
    }
}
