package ru.vyatsu.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vyatsu.service.structure.Brands;
import ru.vyatsu.service.structure.GarageXML;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class ConversionService {
    private static final Logger logger = LoggerFactory.getLogger(ConversionService.class);
    private static final XmlMapper xmlMapper = new XmlMapper();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private ConversionService() {
        throw new IllegalStateException("Utility class");
    }

    public static void convert(String inputFile, String outputFile, ConversionType conversionType) {
        try {
            switch (conversionType) {
                case XML_TO_JSON -> {
                    if (isXMLtoJSON(inputFile, outputFile)) {
                        val xmlContent = readFile(inputFile);
                        val jsonContent = convertXMLtoJSON(xmlContent);
                        writeFile(outputFile, jsonContent);
                    } else {
                        logger.error("Неверный формат для XML -> JSON конвертации.");
                    }
                }
                case JSON_TO_XML -> {
                    if (isJSONtoXML(inputFile, outputFile)) {
                        val jsonContent = readFile(inputFile);
                        val xmlContent = convertJSONtoXML(jsonContent);
                        writeFile(outputFile, xmlContent);
                    } else {
                        logger.error("Неверный формат для JSON -> XML конвертации.");
                    }
                }
                default -> logger.error("Несоответствие форматов файла и выбранной операции.");
            }
        } catch (Exception exception) {
            logger.error("Произошла ошибка во время конвертации: {}", exception.getMessage());
        }
    }

    private static String readFile(String path) throws IOException {
        return Files.readString(Paths.get(path));
    }

    private static void writeFile(String path, String content) throws IOException {
        Files.writeString(Paths.get(path), content, StandardCharsets.UTF_8);
    }

    public static String convertXMLtoJSON(String xmlContent) throws JsonProcessingException {
        val garageXML = xmlMapper.readValue(xmlContent, GarageXML.class);
        val result = TransformerType.XMLTOJSON.transform(garageXML);

        if (!(result instanceof Brands brands)) {
            logger.error("Ожидалось получить Brands, а получили {}", result != null ? result.getClass().getSimpleName() : "null");
            return null;
        }

        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(brands);
    }

    public static String convertJSONtoXML(String jsonContent) throws JsonProcessingException {
        val brands = objectMapper.readValue(jsonContent, Brands.class);
        val garageXML = (GarageXML) TransformerType.JSONTOXML.transform(brands);
        return xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(garageXML);
    }

    public static boolean isXMLtoJSON(String inputFile, String outputFile) {
        return inputFile.endsWith(".xml") && outputFile.endsWith(".json");
    }

    public static boolean isJSONtoXML(String inputFile, String outputFile) {
        return inputFile.endsWith(".json") && outputFile.endsWith(".xml");
    }
}