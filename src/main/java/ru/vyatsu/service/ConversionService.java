package ru.vyatsu.service;

import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.val;
import ru.vyatsu.service.converters.Converter;
import ru.vyatsu.service.converters.JSONtoXMLConverter;
import ru.vyatsu.service.converters.XMLtoJSONConverter;

import java.io.*;

@FieldDefaults(makeFinal = true)
public class ConversionService {
    static String EXTENSIO_NXML = ".xml";
    static String EXTENSION_JSON = ".json";

    @Getter
    static ConversionService instance = new ConversionService();

    private ConversionService() {}

    public void convert(final String inputPath, final String outputPath, final ConversionType conversionType) throws ConversionException {
        try {
            Converter<?, ?> converter;
            switch (conversionType) {
                case XML_TO_JSON -> {
                    if (!isXMLtoJSON(inputPath, outputPath)) {
                        throw new ConversionException("Неверные расширения файлов для конвертации XML в JSON.");
                    }
                    converter = new XMLtoJSONConverter();
                }
                case JSON_TO_XML -> {
                    if (!isJSONtoXML(inputPath, outputPath)) {
                        throw new ConversionException("Неверные расширения файлов для конвертации JSON в XML.");
                    }
                    converter = new JSONtoXMLConverter();
                }
                default -> throw new ConversionException("Несоответствие форматов файла и выбранной операции.");
            }

            try (val inputStream = new FileInputStream(inputPath);
                 val outputStream = new FileOutputStream(outputPath)) {
                converter.convert(inputStream, outputStream);
            }
        } catch (IOException conversionIOException) {
            throw new ConversionException("Ошибка при чтении/записи файла или обработке JSON/XML");
        }
    }

    public static boolean isXMLtoJSON(final String inputPath, final String outputFile) {
        return inputPath.endsWith(EXTENSIO_NXML) && outputFile.endsWith(EXTENSION_JSON);
    }

    public static boolean isJSONtoXML(final String inputPath, final String outputFile) {
        return inputPath.endsWith(EXTENSION_JSON) && outputFile.endsWith(EXTENSIO_NXML);
    }
}
