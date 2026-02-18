package com.cristian.business;

public class RequestHandlerFactory {
    public IRequestHandler createRequestHandler(String requestType){
        return switch (requestType) {
            case "REGISTER_PATIENT" -> new RegisterPatientHandler();
            case "REGISTER_VIRUS" -> new RegisterVirusHandler();
            case "GENETIC_DIAGNOSIS" -> new ProcessSampleHandler();
            case "GENERATE_HIGH-RISK_PATIENT_REPORT" -> new HighRiskReportHandler();
            case "GENERATE_MUTATION_REPORT" -> new MutationReportHandler();
            default -> throw new IllegalArgumentException("La solicitud '" + requestType + "' es desconocida.");
        };
    }
}
