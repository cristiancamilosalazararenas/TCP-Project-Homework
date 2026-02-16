package com.cristian.business;

public class NameProcessor implements IMessageProcessor{
    @Override
    public String process(String message) {
        if (!message.contains("#")) {
            return "[ERROR] Error procesando el mensaje";
        }
        String[] parts = message.split("#");
        String requestType = parts[0];
        IInfoExporter exporterType = this.createExporterForType(requestType);
        exporterType.export(parts);
        return personalizedResponse(exporterType);
    }

    private IInfoExporter createExporterForType(String requestType){
        switch (requestType){
            case "REGISTRAR_PACIENTE":
                return new CSVPacienteExporter();
            case "REGISTRAR_VIRUS":
                return new FastaRegistroVirusExport();
            case "REGISTRAR_MUESTRA":
                return new FastaDiagnosticoGenetico();
            default:
                throw new IllegalArgumentException("La solicitud '" + requestType + "' es desconocida.");
        }
    }

    private String personalizedResponse(IInfoExporter exporterType){
        if(exporterType instanceof CSVPacienteExporter){
            return "Paciente registrado correctamente...";
        } else if (exporterType instanceof FastaRegistroVirusExport){
            return "Virus registrado correctamente...";
        } else {
            return "Muestra registrada correctamente...";
        }
    }
}
