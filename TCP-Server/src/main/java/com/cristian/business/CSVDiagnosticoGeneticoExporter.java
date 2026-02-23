package com.cristian.business;
/**
 * Implementación concreta de {@link AbstractInfoExporter} encargada de exportar
 * diagnósticos genéticos en formato CSV.
 * Esta clase valida que los datos recibidos tengan la estructura esperada
 * y genera una línea CSV con la siguiente estructura:
 *  virus,posicion_inicio,posicion_fin
 **/
public class CSVDiagnosticoGeneticoExporter extends AbstractInfoExporter {
    /**
     * Valida que el arreglo contenga la cantidad correcta de datos.
     *
     * @param parts arreglo de datos del diagnóstico
     * @throws IllegalArgumentException si la cantidad de elementos no es válida
     */
    @Override
    protected void validate(String[] parts) {
        if (parts.length !=  4) {
            throw new IllegalArgumentException("Diagnóstico requiere 3 partes");
        }
    }
    /**
     * Formatea los datos del diagnóstico en una línea CSV.
     * <p>
     * Ignora el primer elemento del arreglo y usa los índices:
     * <ul>
     *     <li>1 → virus</li>
     *     <li>2 → posición inicial</li>
     *     <li>3 → posición final</li>
     * </ul>
     *
     * @param parts datos del diagnóstico
     * @return cadena formateada en CSV
     */
    @Override
    protected String format(String[] parts) {
        return parts[1] + "," + parts[2] + "," + parts[3];
    }

    @Override
    protected String getFileName() {
        return "diagnosticoGenetico.csv";
    }
}
