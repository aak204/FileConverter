package ru.vyatsu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vyatsu.service.ConversionService;
import ru.vyatsu.service.ConversionType;
import ru.vyatsu.service.MenuService;
import java.io.ByteArrayInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


class ConversionServiceTest {

    @Test
    void testConvertXMLtoJSONSuccess() throws Exception {
        String inputXml = new String(Files.readAllBytes(Paths.get("src/test/resources/data.xml")));

        String actualXml = ConversionService.convertXMLtoJSON(inputXml);

        String expectedJson = new String(Files.readAllBytes(Paths.get("src/test/resources/data.json")));

        Assertions.assertEquals(expectedJson, actualXml, "Преобразованный JSON не соответствует ожидаемому XML.");
    }

    @Test
    void testMainWithInsufficientArgs() {
        try (MockedStatic<LoggerFactory> mocked = mockStatic(LoggerFactory.class)) {
            Logger mockLogger = mock(Logger.class);
            mocked.when(() -> LoggerFactory.getLogger(any(Class.class))).thenReturn(mockLogger);

            String[] args = {"data.json"}; // Недостаточно аргументов
            Main.main(args);

            verify(mockLogger).error(contains("Неверное количество аргументов."));
        }
    }

    @Test
    void testConvertJSONtoXMLSuccess() throws Exception {
        String inputJson = new String(Files.readAllBytes(Paths.get("src/test/resources/data.json")));

        String actualXml = ConversionService.convertJSONtoXML(inputJson);

        String expectedXml = new String(Files.readAllBytes(Paths.get("src/test/resources/data.xml")));

        Assertions.assertEquals(expectedXml, actualXml, "Преобразованный XML не соответствует ожидаемому JSON.");
    }
    @Test
    void testConversionWithInvalidFormat() {
        String invalidInputFile = "src/test/resources/test.invalid";
        String outputFile = "src/test/resources/output.json";

        assertDoesNotThrow(() ->
                ConversionService.convert(invalidInputFile, outputFile, ConversionType.XML_TO_JSON)
        );

        Assertions.assertFalse(Files.exists(Paths.get(outputFile)));
    }

    @Test
    void testConversionWithNonExistentInputFile() {
        String nonExistentInputFile = "src/test/resources/non_existent.xml";
        String outputFile = "src/test/resources/output.json";

        assertDoesNotThrow(() ->
                ConversionService.convert(nonExistentInputFile, outputFile, ConversionType.XML_TO_JSON)
        );

        Assertions.assertFalse(Files.exists(Paths.get(outputFile)));
    }
    @Test
    void testConversionWithIncorrectConversionType() {
        String inputFile = "src/test/resources/data.xml";
        String outputFile = "src/test/resources/output.json";

        assertDoesNotThrow(() ->
                ConversionService.convert(inputFile, outputFile, ConversionType.JSON_TO_XML)
        );

        Assertions.assertFalse(Files.exists(Paths.get(outputFile)));
    }

    @Test
    void testConversionWithUnsupportedFileFormat() {
        String inputFile = "src/test/resources/data.txt";
        String outputFile = "src/test/resources/output.xml";

        assertDoesNotThrow(() ->
                ConversionService.convert(inputFile, outputFile, ConversionType.JSON_TO_XML)
        );

        Assertions.assertFalse(Files.exists(Paths.get(outputFile)));
    }

    @Test
    void testConversionWithEmptyInputFile() {
        String emptyInputFile = "src/test/resources/empty.xml";
        String outputFile = "src/test/resources/output.json";

        assertDoesNotThrow(() ->
                ConversionService.convert(emptyInputFile, outputFile, ConversionType.XML_TO_JSON)
        );

        Assertions.assertFalse(Files.exists(Paths.get(outputFile)));
    }

    @Test
    void testGetUserChoiceValidInput() {
        String input = "1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        try {
            Assertions.assertEquals(1, MenuService.getUserChoice());
        } finally {
            System.setIn(System.in); // Возвращаем стандартный ввод
        }
    }
}
