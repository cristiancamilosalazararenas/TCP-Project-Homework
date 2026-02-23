package com.cristian.business;

import java.io.FileWriter;
import java.io.IOException;
/**
 * Clase abstracta base para exportadores de información.
 * <p>
 * Proporciona una implementación común del método {@code export} que:
 * <ul>
 *     <li>Valida los datos recibidos</li>
 *     <li>Formatea el contenido</li>
 *     <li>Obtiene el nombre del archivo destino</li>
 *     <li>Escribe el resultado en el archivo</li>
 * </ul>
 * Las clases hijas deben definir la lógica específica implementando
 * los métodos abstractos.
 */
public abstract class AbstractInfoExporter implements IInfoExporter{
    /**
     * Exporta la información recibida.
     * <p>
     * Este método define el flujo general del proceso de exportación:
     * primero valida los datos, luego los formatea y finalmente los escribe
     * en el archivo correspondiente.
     *
     * @param parts arreglo de datos que serán procesados y exportados
     * @throws RuntimeException si ocurre un error al escribir el archivo
     */
    @Override
    public void export(String[] parts) {
        validate(parts);

        String content = format(parts);
        String fileName = getFileName();

        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(content);
            writer.write("\n");
        } catch (IOException e) {
            throw new RuntimeException("Error escribiendo archivo", e);
        }
    }
    /**
     * Valida los datos antes de procesarlos.
     *
     * @param parts datos a validar
     * @throws RuntimeException si los datos no cumplen los requisitos
     */
    protected abstract void validate(String[] parts);
    /**
     * Convierte los datos en una representación de texto lista para exportar.
     *
     * @param parts datos a formatear
     * @return cadena formateada que se escribirá en el archivo
     */
    protected abstract String format(String[] parts);
    /**
     * Obtiene el nombre del archivo donde se guardará la información.
     *
     * @return nombre del archivo destino
     */
    protected abstract String getFileName();
}
