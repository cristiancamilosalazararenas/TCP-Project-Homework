package com.cristian.business;
/**
 * Crea instancias de IRequestHandler según el tipo de solicitud.
 * Dependiendo del valor de requestType, devuelve el handler correspondiente para procesar
 * la solicitud.
 */
public class RequestHandlerFactory {
    /**
     * Crea un handler de solicitud basado en el tipo proporcionado.
     *
     * @param requestType Tipo de solicitud. Valores posibles:
     *                    <ul>
     *                        <li>"REGISTER_PATIENT" - registra un paciente</li>
     *                        <li>"REGISTER_VIRUS" - registra un virus</li>
     *                        <li>"GENETIC_DIAGNOSIS" - procesa una muestra genética</li>
     *                        <li>"GENERATE_HIGH-RISK_PATIENT_REPORT" - genera reporte de pacientes de alto riesgo</li>
     *                        <li>"GENERATE_MUTATION_REPORT" - genera reporte de mutaciones</li>
     *                    </ul>
     * @return Una instancia de {@link IRequestHandler} correspondiente al tipo de solicitud
     * @throws IllegalArgumentException Si el tipo de solicitud es desconocido
     */
    public IRequestHandler createRequestHandler(String requestType){
        return switch (requestType) {
            case "REGISTER_PATIENT" -> new RegisterPatientHandler();
            case "REGISTER_VIRUS" -> new RegisterVirusHandler();
            case "GENETIC_DIAGNOSIS" -> new ProcessSampleHandler();
            case "GENERATE_HIGH-RISK_PATIENT_REPORT" -> new HighRiskReportHandler();
            default -> throw new IllegalArgumentException("La solicitud '" + requestType + "' es desconocida.");
        };
    }
}
