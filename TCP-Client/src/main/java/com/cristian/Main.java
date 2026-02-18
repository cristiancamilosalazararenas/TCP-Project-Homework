package com.cristian;

import com.cristian.common.IConfigReader;
import com.cristian.common.PropertiesManager;
import com.cristian.network.IMessageService;
import com.cristian.network.ISSLConfig;
import com.cristian.network.SSLTCPClient;
import com.cristian.network.TCPConfig;

public class Main {
    static void main(String[] args) {
        IConfigReader reader = new PropertiesManager("application.properties");
        ISSLConfig tcpConfig = new TCPConfig(reader);
        IMessageService client = new SSLTCPClient(tcpConfig);
        String response = client.sendMessage("REGISTER_PATIENT#123456789#Camila#Prada#19#camilaprada@gmail.com#Femenino#Manizales#Colombia");
        System.out.println("Respuesta: %s".formatted(response));
        response = client.sendMessage("REGISTER_PATIENT#1007235021#Cristian#Salazar#25#cristianc.salazara@autonoma.edu.co#Masculino#Manizales#Colombia");
        System.out.println("Respuesta: %s".formatted(response));
        response = client.sendMessage("REGISTER_VIRUS#H1N1#altamente_infeccioso#ATCGAAAATCGGG");
        System.out.println("Respuesta: %s".formatted(response));
        response = client.sendMessage("REGISTER_VIRUS#Covid19#altamente_infeccioso#ATCGAAAATCAAA");
        System.out.println("Respuesta: %s".formatted(response));


        response = client.sendMessage("GENETIC_DIAGNOSIS#1054863872#25-01-2026#ATGTTGC");
        System.out.println("Respuesta: %s".formatted(response));

    }
}
