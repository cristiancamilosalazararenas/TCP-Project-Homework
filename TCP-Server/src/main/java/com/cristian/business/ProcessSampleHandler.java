package com.cristian.business;
/**
 * Manejador encargado de procesar solicitudes de análisis de muestras genéticas.
 * Implementa la interfaz {@link IRequestHandler}, por lo que maneja las solicitudes
 * <ul>
 *     <li>Guardar la muestra genética en formato FASTA</li>
 *     <li>Ejecutar el análisis diagnóstico de la secuencia</li>
 * </ul>
 *
 * Utiliza:
 * <ul>
 *     <li>{@link GeneticFastaWriter} para persistir la muestra</li>
 *     <li>{@link ProcessDiagnostic} para analizarla</li>
 * </ul>
 */
public class ProcessSampleHandler implements IRequestHandler{
    /** Componente encargado de guardar la muestra genética */
    private GeneticFastaWriter writer = new GeneticFastaWriter();

/**
 * Procesa una solicitud de análisis de muestra genética.
 * Se espera que {@code parts} contenga exactamente 4 elementos:
 * <ul>
 *     <li>0 → tipo de comando</li>
 *     <li>1 → ID del paciente</li>
 *     <li>2 → fecha de la muestra</li>
 *     <li>3 → secuencia genética (ADN)</li>
 * </ul>
 * @param parts arreglo con los datos de la solicitud
 * @return resultado del proceso: error si falla el guardado,
 */
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
        return ProcessDiagnostic.procesarDiagnostico(id, fecha, fasta);
    }
}
