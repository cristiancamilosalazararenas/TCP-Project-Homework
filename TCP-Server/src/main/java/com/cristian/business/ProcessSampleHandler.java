package com.cristian.business;

public class ProcessSampleHandler implements IRequestHandler{
    @Override
    public String handle(String[] parts) {
        if (parts.length != 4) {
            return "[ERROR] Diagnóstico genético inválido";
        }
        GeneticFastaWriter writer = new GeneticFastaWriter();
        return writer.write(parts[1], parts[2], parts[3]);
    }
}
