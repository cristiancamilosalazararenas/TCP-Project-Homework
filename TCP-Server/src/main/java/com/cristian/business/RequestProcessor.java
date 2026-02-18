package com.cristian.business;

public class RequestProcessor implements IMessageProcessor{

    @Override
    public String process(String message) {
        if (!message.contains("#")) {
            return "Error procesando el mensaje...";
        }
        String[] parts = message.split("#");
        String requestType = parts[0];
        RequestHandlerFactory requestHandlerFactory = new RequestHandlerFactory();
        IRequestHandler requestHandler = requestHandlerFactory.createRequestHandler(requestType);
        return requestHandler.handle(parts);
    }
}