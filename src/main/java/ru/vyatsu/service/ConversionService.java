package ru.vyatsu.service;

import lombok.Getter;
import lombok.experimental.FieldDefaults;
import ru.vyatsu.service.converters.Converter;
import ru.vyatsu.service.converters.JSONtoXMLConverter;
import ru.vyatsu.service.converters.XMLtoJSONConverter;

import java.io.*;

@FieldDefaults(makeFinal = true)
public class ConversionService {
    static String extensionXml = ".xml";
    static String extensionJson = ".json";

    @Getter
    static ConversionService instance = new ConversionService();

    private ConversionService() {}

    public void convert(final String inputFile, final String outputFile, final ConversionType conversionType) throws ConversionException {
        try {
            Converter converter;
            switch (conversionType) {
                case XML_TO_JSON -> {
                    if (!isXMLtoJSON(inputFile, outputFile)) {
                        throw new ConversionException("Неверные расширения файлов для конвертации XML в JSON.");
                    }
                    converter = new XMLtoJSONConverter();
                }
                case JSON_TO_XML -> {
                    if (!isJSONtoXML(inputFile, outputFile)) {
                        throw new ConversionException("Неверные расширения файлов для конвертации JSON в XML.");
                    }
                    converter = new JSONtoXMLConverter();
                }
                default -> throw new ConversionException("Несоответствие форматов файла и выбранной операции.");
            }

            try (InputStream inputStream = new FileInputStream(inputFile);
                 OutputStream outputStream = new FileOutputStream(outputFile)) {
                converter.convert(inputStream, outputStream);
            }
        } catch (IOException conversionIOException) {
            throw new ConversionException("Ошибка при чтении/записи файла или обработке JSON/XML");
        }
    }

    public static boolean isXMLtoJSON(final String inputFile, final String outputFile) {
        return inputFile.endsWith(extensionXml) && outputFile.endsWith(extensionJson);
    }

    public static boolean isJSONtoXML(final String inputFile, final String outputFile) {
        return inputFile.endsWith(extensionJson) && outputFile.endsWith(extensionXml);
    }
}
