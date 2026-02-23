package com.cristian.business;

import java.util.Arrays;
/**
 * Implementación concreta de {@link AbstractInfoExporter} encargada de exportar
 * registros de virus en formato FASTA.
 * <p>
 * Esta clase valida que los datos recibidos tengan el formato correcto,
 * incluyendo:
 * <ul>
 *     <li>Cantidad de campos esperados</li>
 *     <li>Nivel de infecciosidad válido</li>
 *     <li>Secuencia genética compuesta únicamente por A, T, C o G</li>
 * </ul>
 **/
public class FASTARegistryVirusExport extends AbstractInfoExporter {
    /**
     * Valida los datos antes de exportarlos.
     * <p>
     * Verifica:
     * <ul>
     *     <li>Que existan exactamente 4 elementos</li>
     *     <li>Que el nivel de infecciosidad sea válido</li>
     *     <li>Que la secuencia genética contenga solo caracteres válidos</li>
     * </ul>
     *
     * @param parts arreglo de datos del virus
     * @throws IllegalArgumentException si algún dato es inválido
     */
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
    /**
     * Formatea los datos del virus en formato FASTA.
     *
     * @param parts arreglo con los datos del virus
     * @return cadena formateada lista para escribirse en archivo
     */
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
