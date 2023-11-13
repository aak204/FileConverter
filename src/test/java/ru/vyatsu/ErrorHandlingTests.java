package ru.vyatsu;

import org.junit.jupiter.api.Test;
import ru.vyatsu.service.ConversionException;
import ru.vyatsu.service.ConversionService;
import ru.vyatsu.service.ConversionType;

import static java.nio.file.Files.exists;
import static java.nio.file.Paths.get;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static ru.vyatsu.service.ConversionType.*;

class ErrorHandlingTests {
    private final String outputFile = "src/test/resources/output.json";

    @Test
    void testConversionWithInvalidFormat() {
        assertConversionException("src/test/resources/test.invalid", XML_TO_JSON);
    }

    @Test
    void testConversionWithNonExistentInputFile() {
        assertConversionException("src/test/resources/non_existent.xml", XML_TO_JSON);
    }

    @Test
    void testConversionWithIncorrectConversionType() {
        assertConversionException("src/test/resources/data.xml", JSON_TO_XML);
    }

    @Test
    void testConversionWithUnsupportedFileFormat() {
        assertConversionException("src/test/resources/data.txt", JSON_TO_XML);
    }

    @Test
    void testConversionWithEmptyInputFile() {
        assertConversionException("src/test/resources/empty.xml", XML_TO_JSON);
    }

    private void assertConversionException(String inputFile, ConversionType conversionType) {
        assertThrows(ConversionException.class, () ->
                ConversionService.convert(inputFile, outputFile, conversionType)
        );

        assertFalse("После неудачного преобразования выходной файл не должен существовать.", exists(get(outputFile)));
    }
}
