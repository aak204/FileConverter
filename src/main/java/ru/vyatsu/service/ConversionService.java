package ru.vyatsu.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.experimental.UtilityClass;
import ru.vyatsu.service.structure.Brands;
import ru.vyatsu.service.structure.GarageXML;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.nio.charset.StandardCharsets.UTF_8;
import static ru.vyatsu.service.TransformerType.JSONTOXML;
import static ru.vyatsu.service.TransformerType.XMLTOJSON;

@UtilityClass
public class ConversionService {
    private final String EXTENSION_XML = ".xml";
    private final String EXTENSION_JSON = ".json";

    private final XmlMapper xmlMapper = new XmlMapper();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void convert(final String inputFile, final String outputFile, final ConversionType conversionType) throws ConversionException {
        try {
            switch (conversionType) {
                case XML_TO_JSON -> {
                    if (!isXMLtoJSON(inputFile, outputFile)) {
                        throw new ConversionException("Неверный формат для XML -> JSON конвертации.");
                    }
                    writeFile(outputFile, convertXMLtoJSON(readFile(inputFile)));
                }
                case JSON_TO_XML -> {
                    if (!isJSONtoXML(inputFile, outputFile)) {
                        throw new ConversionException("Неверный формат для JSON -> XML конвертации.");
                    }
                    writeFile(outputFile, convertJSONtoXML(readFile(inputFile)));
                }
                default -> throw new ConversionException("Несоответствие форматов файла и выбранной операции.");
            }
        } catch (IOException conversionIOException) {
            throw new ConversionException("Ошибка при чтении/записи файла или обработке JSON/XML", conversionIOException);
        }
    }

    private String readFile(final String path) throws IOException {
        return Files.readString(Paths.get(path));
    }

    private void writeFile(final String path, final String content) throws IOException {
        Files.writeString(Paths.get(path), content, UTF_8);
    }

    public String convertXMLtoJSON(final String xmlContent) throws JsonProcessingException {
        return objectMapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(XMLTOJSON.transform(xmlMapper.readValue(xmlContent, GarageXML.class)));
    }

    public String convertJSONtoXML(final String jsonContent) throws JsonProcessingException {
        return xmlMapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(JSONTOXML.transform(objectMapper.readValue(jsonContent, Brands.class)));
    }

    public boolean isXMLtoJSON(final String inputFile, final String outputFile) {
        return inputFile.endsWith(EXTENSION_XML) && outputFile.endsWith(EXTENSION_JSON);
    }

    public boolean isJSONtoXML(final String inputFile, final String outputFile) {
        return inputFile.endsWith(EXTENSION_JSON) && outputFile.endsWith(EXTENSION_XML);
    }
}
