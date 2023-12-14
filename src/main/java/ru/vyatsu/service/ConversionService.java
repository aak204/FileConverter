package ru.vyatsu.service;

import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import ru.vyatsu.service.converters.Converter;
import ru.vyatsu.service.converters.JSONtoXMLConverter;
import ru.vyatsu.service.converters.XMLtoJSONConverter;

import java.io.*;

@FieldDefaults(makeFinal = true)
@Slf4j
public class ConversionService {
    static String EXTENSION_XML = ".xml";
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
            log.error("Ошибка при чтении/записи файла или обработке JSON/XML", conversionIOException);
            throw new ConversionException("Ошибка при чтении/записи файла или обработке JSON/XML", conversionIOException);
        }
    }

    public static boolean isXMLtoJSON(final String inputPath, final String outputPath) {
        return inputPath.endsWith(EXTENSION_XML) && outputPath.endsWith(EXTENSION_JSON);
    }

    public static boolean isJSONtoXML(final String inputPath, final String outputPath) {
        return inputPath.endsWith(EXTENSION_JSON) && outputPath.endsWith(EXTENSION_XML);
    }
}
