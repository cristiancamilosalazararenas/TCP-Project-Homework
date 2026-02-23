package com.cristian;

import com.cristian.common.IConfigReader;
import com.cristian.common.PropertiesManager;
import com.cristian.network.IMessageService;
import com.cristian.network.ISSLConfig;
import com.cristian.network.SSLTCPClient;
import com.cristian.network.TCPConfig;
import java.util.Scanner;

/**
 * Punto de entrada de la aplicación cliente.
 * <p>
 * Esta clase inicializa la configuración del sistema a partir del archivo
 * {@code application.properties}, establece la conexión segura SSL/TLS con el servidor
 * y presenta al usuario un menú interactivo por consola para el envío de mensajes.
 * <p>
 * Las operaciones disponibles son:
 * <ul>
 *     <li>Registrar un paciente ({@code REGISTER_PATIENT})</li>
 *     <li>Registrar un virus ({@code REGISTER_VIRUS})</li>
 *     <li>Realizar un diagnóstico genético ({@code GENETIC_DIAGNOSIS})</li>
 * </ul>
 */
public class Main {

    /**
     * Método principal que inicializa el cliente y ejecuta el bucle del menú interactivo.
     * <p>
     * El flujo de ejecución es el siguiente:
     * <ol>
     *     <li>Carga la configuración desde {@code application.properties} usando {@link PropertiesManager}</li>
     *     <li>Construye la configuración SSL mediante {@link TCPConfig}</li>
     *     <li>Instancia el cliente de comunicación {@link SSLTCPClient}</li>
     *     <li>Presenta el menú en bucle hasta que el usuario seleccione la opción de salida</li>
     * </ol>
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
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