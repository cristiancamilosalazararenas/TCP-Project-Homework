package com.cristian.business;

import java.io.FileWriter;
import java.io.IOException;

public abstract class AbstractInfoExporter implements IInfoExporter{
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
    protected abstract void validate(String[] parts);
    protected abstract String format(String[] parts);
    protected abstract String getFileName();
}
