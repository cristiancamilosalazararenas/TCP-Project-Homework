package com.cristian;

import com.cristian.common.IConfigReader;
import com.cristian.common.PropertiesManager;
import com.cristian.network.IMessageService;
import com.cristian.network.ISSLConfig;
import com.cristian.network.SSLTCPClient;
import com.cristian.network.TCPConfig;

import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        IConfigReader reader = new PropertiesManager("application.properties");
        ISSLConfig tcpConfig = new TCPConfig(reader);
        IMessageService client = new SSLTCPClient(tcpConfig);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Registrar paciente");
            System.out.println("2. Registrar virus");
            System.out.println("3. Diagnóstico genético");
            System.out.println("4. Salir");
            System.out.print("Seleccione opción: ");

            String option = scanner.nextLine();

            switch (option) {

                case "1":
                    System.out.println("\nFormato esperado:");
                    System.out.println("REGISTER_PATIENT#id#Nombre#Apellido#Edad#Correo#Genero#Ciudad#Pais");
                    break;

                case "2":
                    System.out.println("\nFormato esperado:");
                    System.out.println("REGISTER_VIRUS#Nombre#Tipo#Secuencia");
                    break;

                case "3":
                    System.out.println("\nFormato esperado:");
                    System.out.println("GENETIC_DIAGNOSIS#idPaciente#fecha#secuencia");
                    break;

                case "4":
                    System.out.println("Saliendo...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opción inválida");
                    continue;
            }
            System.out.print("\nEscriba el mensaje EXACTO a enviar: ");
            String message = scanner.nextLine();
            String response = client.sendMessage(message);
            System.out.println("Respuesta: " + response);
        }

    }
}
