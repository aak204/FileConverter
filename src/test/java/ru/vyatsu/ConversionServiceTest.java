package ru.vyatsu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vyatsu.service.ConversionService;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


class ConversionServiceTest {

    @Test
    void testConvertXMLtoJSONSuccess() throws Exception {
        String inputXml = "src/main/resources/data.xml"; // XML file path
        String outputJson = "src/main/resources/data.json"; // Expected JSON output file path

        ConversionService conversionService = new ConversionService();
        conversionService.convertXMLtoJSON(inputXml, outputJson);

        String expectedJson = new String(Files.readAllBytes(Paths.get("src/main/resources/data.json")));
        String actualJson = new String(Files.readAllBytes(Paths.get(outputJson)));

        Assertions.assertEquals(expectedJson, actualJson, "Преобразованный JSON не соответствует ожидаемому JSON.");
    }

    @Test
    void testMainWithInsufficientArgs() {
        try (MockedStatic<LoggerFactory> mockedLoggerFactory = mockStatic(LoggerFactory.class)) {
            Logger mockLogger = mock(Logger.class);
            mockedLoggerFactory.when(() -> LoggerFactory.getLogger(any(Class.class))).thenReturn(mockLogger);

            String[] args = {};
            Main.main(args);

            verify(mockLogger).error(eq("Неподдерживаемый формат или комбинация файлов."));
        }
    }

}
