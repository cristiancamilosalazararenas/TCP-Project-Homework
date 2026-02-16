package com.cristian.business;

public class NameProcessor implements IMessageProcessor{
    @Override
    public String process(String message) {
        if (!message.contains("#")) {
            return "[ERROR] Error procesando el mensaje";
        }
        String[] parts = message.split("#");
        String name = parts[0];
        String lastName = parts[1];
        return "Hola %s %s".formatted(name, lastName);
    }
}
