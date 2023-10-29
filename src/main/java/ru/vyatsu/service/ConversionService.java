package ru.vyatsu.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vyatsu.service.structure.Brand;
import ru.vyatsu.service.structure.Brands;
import ru.vyatsu.service.structure.GarageXML;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ConversionService {
    private static final Logger logger = LoggerFactory.getLogger(ConversionService.class);

    public void convertXMLtoJSON(String content, String outputFile) {
        try {
            String contents = Files.readString(Paths.get(content), StandardCharsets.UTF_8);
            XmlMapper xmlMapper = new XmlMapper();
            GarageXML garageXML = xmlMapper.readValue(contents, GarageXML.class);

            List<Brand> brandList = (List<Brand>) TransformerType.XMLTOJSON.transform(garageXML);

            Brands brands = new Brands();
            brands.setCarBrands(brandList);

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonContent = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(brands);

            Path outputPath = Paths.get(outputFile);
            Files.writeString(outputPath, jsonContent);
            logger.info("Конвертация XML в JSON успешно завершена");
        } catch (Exception e) {
            logger.error("Ошибка при конвертации XML в JSON: {}", e.getMessage());
        }
    }

    public void convertJSONtoXML(String content, String outputFile) {
        try {
            String contents = Files.readString(Paths.get(content), StandardCharsets.UTF_8);
            ObjectMapper objectMapper = new ObjectMapper();
            Brands brands = objectMapper.readValue(contents, Brands.class);

            GarageXML garageXML = (GarageXML) TransformerType.JSONTOXML.transform(brands);

            XmlMapper xmlMapper = new XmlMapper();
            String xmlContent = xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(garageXML);

            Path xmlOutputPath = Paths.get(outputFile);
            Files.writeString(xmlOutputPath, xmlContent);
            logger.info("Конвертация JSON в XML успешно завершена");
        } catch (Exception e) {
            logger.error("Ошибка при конвертации JSON в XML: {}", e.getMessage());
        }
    }

    public void convert(String inputFile, String outputFile, int choice) {
        try {
            if (choice == 1 && isXMLtoJSON(inputFile, outputFile)) {
                convertXMLtoJSON(inputFile, outputFile);
            } else if (choice == 2 && isJSONtoXML(inputFile, outputFile)) {
                convertJSONtoXML(inputFile, outputFile);
            } else {
                logger.error("Несоответствие форматов файла и выбранной операции");
            }
        } catch (Exception e) {
            logger.error("Произошла ошибка во время конвертации: {}", e.getMessage());
        }
    }

    public boolean isXMLtoJSON(String inputFile, String outputFile) {
        return inputFile.endsWith(".xml") && outputFile.endsWith(".json");
    }

    public boolean isJSONtoXML(String inputFile, String outputFile) {
        return inputFile.endsWith(".json") && outputFile.endsWith(".xml");
    }
}