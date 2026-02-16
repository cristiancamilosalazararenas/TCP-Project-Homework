package com.cristian.business;

import java.util.Arrays;

public class FastaRegistroVirusExport extends AbstractInfoExporter {

    @Override
    protected void validate(String[] parts) {
        if (parts.length != 4) {
            throw new IllegalArgumentException("FASTA requiere 4 partes");
        }

        boolean nivelValido = Arrays.stream(new String[]{"Poco Infeccioso", "Normal","Altamente Infeccioso"}).anyMatch(nivel -> nivel.equals(parts[2]));
        if (!nivelValido) {
            throw new IllegalArgumentException("Nivel de infecciosidad inválido");
        }

        boolean secuenciaInvalida = parts[3].chars()
                .anyMatch(c -> c != 'A' && c != 'T' && c != 'C' && c != 'G');
        if (secuenciaInvalida) {
            throw new IllegalArgumentException("Secuencia genética inválida");
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
