package ru.vyatsu;

import lombok.val;
import org.junit.jupiter.api.Test;
import ru.vyatsu.service.ConversionException;
import ru.vyatsu.service.ConversionService;
import ru.vyatsu.service.ConversionType;

import java.nio.file.Paths;

import static java.nio.file.Files.exists;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static ru.vyatsu.service.ConversionType.*;

class ErrorHandlingTests {

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

    void assertConversionException(final String inputFile,final ConversionType conversionType) {
        val outputFile = "src/test/resources/output.json";
        assertThrows(ConversionException.class, () ->
                ConversionService.getInstance().convert(inputFile, outputFile, conversionType)
        );

        assertFalse("После неудачного преобразования выходной файл не должен существовать.", exists(Paths.get(outputFile)));
    }
}
