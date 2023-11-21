package ru.vyatsu.service.converters;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface Converter {
    void convert(final InputStream inputStream,final OutputStream outputStream) throws IOException;
}
