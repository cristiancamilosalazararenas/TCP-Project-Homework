 package com.cristian.business;

public class RegisterPatientHandler implements IRequestHandler{
    @Override
    public String handle(String[] parts) {
        if (parts.length != 9) {
            return "Este tipo de solicitud requiere de 9 secciones...";
        }
        PatientCSVWriter patientCSVWriter = new PatientCSVWriter();
        PatientDAO patientDAO = new PatientDAO(parts[1], parts[2], parts[3], Integer.parseInt(parts[4]), parts[5], parts[6], parts[7], parts[8], patientCSVWriter);
        return patientDAO.register();
    }
}
