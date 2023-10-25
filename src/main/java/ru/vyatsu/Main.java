package ru.vyatsu;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.vyatsu.service.TransformerFactory;
import ru.vyatsu.service.converters.JSONtoXMLTransformer;
import ru.vyatsu.service.converters.XMLtoJSONTransformer;
import ru.vyatsu.service.structure.Brand;
import ru.vyatsu.service.structure.Brands;
import ru.vyatsu.service.structure.GarageXML;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Главный класс приложения для преобразования данных между форматами XML и JSON.
 * Поддерживает конвертацию XML в JSON и наоборот.
 */
public class Main {
    /**
     * Основной метод для запуска приложения.
     *
     * @param args Аргументы командной строки. Ожидаются два аргумента: путь к входному файлу и путь к выходному файлу.
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Нужно ввести минимум 2 аргумента: [input file path] [name output file]");
            return;
        }

        String filePath = args[0];
        String outputFile = args[1];
        String content;

        try {
            content = Files.readString(Paths.get(filePath), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + filePath);
            e.printStackTrace();
            return;
        }

        if (isXMLtoJSON(filePath, outputFile)) {
            convertXMLtoJSON(content, outputFile);
        } else if (isJSONtoXML(filePath, outputFile)) {
            convertJSONtoXML(content, outputFile);
        } else {
            System.out.println("Неподдерживаемый формат файла или комбинация форматов");
        }
    }

    private static boolean isXMLtoJSON(String inputFile, String outputFile) {
        return inputFile.endsWith(".xml") && outputFile.endsWith(".json");
    }

    private static boolean isJSONtoXML(String inputFile, String outputFile) {
        return inputFile.endsWith(".json") && outputFile.endsWith(".xml");
    }

    /**
     * Преобразует данные из формата XML в формат JSON.
     *
     * @param content Строка, содержащая XML-данные.
     * @param outputFile Путь к файлу, куда будет записан результат в формате JSON.
     */
    private static void convertXMLtoJSON(String content, String outputFile) {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            GarageXML garageXML = xmlMapper.readValue(content, GarageXML.class);

            XMLtoJSONTransformer transformer = (XMLtoJSONTransformer) TransformerFactory.createTransformer("XMLTOJSON");
            List<Brand> brandList = transformer.transform(garageXML);

            Brands brands = new Brands();
            brands.setBrands(brandList);

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonContent = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(brands);

            Path outputPath = Paths.get(outputFile);
            Files.writeString(outputPath, jsonContent);
        } catch (Exception e) {
            System.out.println("Ошибка при конвертации XML в JSON");
            e.printStackTrace();
        }
    }

    /**
     * Преобразует данные из формата JSON в формат XML.
     *
     * @param content Строка, содержащая JSON-данные.
     * @param outputFile Путь к файлу, куда будет записан результат в формате XML.
     */
    private static void convertJSONtoXML(String content, String outputFile) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Brands brands = objectMapper.readValue(content, Brands.class);

            JSONtoXMLTransformer transformerToXML = (JSONtoXMLTransformer) TransformerFactory.createTransformer("JSONTOXML");
            GarageXML garageForXML = transformerToXML.transform(brands);

            XmlMapper xmlMapper = new XmlMapper();
            String resultXmlContent = xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(garageForXML);

            Path xmlOutputPath = Paths.get(outputFile);
            Files.writeString(xmlOutputPath, resultXmlContent);
        } catch (Exception e) {
            System.out.println("Ошибка при конвертации JSON в XML");
            e.printStackTrace();
        }
    }
}
