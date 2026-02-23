package com.cristian.business;

public class ProcessSampleHandler implements IRequestHandler{

    private GeneticFastaWriter writer = new GeneticFastaWriter();
    private ProcessDiagnostic diagnostic = new ProcessDiagnostic();

    @Override
    public String handle(String[] parts) {
        if (parts.length != 4) {
            return "[ERROR] Diagnóstico genético inválido";
        }
        String id = parts[1];
        String fecha = parts[2];
        String fasta = parts[3];
        //guarda la muestra
        String writeResult = writer.write(id, fecha, fasta);

        if (!writeResult.startsWith("OK"))
            return writeResult;

        // analiza la muestra
        return diagnostic.procesarDiagnostico(id, fecha, fasta);
    }
}
