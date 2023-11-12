package ru.vyatsu;

import org.junit.jupiter.api.Test;
import ru.vyatsu.service.ConversionException;
import ru.vyatsu.service.ConversionService;
import ru.vyatsu.service.ConversionType;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;

class ErrorHandlingTests {
    private final String outputFile = "src/test/resources/output.json";

    @Test
    void testConversionWithInvalidFormat() {
        assertConversionException("src/test/resources/test.invalid", ConversionType.XML_TO_JSON);
    }

    @Test
    void testConversionWithNonExistentInputFile() {
        assertConversionException("src/test/resources/non_existent.xml", ConversionType.XML_TO_JSON);
    }

    @Test
    void testConversionWithIncorrectConversionType() {
        assertConversionException("src/test/resources/data.xml", ConversionType.JSON_TO_XML);
    }

    @Test
    void testConversionWithUnsupportedFileFormat() {
        assertConversionException("src/test/resources/data.txt", ConversionType.JSON_TO_XML);
    }

    @Test
    void testConversionWithEmptyInputFile() {
        assertConversionException("src/test/resources/empty.xml", ConversionType.XML_TO_JSON);
    }

    private void assertConversionException(String inputFile, ConversionType conversionType) {
        assertThrows(ConversionException.class, () ->
                ConversionService.convert(inputFile, outputFile, conversionType)
        );

        assertFalse("После неудачного преобразования выходной файл не должен существовать.", Files.exists(Paths.get(outputFile)));
    }
}
