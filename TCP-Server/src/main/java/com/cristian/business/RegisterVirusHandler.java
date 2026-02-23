package com.cristian.business;
/**
 * Manejador encargado de procesar solicitudes de registro de virus.
 * <p>
 * Implementa la interfaz {@link IRequestHandler}, por lo que forma parte del
 * sistema de procesamiento de comandos. Su responsabilidad es validar los datos
 * recibidos, crear el objeto {@link VirusDAO} con la información del virus y
 * delegar el almacenamiento al componente {@link VirusFastaWriter}.
 */
public class RegisterVirusHandler implements IRequestHandler {
    /**
     * Procesa una solicitud de registro de virus.
     * <p>
     * Se espera que el arreglo {@code parts} contenga exactamente 4 elementos
     *
     */
    @Override
    public String handle(String[] parts) {
        if (parts.length != 4) {
            return "Este tipo de solicitud requiere de 4 secciones...";
        }
        VirusFastaWriter virusFastaWriter = new VirusFastaWriter();
        VirusDAO virusDAO = new VirusDAO(parts[1], parts[2], parts[3], virusFastaWriter);
        return virusDAO.register();
    }

}
