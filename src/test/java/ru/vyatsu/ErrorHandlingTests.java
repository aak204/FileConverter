package ru.vyatsu;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.vyatsu.service.ConversionService;
import ru.vyatsu.service.ConversionType;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ErrorHandlingTests {
    @Test
    void testConversionWithInvalidFormat() {
        val invalidInputFile = "src/test/resources/test.invalid";
        val outputFile = "src/test/resources/output.json";

        assertDoesNotThrow(() ->
                ConversionService.convert(invalidInputFile, outputFile, ConversionType.XML_TO_JSON)
        );

        assertFalse(Files.exists(Paths.get(outputFile)));
    }

    @Test
    void testConversionWithNonExistentInputFile() {
        val nonExistentInputFile = "src/test/resources/non_existent.xml";
        val outputFile = "src/test/resources/output.json";

        assertDoesNotThrow(() ->
                ConversionService.convert(nonExistentInputFile, outputFile, ConversionType.XML_TO_JSON)
        );

        assertFalse(Files.exists(Paths.get(outputFile)));
    }
    @Test
    void testConversionWithIncorrectConversionType() {
        val inputFile = "src/test/resources/data.xml";
        val outputFile = "src/test/resources/output.json";

        assertDoesNotThrow(() ->
                ConversionService.convert(inputFile, outputFile, ConversionType.JSON_TO_XML)
        );

        assertFalse(Files.exists(Paths.get(outputFile)));
    }

    @Test
    void testConversionWithUnsupportedFileFormat() {
        val inputFile = "src/test/resources/data.txt";
        val outputFile = "src/test/resources/output.xml";

        assertDoesNotThrow(() ->
                ConversionService.convert(inputFile, outputFile, ConversionType.JSON_TO_XML)
        );

        assertFalse(Files.exists(Paths.get(outputFile)));
    }

    @Test
    void testConversionWithEmptyInputFile() {
        val emptyInputFile = "src/test/resources/empty.xml";
        val outputFile = "src/test/resources/output.json";

        assertDoesNotThrow(() ->
                ConversionService.convert(emptyInputFile, outputFile, ConversionType.XML_TO_JSON)
        );

        assertFalse(Files.exists(Paths.get(outputFile)));
    }
}
