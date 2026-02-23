package com.cristian.business;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PatientCSVWriter {

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
