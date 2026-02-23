 package com.cristian.business;
 /**
  * Manejador responsable de procesar solicitudes de registro de pacientes.
  * <p>
  * Esta clase implementa la interfaz {@link IRequestHandler},
  * Su función principal es validar los datos recibidos,
  * construir el objeto {@link PatientDAO} con la información
  * del paciente y delegar el almacenamiento al {@link PatientCSVWriter}.
  */
public class RegisterPatientHandler implements IRequestHandler{
     /**
      * Procesa la solicitud de registro de un paciente.
      * @param parts arreglo de datos de la solicitud
      * @return mensaje indicando el resultado del registro o un error si la cantidad de parámetros es incorrecta
      */
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
