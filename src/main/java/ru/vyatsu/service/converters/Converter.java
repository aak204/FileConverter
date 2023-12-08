package ru.vyatsu.service.converters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.experimental.FieldDefaults;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@FieldDefaults(makeFinal = true)
public abstract class Converter<T, R> {
    protected XmlMapper xmlMapper = new XmlMapper();
    protected ObjectMapper objectMapper = new ObjectMapper();

    public abstract R transform(final T input) throws IOException;

    protected abstract T readValue(final InputStream inputStream) throws IOException;
    protected abstract void writeValue(final OutputStream outputStream,final R output) throws IOException;

    public void convert(final InputStream inputStream, final OutputStream outputStream) throws IOException {
        writeValue(outputStream, transform(readValue(inputStream)));
    }
}