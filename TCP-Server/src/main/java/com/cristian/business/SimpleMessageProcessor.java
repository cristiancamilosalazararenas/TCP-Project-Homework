package com.cristian.business;

public class SimpleMessageProcessor implements IMessageProcessor{
    @Override
    public String process(String message) {
        return "Message: %s".formatted(message);
    }
}