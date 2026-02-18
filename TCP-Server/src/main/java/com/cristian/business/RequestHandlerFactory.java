package com.cristian.business;

public class RequestHandlerFactory {
    public IRequestHandler createRequestHandler(String requestType){
        return switch (requestType) {
            case "REGISTER_PATIENT" -> new RegisterPatientHandler();
            case "VALIDATE_PATIENT" -> new ValidatePatientHandler();
            case "REGISTER_VIRUS" -> new RegisterVirusHandler();
            case "PROCESS_SAMPLE" -> new ProcessSampleHandler();
            case "GENERATE_HIGH-RISK_PATIENT_REPORT" -> new HighRiskReportHandler();
            case "GENERATE_MUTATION_REPORT" -> new MutationReportHandler();
            default -> throw new IllegalArgumentException("La solicitud '" + requestType + "' es desconocida.");
        };
    }
}
