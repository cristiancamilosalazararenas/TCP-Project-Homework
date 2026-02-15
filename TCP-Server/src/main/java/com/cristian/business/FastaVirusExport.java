package com.cristian.business;

public class FastaVirusExport extends AbstractInfoExporter {

    @Override
    protected void validate(String[] parts) {
        if (parts.length != 4) {
            throw new IllegalArgumentException("FASTA requiere 4 partes");
        }
    }

    @Override
    protected String format(String[] parts) {
        String nombreVirus = parts[1];
        String nivel = parts[2];
        String secuencia = parts[3];

        return ">" + nombreVirus + "|" + nivel + "\n" + secuencia;
    }

    @Override
    protected String getFileName() {
        return "virus.fasta";
    }
}
