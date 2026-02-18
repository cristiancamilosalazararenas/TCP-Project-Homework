package com.cristian.business;

public class RegisterVirusHandler implements IRequestHandler {

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
